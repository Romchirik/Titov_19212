package ru.nsu.titov.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.nsu.titov.view.UISettings;


public final class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent a = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/start_screen.fxml"));
        stage.setTitle("Pacman");
        Scene b = new Scene(a, UISettings.MAIN_WINDOW_WIDTH, UISettings.MAIN_WINDOW_HEIGHT);

        stage.setScene(b);
        stage.show();
    }

}
