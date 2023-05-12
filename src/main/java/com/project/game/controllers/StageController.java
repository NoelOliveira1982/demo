package com.project.game.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.project.game.models.Character;
import com.project.game.models.CharacterConstructor;
import com.project.game.models.Sprite;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class StageController implements Initializable {

    @FXML
    private ImageView player1Shape;

    private Character player1;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        createPlayer();
        player1.playIdleAnimation();
    }

    public void controlPlayerPressed(KeyEvent event) {
        KeyboardController.keyboardHandlerPressed(event, player1);
    }

    public void controlPlayerReleased(KeyEvent event){
        KeyboardController.keyboardHandlerReleased(event, player1);
    }

    public void createPlayer(){
        player1Shape.setFitHeight(256);
        player1Shape.setFitWidth(256);

        player1 = new CharacterConstructor()
            .withName("Jorge")
            .withLife((byte) 100)
            .withPosition(new  Point2D(100, 100))
            .withResistance((byte) 30).withShape(player1Shape)
            .withSpeed((byte) 5)
            .withStrength((byte) 30)
            .withSprites(new  Sprite("0_Reaper_Man"))
            .build();
    }
}


/*
 * this.position = new Point2D(100, 100);
        this.shape = shape;
        
        this.shape.setFitHeight(256);
        this.shape.setFitWidth(256);
 * 
 */