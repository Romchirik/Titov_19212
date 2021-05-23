package ru.nsu.titov.controller;

import org.apache.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;


public class GameSession {

    private static final Logger logger = Logger.getLogger(GameSession.class);

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

    public void storeInfo() {
        Properties props = new Properties();
        try {
            props.load(getClass().getClassLoader().getResourceAsStream(Settings.LEADERBOARD_FILE));
        } catch (IOException e) {
            logger.error("Unable to load leaderboard, leaderboard.properties missing");
            return;
        }

        if (props.containsKey(playerName)) {
            String score = props.getProperty(playerName);
            if (Integer.parseInt(score) < currentScore) {
                props.setProperty(playerName, Integer.toString(currentScore));
            }
        } else {
            props.setProperty(playerName, Integer.toString(currentScore));
        }

        try (OutputStream output = new FileOutputStream(getClass().getClassLoader().getResource(Settings.LEADERBOARD_FILE).getFile())) {
            props.store(output, "");
        } catch (IOException io) {
            io.printStackTrace();
        }

    }
}
