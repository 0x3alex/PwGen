package de.alex.pwgen;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 350, 190);
        stage.setTitle("PwGen");
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(Application.class.getResourceAsStream("/pwgen.png"))));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}