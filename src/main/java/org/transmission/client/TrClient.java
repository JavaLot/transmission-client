package org.transmission.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.transmission.client.args.*;
import org.transmission.client.jackson.JacksonProvider;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.zip.GZIPInputStream;

public class TrClient {

    private final HttpClient httpClient;
    private final HttpRequest.Builder requestBuilder;
    private final ObjectMapper mapper;
    private final ThreadLocal<TrTiming> timing = new ThreadLocal<>();

    private String trSessionId;

    public TrClient(URI rpcURI) {
        this(rpcURI, null, null, null, false, false);
    }

    public TrClient(URI rpcURI, PasswordAuthentication auth) {
        this(rpcURI, auth, null, null, false, false);
    }

    private TrClient(
            URI rpcURI,
            PasswordAuthentication auth,
            Consumer<HttpClient.Builder> httpClientTuner,
            Consumer<HttpRequest.Builder> httpRequestTuner,
            boolean useGzip,
            boolean strictJsonMapping
    ) {
        requestBuilder = HttpRequest.newBuilder(rpcURI).header("Content-Type", "application/json");

        if (auth != null) {
            requestBuilder.header("Authorization", "Basic " + base64(auth));
        }

        HttpClient.Builder httpClientBuilder = HttpClient.newBuilder();
        if(httpClientTuner != null) {
            httpClientTuner.accept(httpClientBuilder);
        }

        httpClient = httpClientBuilder.build();

        if(httpRequestTuner != null) {
            httpRequestTuner.accept(requestBuilder);
        }

        if(useGzip) {
            requestBuilder.header("Accept-Encoding", "gzip");
        }

        mapper = JacksonProvider.getMapper(strictJsonMapping);
    }

    public TrTiming getTiming() {
        return timing.get();
    }

    private TrResponse<?> post(TrRequest<?> req) throws TrException {
        TrResponse<?> result;

        Instant start = Instant.now();
        Instant post1 = null;
        Instant result1 = null;
        Instant start2 = null;
        Instant post2 = null;
        Instant result2 = null;

        try {
            timing.remove();

            HttpRequest.Builder copyBuilder = requestBuilder.copy();
            if (trSessionId != null) {
                copyBuilder.header("X-Transmission-Session-Id", trSessionId);
            }
            copyBuilder.POST(bodyPublisher(req));

            post1 = Instant.now();
            HttpResponse<InputStream> response = httpClient.send(copyBuilder.build(), HttpResponse.BodyHandlers.ofInputStream());
            result1 = Instant.now();

            if (response.statusCode() == 409) {
                start2 = Instant.now();
                Optional<String> trSession = response.headers().firstValue("X-Transmission-Session-Id");
                trSession.ifPresent(s -> trSessionId = s);

                copyBuilder = requestBuilder.copy()
                        .header("X-Transmission-Session-Id", trSessionId)
                        .POST(bodyPublisher(req));

                post2 = Instant.now();
                response = httpClient.send(copyBuilder.build(), HttpResponse.BodyHandlers.ofInputStream());
                result2 = Instant.now();
            }

            InputStream stream = response.headers().firstValue("Content-Encoding").orElse("").equals("gzip")?
                    new GZIPInputStream(response.body()):
                    response.body();

            if(response.statusCode() == 200) {
                result = mapper.readValue(stream, req.method().getJavaType());
            } else {
                String message = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
                result = new TrResponse<>(message);
            }
        } catch (IOException | InterruptedException e) {
            throw new TrException(e);
        } finally {
            timing.set(new TrTiming(trSessionId, start, post1, result1, start2, post2, result2, Instant.now()));

            if(req.method() == TrMethod.SESSION_CLOSE) {
                trSessionId = null;
            }
        }

        if(!result.isSuccess()) {
            throw new TrException(result.result());
        }

        return result;
    }

