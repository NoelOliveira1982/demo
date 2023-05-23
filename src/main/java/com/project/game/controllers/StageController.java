package com.project.game.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import com.project.game.models.Character;
import com.project.game.models.CharacterConstructor;
import com.project.game.models.Sprite;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class StageController implements Initializable {

    @FXML
    private ImageView player1Shape;
    @FXML
    private ImageView ground;
    @FXML
    private ImageView ground2;
    @FXML
    private ImageView ground3;
    @FXML
    private ImageView ground4;

    private Character player1;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ground.setImage(new Image(getClass().getResourceAsStream("/com/project/game/sprites/scenario/ground.png")));
        ground.setY(460);
        ground.setX(0);
        ground.setFitWidth(230);
        ground.setFitHeight(30);
        ground2.setImage(new Image(getClass().getResourceAsStream("/com/project/game/sprites/scenario/ground.png")));
        ground2.setY(460);
        ground2.setX(230);
        ground2.setFitWidth(40);
        ground2.setFitHeight(30);
        ground3.setImage(new Image(getClass().getResourceAsStream("/com/project/game/sprites/scenario/ground.png")));
        ground3.setY(350);
        ground3.setX(270);
        ground3.setFitWidth(640);
        ground3.setFitHeight(30);
        EntitiesController.addEntity(ground);
        EntitiesController.addEntity(ground2);
        EntitiesController.addEntity(ground3);
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
        player1Shape.setPreserveRatio(false);
        player1Shape.setFitHeight(130);
        player1Shape.setFitWidth(115);

        player1 = new CharacterConstructor()
            .withName("Jorge")
            .withLife((byte) 100)
            .withPosition(new  Point2D(0, 0))
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