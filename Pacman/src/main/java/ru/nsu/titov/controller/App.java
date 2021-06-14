package ru.nsu.titov.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.nsu.titov.view.UISettings;


public final class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Window.getInstance().window = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/start_screen.fxml"));
        stage.setTitle("Pacman");
        AnchorPane view = loader.load();
        StartScreenController controller = (StartScreenController) loader.getController();
        controller.init();
        Scene tmp = new Scene(view, UISettings.MAIN_WINDOW_WIDTH, UISettings.MAIN_WINDOW_HEIGHT);

        stage.setScene(tmp);
        stage.show();
    }
}
