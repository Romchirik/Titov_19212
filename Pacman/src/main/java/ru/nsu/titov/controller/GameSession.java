package ru.nsu.titov.controller;

public class GameSession {

    public String levelName = "default_map";
    public String playerName = "Player";
    public int currentScore = 0;
    public int maxScore = 0;

    private static GameSession instance = null;

    public static GameSession getInstance() {
        if (instance == null) {
            instance = new GameSession();
        }
        return instance;
    }

    public static void loadInfo(String playerName){

    }

    public static void storeInfo(){

    }
}
