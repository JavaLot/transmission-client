package org.transmission.client;

/**
 * byte sizes accommodated in 64 bit java long.
 *
 * @author dad
 */
public enum ByteUnit {
    B(1),
    KB(1024L),
    MB(1048576L),
    GB(1073741824L),
    TB(1099511627776L),
    PB(1125899906842624L),
    EB(1152921504606846976L);

    private final long size;

    ByteUnit(long size) {
        this.size = size;
    }

    public long bytes() {
        return size;
    }

    public static String print(long value) {
        boolean negativ = false;
        if(value < 0) {
            value = -value;
            negativ = true;
        }
        double result = value;

        ByteUnit unit = B;

        ByteUnit[] units = ByteUnit.values();
        int i = 0;

        while(i < units.length && !(value < 1024)) {
            value >>= 10;
            unit = units[++i];
            result /= 1024;
        }


        return (negativ? "-": "") + result + unit;
    }
}