    private HttpRequest.BodyPublisher bodyPublisher(TrRequest<?> req) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        mapper.writeValue(bos, req);
        return HttpRequest.BodyPublishers.ofByteArray(bos.toByteArray());
    }

    private static String base64(PasswordAuthentication auth) {
        return new String(Base64.getEncoder().encode((auth.getUserName() + ':' + String.valueOf(auth.getPassword())).getBytes(StandardCharsets.UTF_8)));
    }

    // -- transmission commands ----------------------------------------------------------------------------------------
    public void torrentStart(TorrentIds arg) throws TrException {
        TrRequest<TorrentIds> req = new TrRequest<>(TrMethod.TORRENT_START, arg);
        post(req);
    }

    public void torrentStartNow(TorrentIds arg) throws TrException {
        TrRequest<TorrentIds> req = new TrRequest<>(TrMethod.TORRENT_START_NOW, arg);
        post(req);
    }

    public void torrentStop(TorrentIds arg) throws TrException {
        TrRequest<TorrentIds> req = new TrRequest<>(TrMethod.TORRENT_STOP, arg);
        post(req);
    }

    public void torrentVerify(TorrentIds arg) throws TrException {
        TrRequest<TorrentIds> req = new TrRequest<>(TrMethod.TORRENT_VERIFY, arg);
        post(req);
    }

    public void torrentReannounce(TorrentIds arg) throws TrException {
        TrRequest<TorrentIds> req = new TrRequest<>(TrMethod.TORRENT_REANNOUNCE, arg);
        post(req);
    }

    public void torrentSet(TorrentSet arg) throws TrException {
        TrRequest<TorrentSet> req = new TrRequest<>(TrMethod.TORRENT_SET, arg);
        post(req);
    }

    public Torrents torrentGet(TorrentGet arg) throws TrException {
        TrRequest<TorrentGet> req = new TrRequest<>(TrMethod.TORRENT_GET, arg);
        return (Torrents) post(req).arguments();
    }

    public TorrentAdded torrentAdd(TorrentAdd arg) throws TrException {
        TrRequest<TorrentAdd> req = new TrRequest<>(TrMethod.TORRENT_ADD, arg);
        return (TorrentAdded) post(req).arguments();
    }

    public void torrentRemove(TorrentRemove arg) throws TrException {
        TrRequest<TorrentRemove> req = new TrRequest<>(TrMethod.TORRENT_REMOVE, arg);
        post(req);
    }

    public void torrentSetLocation(TorrentSetLocation arg) throws TrException {
        TrRequest<TorrentSetLocation> req = new TrRequest<>(TrMethod.TORRENT_SET_LOCATION, arg);
        post(req);
    }

    public TorrentRenamedPath torrentRenamePath(TorrentRenamePath arg) throws TrException {
        TrRequest<TorrentRenamePath> req = new TrRequest<>(TrMethod.TORRENT_RENAME_PATH, arg);
        return (TorrentRenamedPath) post(req).arguments();
    }

    public void sessionSet(Session arg) throws TrException {
        TrRequest<Session> req = new TrRequest<>(TrMethod.SESSION_SET, arg);
        post(req);
    }

    public Session sessionGet() throws TrException {
        TrRequest<Void> req = new TrRequest<>(TrMethod.SESSION_GET);
        return (Session) post(req).arguments();
    }

    public Session sessionGet(SessionGet arg) throws TrException {
        TrRequest<SessionGet> req = new TrRequest<>(TrMethod.SESSION_GET, arg);
        return (Session) post(req).arguments();
    }

    public SessionStats sessionStats() throws TrException {
        TrRequest<Void> req = new TrRequest<>(TrMethod.SESSION_STATS);
        return (SessionStats) post(req).arguments();
    }

    public BlockList blocklistUpdate() throws TrException {
        TrRequest<BlockList> req = new TrRequest<>(TrMethod.BLOCKLIST_UPDATE);
        return (BlockList) post(req).arguments();
    }

    public PortTest portTest() throws TrException {
        TrRequest<PortTest> req = new TrRequest<>(TrMethod.PORT_TEST);
        return (PortTest) post(req).arguments();
    }

    public void sessionClose() throws TrException {
        TrRequest<Void> req = new TrRequest<>(TrMethod.SESSION_CLOSE);
        post(req);
    }

    public void queueMoveTop(TorrentIds arg) throws TrException {
        TrRequest<TorrentIds> req = new TrRequest<>(TrMethod.QUEUE_MOVE_TOP, arg);
        post(req);
    }

    public void queueMoveUp(TorrentIds arg) throws TrException {
        TrRequest<TorrentIds> req = new TrRequest<>(TrMethod.QUEUE_MOVE_UP, arg);
        post(req);
    }

    public void queueMoveDown(TorrentIds arg) throws TrException {
        TrRequest<TorrentIds> req = new TrRequest<>(TrMethod.QUEUE_MOVE_DOWN, arg);
        post(req);
    }

    public void queueMoveBottom(TorrentIds arg) throws TrException {
        TrRequest<TorrentIds> req = new TrRequest<>(TrMethod.QUEUE_MOVE_BOTTOM, arg);
        post(req);
    }

    public FreeSpace freeSpace(FreeSpace arg) throws TrException {
        TrRequest<FreeSpace> req = new TrRequest<>(TrMethod.FREE_SPACE, arg);
        return (FreeSpace) post(req).arguments();
    }

    // -- builder ------------------------------------------------------------------------------------------------------
    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private URI rpcURI;
        private PasswordAuthentication authentication;
        private Consumer<HttpClient.Builder> httpClientTuner;
        private Consumer<HttpRequest.Builder> httpRequestTuner;
        private boolean useGzip;
        private boolean strictJsonMapping;

        public TrClient build() {
            return new TrClient(
                    rpcURI,
                    authentication,
                    httpClientTuner,
                    httpRequestTuner,
                    useGzip,
                    strictJsonMapping
            );
        }

        public Builder rpcUri(URI rpcURI) {
            this.rpcURI = rpcURI;
            return this;
        }

        public Builder authenticate(PasswordAuthentication authentication) {
            this.authentication = authentication;
            return this;
        }

        public Builder httpClientTune(Consumer<HttpClient.Builder> httpClientTuner) {
            this.httpClientTuner = httpClientTuner;
            return this;
        }

        public Builder httpRequestTune(Consumer<HttpRequest.Builder> httpRequestTuner) {
            this.httpRequestTuner = httpRequestTuner;
            return this;
        }

        public Builder useGzip() {
            this.useGzip = true;
            return this;
        }

        public Builder strictJsonMapping() {
            this.strictJsonMapping = true;
            return this;
        }
    }
}
