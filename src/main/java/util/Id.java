package util;

import java.util.concurrent.atomic.AtomicLong;

public class Id {
    private static AtomicLong idCounter = new AtomicLong();

    public static String createId() {
        return String.valueOf(idCounter.getAndIncrement());
    }
}
