package com.project.game.models;

import java.util.ArrayList;
import java.util.List;

import com.project.game.controllers.CollisionController;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;


public class AnimatedMovable {
    protected boolean isIdle;
    protected byte acceleration;
    protected Point2D position;
    protected ImageView shape;
    protected Sprite sprites;
    protected double speedX;
    protected double speedY;
    protected boolean moveLeft;
    protected boolean moveUp;

    public AnimatedMovable(byte acceleration, Point2D position, ImageView shape, Sprite sprites){
        this.acceleration = acceleration;
        this.isIdle = true;
        this.position = position;
        this.shape = shape;
        this.shape.setX(position.getX());
        this.shape.setY(position.getY());
        this.sprites = sprites;
        this.speedX = 0;
        this.speedY = 0;
        this.moveLeft = false;
        this.moveUp = false;
        setSprites();
    }

    public void animateSprites(String prefix, int numSprites, int duration) {
        List<KeyFrame> frames = new ArrayList<>();
        for (int i = 0; i <= numSprites; i++) {
            String spriteName = sprites.getPrefix() + prefix + String.format("%03d", i);
            Image spriteImage = sprites.getSprite(spriteName);
    
            if (spriteImage != null) {
                Runnable updateImage = () -> shape.setImage(spriteImage);
                Platform.runLater(updateImage);
                KeyValue imageValue = new KeyValue(shape.imageProperty(), spriteImage);
                KeyFrame frame = new KeyFrame(Duration.millis((i + 1) * duration), imageValue);
    
                frames.add(frame);
            }
        }
        shape.setImage(sprites.getCurrentSprite());
        if(moveLeft){
            shape.setScaleX(-1);
        } else {
            shape.setScaleX(1);
        }
        Timeline timeline = new Timeline(frames.toArray(new KeyFrame[0]));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public boolean getIsIdle(){
        return isIdle;
    }

    public AnimatedMovable moveToDown(String prefix){
        updateLocalization(prefix, KeyCode.DOWN);
        return this;
    }

    public AnimatedMovable moveToLeft(String prefix){
        setMoveLeft(true);
        updateLocalization(prefix, KeyCode.LEFT);
        return this;
    }

    public AnimatedMovable moveToRight(String prefix){
        setMoveLeft(false);
        updateLocalization(prefix, KeyCode.RIGHT);
        return this;
    }

    public AnimatedMovable moveToUp(String prefix){
        updateLocalization(prefix, KeyCode.UP);
        return this;
    }

    public void playIdleAnimation() {
        setIdle(true);
        animateSprites("_Idle_", 17, 100);
    }

    protected void setIdle(boolean idle) {
        this.isIdle = idle;
    }

    public void setMoveUp(boolean moveUp) {
        this.moveUp = moveUp;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public AnimatedMovable stopMoveY(){
        setSpeedY(0);
        return this;
    }

    public AnimatedMovable stopMoveX(){
        setSpeedX(0);
        return this;
    }

    private AnimatedMovable updateLocalization(String prefix, KeyCode direction){
        Collision checkCollision = CollisionController.checkCollision(new BoundingBox(
            this.shape.getBoundsInLocal().getMinX() + (direction == KeyCode.LEFT ? acceleration : direction == KeyCode.RIGHT ? (-acceleration) : 0), 
            this.shape.getBoundsInLocal().getMinY() + (direction == KeyCode.DOWN ? acceleration : direction == KeyCode.UP ? (-acceleration) : 0), 
            this.shape.getBoundsInLocal().getWidth(), 
            this.shape.getBoundsInLocal().getHeight())
        );

        if(isIdle) {
            animateSprites(prefix, 11, 100);
            this.setIdle(false);
        }

        if((direction == KeyCode.LEFT && checkCollision.getCheckLeft()) || (direction == KeyCode.RIGHT && checkCollision.getCheckRight())){
            setSpeedX(0);
        } else if(speedX < 20){
            setSpeedX(speedX + acceleration);
        }

        if((direction == KeyCode.UP && checkCollision.getCheckUp()) || (direction == KeyCode.DOWN && checkCollision.getCheckDown())) {
            setSpeedY(0);
        } else if(speedY < 20){
            setSpeedY(speedY + acceleration);
        }

        if(direction == KeyCode.LEFT){
            position = position.subtract(Math.min(speedX, checkCollision.getMinDistanceLeft()), 0);
        } else if (direction == KeyCode.RIGHT) {
            position = position.add(Math.min(speedX, checkCollision.getMinDistanceRight()), 0);
        }

        if(direction == KeyCode.UP) {
            position = position.subtract(0, Math.min(speedY, checkCollision.getMinDistanceDown()));
        } else if(direction == KeyCode.DOWN){
            position = position.add(0, Math.min(speedY, checkCollision.getMinDistanceDown()));
        }
        
        this.shape.setX(position.getX());
        this.shape.setY(position.getY());
        
        return this;
    }

    public void setSprites(){
        int  i = 0;
        for (i = 0; i <= 17; i++) {
            String spriteName = String.format(sprites.getPrefix() + "_Idle_%03d", i);
            Image image = new Image(getClass().getResourceAsStream("/com/project/game/sprites/" + sprites.getFolder() + "/Idle/" + spriteName + ".png"));
            sprites.addSprite(spriteName, image);
        }
        for (i = 0; i <= 11; i++) {
            String spriteName = String.format(sprites.getPrefix() + "_Running_%03d", i);
            Image image = new Image(getClass().getResourceAsStream("/com/project/game/sprites/" + sprites.getFolder() + "/Running/" + spriteName + ".png"));
            sprites.addSprite(spriteName, image);
        }
    }
}
