package ru.nsu.titov.model;

public class UniqueId {
    static private int id = 0;

    static public int getId() {
        return id++;
    }
}
