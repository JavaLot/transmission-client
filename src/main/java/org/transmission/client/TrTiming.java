package org.transmission.client;

import java.time.Duration;
import java.time.Instant;

public record TrTiming(
        String sessionId,
        Instant start,
        Instant post1,
        Instant result1,
        Instant start2,
        Instant post2,
        Instant result2,
        Instant exit
) {
    public boolean isTwoPosts() {
        return post2 != null;
    }

    public Duration postDuration() {
        Duration result = null;
        if(result1 != null) {
            result = Duration.between(post1, result1);
        }
        if(result != null && post2 != null && result2 != null) {
            result = result.plus(Duration.between(post2, result2));
        }
        return result;
    }

    public Duration parseDuration() {
        Duration postDuration = postDuration();
        if(postDuration != null) {
            return Duration.between(start, exit).minus(postDuration);
        } else {
            return Duration.between(start, exit);
        }
    }

    public Duration resultDuration() {
        if(post2 != null && result2 != null) {
            return Duration.between(post2, result2);
        } else if(result1 != null) {
            return Duration.between(post1, result1);
        } else {
            return null;
        }
    }

    public Duration serializationDuration() {
        Duration result = Duration.between(start, post1);
        if(start2 != null && post2 != null) {
            result = result.plus(Duration.between(start2, post2));
        }
        return result;
    }

    public Duration deserializationDuration() {
        Duration result = null;
        if(result1 != null) {
            result = Duration.between(result1, (start2!=null? start2: exit));
        }
        if(result != null && result2 != null) {
            result = result.plus(Duration.between(result2, exit));
        }
        return result;
    }

    public Duration duration() {
        return Duration.between(start, exit);
    }
}
