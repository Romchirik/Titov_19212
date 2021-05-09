package ru.nsu.titov.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;
import ru.nsu.titov.view.UISettings;

import java.io.IOException;
import java.util.ArrayList;


public class StartScreenController {
    public Button startButton;
    public Button leaderboardButton;
    public Button exitButton;


    private final ArrayList<String> availableLevels = new ArrayList<>();
    public TextField playerNameSelector;
    public ChoiceBox<String> levelSelector;

    public StartScreenController() {
    }


    @FXML
    public void startGame(ActionEvent actionEvent) throws IOException {

        GameSession.getInstance().playerName = playerNameSelector.getCharacters().toString();
        System.out.println(GameSession.getInstance().playerName);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent game = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/game_screen.fxml"));
        Scene tmp = new Scene(game, UISettings.MAIN_WINDOW_WIDTH, UISettings.MAIN_WINDOW_HEIGHT);
        window.setScene(tmp);
    }

    @FXML
    public void exit() {
        GameSession.storeInfo();
        Platform.exit();
    }

    @FXML
    public void selectPlayer(ActionEvent actionEvent) {
    }

    @FXML
    public void showLeaderboard(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent game = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/leaderboard_screen.fxml"));
    }

    @FXML
    public void selectLevel(ActionEvent actionEvent) {

    }


    public void changePlayer(InputMethodEvent inputMethodEvent) {
        System.out.println(inputMethodEvent.getCommitted());
    }
}
