package ru.nsu.titov.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import ru.nsu.titov.view.UISettings;

import java.io.IOException;
import java.util.Properties;


public class LeaderboardController {
    private static final Logger logger = Logger.getLogger(LeaderboardController.class);

    private ObservableList<String> items = FXCollections.observableArrayList();

    @FXML
    public ListView<String> table;

    public LeaderboardController() {
    }

    public void init() {
        Properties props = new Properties();
        try {
            props.load(getClass().getClassLoader().getResourceAsStream("leaderboard.properties"));
        } catch (IOException e) {
            logger.error("Unable to load leaderboard, leaderboard.properties missing");
        }

        props.forEach((key, value) -> {
            items.add(key + " " + value);
        });
        table.setItems(items);

    }

    @FXML
    public void handleKey(KeyEvent keyEvent) throws IOException {
        switch (keyEvent.getCode()) {
            case ESCAPE: {
                Stage window = (Stage) ((Node) keyEvent.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/start_screen.fxml"));
                AnchorPane view = loader.load();
                StartScreenController controller = (StartScreenController) loader.getController();
                controller.init();
                Scene tmp = new Scene(view, UISettings.MAIN_WINDOW_WIDTH, UISettings.MAIN_WINDOW_HEIGHT);

                window.setScene(tmp);
                break;
            }
        }
    }
}
