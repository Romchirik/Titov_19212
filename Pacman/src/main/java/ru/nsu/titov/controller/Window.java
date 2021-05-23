package ru.nsu.titov.controller;

import javafx.stage.Stage;

public class Window {
    public Stage window;
    private static Window instance;
    public static Window getInstance() {
        if(instance == null){
            instance = new Window();
        }
        return instance;
    }
}
