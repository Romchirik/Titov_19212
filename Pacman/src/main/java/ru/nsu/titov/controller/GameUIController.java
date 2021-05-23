package ru.nsu.titov.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
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
    public Canvas gameArea;
    public BorderPane root;

    private final Model model = Model.getInstance();
    public Label score;
    public Label lives;
    public Label level;

    Timeline ghostsFlipTimer = new Timeline(
            new KeyFrame(Duration.millis(Settings.GHOSTS_FLIP_TIME * 1000),
                    event -> model.flipScatterChase())
    );
    Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(1000 / Settings.FPS),
                    event -> update())
    );

    public GameUIController() {
        model.init();
        ghostsFlipTimer.setCycleCount(Animation.INDEFINITE);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        ghostsFlipTimer.play();
    }

    @FXML
    public void handleKey(KeyEvent keyEvent) throws IOException {

        switch (keyEvent.getCode()) {
            case LEFT -> model.setPacmanDirection(Direction.LEFT);
            case RIGHT -> model.setPacmanDirection(Direction.RIGHT);
            case UP -> model.setPacmanDirection(Direction.UP);
            case DOWN -> model.setPacmanDirection(Direction.DOWN);
            case ESCAPE -> {
                timeline.stop();
                ghostsFlipTimer.stop();
                Stage window = (Stage) ((Node) keyEvent.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/start_screen.fxml"));
                AnchorPane view = loader.load();
                StartScreenController controller = (StartScreenController) loader.getController();
                controller.init();
                Scene tmp = new Scene(view, UISettings.MAIN_WINDOW_WIDTH, UISettings.MAIN_WINDOW_HEIGHT);

                window.setScene(tmp);
            }
        }

    }

    void update() {
        //update model
        if (!model.tick()) {
            ghostsFlipTimer.stop();
            timeline.stop();
            Stage window = Window.getInstance().window;
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/start_screen.fxml"));
            AnchorPane view = null;
            try {
                view = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            StartScreenController controller = (StartScreenController) loader.getController();
            controller.init();
            Scene tmp = new Scene(view, UISettings.MAIN_WINDOW_WIDTH, UISettings.MAIN_WINDOW_HEIGHT);

            window.setScene(tmp);
        } else {
            GameSession.getInstance().currentScore = model.getCurrentScore();
        }

        //repaint
        GraphicsContext tmp = gameArea.getGraphicsContext2D();
        tmp.clearRect(0, 0, gameArea.getWidth(), gameArea.getHeight());

        //here we can filter objects to paint
        model.getActiveObjects().forEach((e, object) -> painter.paint(object, tmp));

        level.setText(GameSession.getInstance().levelName);
        lives.setText("Lives: " + model.getPacmanLives());
        score.setText("Score: " + model.getCurrentScore());
    }

}
