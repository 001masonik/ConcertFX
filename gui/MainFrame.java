package org.example.concertjavafx.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.concertjavafx.dao.SongDAO;
import org.example.concertjavafx.entity.Song;
import org.example.concertjavafx.entity.User;

import java.io.IOException;

public class MainFrame {

    @FXML private TableView<Song> songTable;
    @FXML private TableColumn<Song, String> nameCol;
    @FXML private TableColumn<Song, String> artistCol;
    @FXML private TableColumn<Song, Integer> votesCol;

    private final SongDAO songDAO = new SongDAO();
    private static User currentUser;

    // 1. Порожній конструктор — обов'язковий для FXML завантажувача
    public MainFrame() {}

    // 2. Конструктор для передачі користувача при вході
    public MainFrame(User user) {
        currentUser = user;
        show();
    }

    public void show() {
        try {
            // Переконайся, що шлях до .fxml файлу правильний
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/concertjavafx/main-view.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Концерт — " + (currentUser != null ? currentUser.getLogin() : "Гість"));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        // Налаштування колонок таблиці
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        artistCol.setCellValueFactory(new PropertyValueFactory<>("artist"));
        votesCol.setCellValueFactory(new PropertyValueFactory<>("voteCount"));

        refreshData();
    }

    @FXML
    private void refreshData() {
        // Завантаження актуальних даних з бази
        songTable.setItems(FXCollections.observableArrayList(songDAO.findAll()));
    }

    @FXML
    private void handleVote() {
        Song selected = songTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.incrementVoteCount();
            songDAO.save(selected);
            refreshData();
        }
    }
}