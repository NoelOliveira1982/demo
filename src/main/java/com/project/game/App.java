package com.project.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.project.game.controllers.StageController;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = loadFXML("stage");
        scene = new Scene(loader.load(), 640, 480);
        StageController stageController = loader.getController();
        scene.setOnKeyPressed(event -> stageController.controlPlayerPressed(event));
        scene.setOnKeyReleased(event -> stageController.controlPlayerReleased(event));
        stage.setScene(scene);
        stage.show();
    }

    public static double getAppHeight(){
        return scene.getHeight();
    }

    public static double getAppWidth(){
        return scene.getWidth();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml).load());
    }

    private static FXMLLoader loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader;
    }

    public static void main(String[] args) {
        launch();
    }

}