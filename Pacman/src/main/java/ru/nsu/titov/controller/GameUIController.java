package ru.nsu.titov.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.nsu.titov.model.Direction;
import ru.nsu.titov.model.Model;
import ru.nsu.titov.view.CustomPainter;
import ru.nsu.titov.view.UISettings;

import java.io.IOException;

public final class GameUIController {


    private final CustomPainter painter = new CustomPainter();
    public GridPane infoPanel;
    public Label currentLevel;
    public Canvas gameArea;
    public BorderPane root;

    private final Model model = Model.getInstance();


    Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(1000 / 60),
                    new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            update();
                        }
                    })
    );

    public GameUIController() {
        model.init();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @FXML
    public void handleKey(KeyEvent keyEvent) throws IOException {
        switch (keyEvent.getCode()){
            case LEFT -> model.setPacmanDirection(Direction.LEFT);
            case RIGHT-> model.setPacmanDirection(Direction.RIGHT);
            case UP -> model.setPacmanDirection(Direction.UP);
            case DOWN -> model.setPacmanDirection(Direction.DOWN);
            case ESCAPE -> {
                timeline.pause();
                Stage window = (Stage) ((Node) keyEvent.getSource()).getScene().getWindow();
                Parent start = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/start_screen.fxml"));
                Scene tmp = new Scene(start, UISettings.MAIN_WINDOW_WIDTH, UISettings.MAIN_WINDOW_HEIGHT);
                window.setScene(tmp);
            }
        }

    }

    void update() {
        //update model
        if (!model.tick()) {
            Platform.exit();
            timeline.stop();
        } else {
            GameSession.getInstance().currentScore = model.getCurrentScore();
        }

        //repaint

        GraphicsContext tmp = gameArea.getGraphicsContext2D();
        tmp.clearRect(0, 0, gameArea.getWidth(), gameArea.getHeight());
        //here we can filter objects to paint

        model.getActiveObjects().forEach((e, object) -> painter.paint(object, tmp));
    }

}
