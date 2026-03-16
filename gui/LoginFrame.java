package org.example.concertjavafx.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.concertjavafx.dao.UserDAO;
import org.example.concertjavafx.entity.User;
import java.io.IOException;

public class LoginFrame {

    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;

    public void show(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/concertjavafx/login-frame.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Вхід");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogin() {
        UserDAO dao = new UserDAO();
        User user = dao.findByCredentials(loginField.getText(), passwordField.getText());
        if (user != null) {
            Stage currentStage = (Stage) loginField.getScene().getWindow();
            currentStage.close();
            new MainFrame(user);
        } else {
            new Alert(Alert.AlertType.ERROR, "Невірний логін або пароль").showAndWait();
        }
    }

    @FXML
    private void handleOpenRegister() {
        new RegisterFrame().show(new Stage());
    }
}