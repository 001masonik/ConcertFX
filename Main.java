package org.example.concertjavafx;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.concertjavafx.gui.LoginFrame;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Твій метод ініціалізації адміна тут
        new LoginFrame().show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}