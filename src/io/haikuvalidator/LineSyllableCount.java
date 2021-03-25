package io.haikuvalidator;

public enum LineSyllableCount {
    ONE(5), TWO(7), THREE(5);

    private final int syllableCount;

    LineSyllableCount(int syllableCount) {
        this.syllableCount = syllableCount;
    }

    public int syllableCount() {
        return syllableCount;
    }
}