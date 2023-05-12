package com.project.game.controllers;

import com.project.game.models.Character;

import javafx.scene.input.KeyEvent;

public class KeyboardController {

    public static void keyboardHandlerPressed(KeyEvent event, Character player){
        switch (event.getCode()) {
            case UP: {
                player.moveToUp("Running_");
                break;
            }
            case DOWN: {
                player.moveToDown("Running_");
                break;
            }
            case LEFT: {
                player.moveToLeft("Running_");
                break;
            }
            case RIGHT: {
                player.moveToRight("Running_");
                break;
            }
        }
    }

    public static void keyboardHandlerReleased(KeyEvent event, Character player){
        switch (event.getCode()) {
            case UP:
            case DOWN: {
                player.playIdleAnimation();
                player.stopMoveY();
                break;
            }
            case LEFT:
            case RIGHT: {
                player.playIdleAnimation();
                player.stopMoveX();
                break;
            }
        }
    }
}
