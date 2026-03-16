package org.example.concertjavafx.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.concertjavafx.dao.UserDAO;
import org.example.concertjavafx.entity.User;
import java.io.IOException;

public class RegisterFrame {

    @FXML private TextField loginField;
    @FXML private PasswordField passField;
    @FXML private PasswordField confirmField;

    public void show(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/concertjavafx/register-view.fxml"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Реєстрація");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRegister() {
        if (loginField.getText().isEmpty() || !passField.getText().equals(confirmField.getText())) {
            new Alert(Alert.AlertType.WARNING, "Помилка вводу даних").showAndWait();
            return;
        }

        UserDAO dao = new UserDAO();
        User user = new User();
        user.setLogin(loginField.getText());
        user.setPassword(passField.getText());
        user.setRole("User");
        dao.save(user);

        new Alert(Alert.AlertType.INFORMATION, "Успішно!").showAndWait();
        ((Stage) loginField.getScene().getWindow()).close();
    }
}