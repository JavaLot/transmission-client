package org.transmission.client;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.RecordComponent;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.BitSet;
import java.util.Collection;

public class Printer {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static String string(Record record) {
        StringBuilder result = new StringBuilder();

        if(record != null) {
            result.append(record.getClass().getSimpleName()).append('[');
            int counter = 0;
            RecordComponent[] comps = record.getClass().getRecordComponents();
            for (RecordComponent comp : comps) {
                Method m = comp.getAccessor();
                try {
                    Object v = m.invoke(record);
                    if (v != null) {
                        if(counter > 0) {
                            result.append(", ");
                        }
                        result.append(comp.getName()).append('=');
                        if(v instanceof Record other) {
                            result.append(string(other));
                        } else if(v instanceof BitSet bit) {
                            for(int i = 0; i < bit.length(); i++) {
                                if(bit.get(i)) result.append('1'); else result.append('0');
                            }
                        } else if(v instanceof Collection col) {
                            result.append('[');
                            int cc = 0;
                            for (Object o : col) {
                                if(cc > 0) {
                                    result.append(", ");
                                }
                                if(o instanceof Record crec) {
                                    result.append(string(crec));
                                } else {
                                    result.append(o);
                                }
                                cc++;
                            }
                            result.append(']');
                        } else {
                            result.append(v);
                        }
                        counter++;
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            result.append(']');
        }

        return result.toString();
    }

    public static void println(Record record) {
        System.out.println(string(record));
    }

    public static void print(Record record) {
        System.out.print(string(record));
    }

    public static String timing(TrTiming timing) {
        return instant(timing.post1()) + " " + duration(timing.postDuration());
    }

    public static String instant(Instant instant) {
        return instant != null? LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).format(DATE_TIME_FORMATTER): "";
    }

    public static String duration(Duration duration) {
        return duration!= null? duration.toString().substring(2).replaceAll("(\\d[HMS])(?!$)", "$1 ").toLowerCase(): "";
    }

    public static void println(TrTiming timing) {
        System.out.println("two posts: " + timing.isTwoPosts() + ", session: " + timing.sessionId());
        System.out.println("post: " + duration(timing.postDuration()));
        System.out.println("parse: " + duration(timing.parseDuration()));
        Duration resultDuration = timing.resultDuration();
        if(resultDuration != null) {
            System.out.println("result: " + duration(resultDuration));
        }
        Duration serializationDuration = timing.serializationDuration();
        if(serializationDuration != null) {
            System.out.println("serialization: " + duration(serializationDuration));
        }
        Duration deserializationDuration = timing.deserializationDuration();
        if(deserializationDuration != null) {
            System.out.println("deserialization: " + duration(deserializationDuration));
        }
        System.out.println("all: " + duration(timing.duration()));
    }
}
