package ru.nsu;

public class Pair {
    int x1 = 0;
    int x2 = 0;

    public Pair(int val1, int val2) {
        x1 = val1;
        x2 = val2;
    }

    void setX1(int val) {
        x1 = val;
    }

    void setX2(int val) {
        x2 = val;
    }

    void setPair(int val1, int val2) {
        x1 = val1;
        x2 = val2;
    }

    int first() {
        return x1;
    }

    int second() {
        return x2;
    }
}
