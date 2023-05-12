package com.project.game.controllers;

import com.project.game.models.Character;

import javafx.scene.input.KeyEvent;

public class KeyboardController {

    public static void keyboardHandlerPressed(KeyEvent event, Character player){
        switch (event.getCode()) {
            case UP: {
                player.moveToUp();
                if(player.getIsIdle()) player.setIdle(false);
                break;
            }
            case DOWN: {
                player.moveToDown();
                if(player.getIsIdle()) player.setIdle(false);
                break;
            }
            case LEFT: {
                player.moveToLeft();
                if(player.getIsIdle()) player.setIdle(false);
                break;
            }
            case RIGHT: {
                player.moveToRight();
                if(player.getIsIdle()) player.setIdle(false);
                break;
            }
        }
    }

    public static void keyboardHandlerReleased(KeyEvent event, Character player){
        switch (event.getCode()) {
            case UP:
            case DOWN:
            case LEFT:
            case RIGHT: {
                player.playIdleAnimation();
                break;
            }
        }
    }
}
