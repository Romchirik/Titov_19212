package ru.nsu.titov.controller;

import ru.nsu.titov.model.ModelController;
import ru.nsu.titov.view.UIController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainController implements ActionListener {


    Timer timer = new Timer(1000 / 60, this);
    ModelController model = new ModelController();
    UIController ui = new UIController(this);

    AppState currentAppState = AppState.START;

    private int modelTicksPassed = 0;
    private boolean gameStarted = false;

    public MainController() throws IOException {

    }

    public void startGame() {
        gameStarted = true;
    }

    public void stopGame() {
        gameStarted = false;
    }

    public void resetGame() {
        model.reset();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (currentAppState) {
            case GAME -> updateGameField();
            default -> {
            }
        }
    }

    public void setMode(AppState state) {
        this.currentAppState = state;
        ui.setPanel(state.toString());
    }

    public void exit() {
        System.exit(0);
    }


    private void updateGameField() {
        modelTicksPassed++;
        if (modelTicksPassed > Settings.FPS) {
            model.tick();
            modelTicksPassed = 0;
        }
        ui.repaintGameField(model, modelTicksPassed);
    }
}

