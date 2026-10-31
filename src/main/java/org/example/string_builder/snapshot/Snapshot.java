package org.example.string_builder.snapshot;

import java.util.Arrays;

public class Snapshot {

    private final char [] string;
    private final int length;

    private Snapshot (char[] string, int length) {
        this.string = Arrays.copyOf(string, length);
        this.length = length;
    }

    public char[] getString() {
        return Arrays.copyOf(this.string, this.length);
    }

    public static Snapshot of (char[] string, int length) {
        return new Snapshot(string, length);
    }

    @Override
    public String toString() {
        return "Snapshot{" +
                "string=" + Arrays.toString(string) +
                '}';
    }
}
