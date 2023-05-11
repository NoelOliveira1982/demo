package com.project.game.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.project.game.models.Character;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

public class StageController implements Initializable {

    @FXML
    private ImageView player1Shape;

    private Character player1;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        player1 = new  Character("Teste", player1Shape);
        player1.playIdleAnimation();
    }
}
