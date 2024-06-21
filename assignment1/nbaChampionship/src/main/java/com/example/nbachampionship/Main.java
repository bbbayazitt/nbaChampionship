package com.example.nbachampionship;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/nbachampionship/view/PieChartView.fxml"));

            root.setStyle("-fx-padding: 20px;");

            stage.setTitle("NBA Championships");

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());

            stage.setFullScreen(true);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    private void handleIOException(IOException e) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error loading FXML", e);
    }

    public static void main(String[] args) {
        launch(args);
    }
}