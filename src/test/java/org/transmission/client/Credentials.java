package org.transmission.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Credentials(
        URI rpcURI,
        String userName,
        char[] userPassword
) {
    public static final String RPC_URI = "rpcURI";
    public static final String USER_NAME = "userName";
    public static final String USER_PASSWORD = "userPassword";
    public static final String PROPERTY_DELIMITER = ".";

    private static final Map<String, Credentials> credentials = new HashMap<>();
    private static final Object lock = new Object();

    /**
     * Get default credentials for docker transmission.
     *
     * @return docker credentials
     */
    public static Credentials get() {
        loadProps();
        return credentials.get(null);
    }

    public static Credentials get(String name) {
        loadProps();
        return credentials.get(name);
    }

    public static Collection<String> configs() {
        loadProps();
        return credentials.keySet();
    }

    private static void loadProps() {
        if (credentials.size() == 0) {
            synchronized (lock) {
                if (credentials.size() == 0) {
                    Properties props = new Properties();
                    boolean success = false;
                    try (InputStream is = new FileInputStream("secure.properties")) {
                        props.load(is);
                        success = true;
                    } catch (IOException ex) {
                        dockerDefault();
                    }

                    if(success) {
                        Map<String, String[]> propsGroups = new HashMap<>();
                        Pattern pattern = Pattern.compile("(.*?)\\" + PROPERTY_DELIMITER + "?(" + RPC_URI + "|" + USER_NAME + "|" + USER_PASSWORD +")");

                        props.forEach((name, value) -> {
                            Matcher matcher = pattern.matcher((String)name);
                            if(matcher.matches()) {
                                String configName = matcher.group(1);
                                if(configName.isBlank()) {
                                    configName = null;
                                }
                                String propName = matcher.group(2);
                                String[] group = propsGroups.computeIfAbsent(configName, k -> new String[3]);
                                switch (propName) {
                                    case RPC_URI -> group[0] = (String) value;
                                    case USER_NAME -> group[1] = (String) value;
                                    case USER_PASSWORD -> group[2] = (String) value;
                                }
                            }
                        });

                        for(String key:propsGroups.keySet()) {
                            String[] values = propsGroups.get(key);
                            URI rpcURI = URI.create(values[0]);
                            String userName = values[1];
                            char[] userPass = values[2] != null? values[2].toCharArray(): null;
                            Credentials cred = new Credentials(rpcURI, userName, userPass);
                            credentials.put(key, cred);
                        }

                        if(!credentials.containsKey(null)) {
                            dockerDefault();
                        }
                    }
                }
            }
        }
    }

    private static void dockerDefault() {
        credentials.put(null, new Credentials(URI.create("http://localhost:9099/transmission/rpc"), null, null));
    }

    public PasswordAuthentication getAuthentication() {
        return userPassword != null? new PasswordAuthentication(userName, userPassword): null;
    }
}
