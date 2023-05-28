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
import javafx.util.Duration;

public class AnimatedMovable {
    protected boolean isIdle;
    protected byte acceleration;
    protected Point2D position;
    protected ImageView shape;
    protected Sprite sprites;
    protected double speedX;
    protected double speedY;
    protected boolean spriteReflected;

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
        this.spriteReflected = false;
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
        if(spriteReflected){
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
        // updateLocalization(prefix, speedX, speed);
        return this;
    }

    public AnimatedMovable moveToLeft(String prefix){
        setSpriteReflected(true);
        updateLocalization(prefix, true);
        return this;
    }

    public AnimatedMovable moveToRight(String prefix){
        setSpriteReflected(false);
        updateLocalization(prefix, false);
        return this;
    }

    public AnimatedMovable moveToUp(String prefix){
        // updateLocalization(prefix, moveX, (byte) -speed);
        return this;
    }

    public void playIdleAnimation() {
        setIdle(true);
        animateSprites("_Idle_", 17, 100);
    }

    protected void setIdle(boolean idle) {
        this.isIdle = idle;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public void setSpriteReflected(boolean spriteReflected) {
        this.spriteReflected = spriteReflected;
    }

    public AnimatedMovable stopMoveY(){
        setSpeedY(0);
        return this;
    }

    public AnimatedMovable stopMoveX(){
        setSpeedX(0);
        return this;
    }

    private AnimatedMovable updateLocalization(String prefix, boolean isLeft){
        Collision checkCollision = CollisionController.checkCollision(new BoundingBox(
            this.shape.getBoundsInLocal().getMinX()+acceleration, 
            this.shape.getBoundsInLocal().getMinY(), 
            this.shape.getBoundsInLocal().getWidth(), 
            this.shape.getBoundsInLocal().getHeight())
        );

        if(isIdle) {
            animateSprites(prefix, 11, 100);
            this.setIdle(false);
        }

        if((isLeft && checkCollision.getCheckLeft()) || (!isLeft && checkCollision.getCheckRight())){
            setSpeedX(0);
        } else if(speedX < 20){
            setSpeedX(speedX + acceleration);
        }

        if(isLeft){
            position = position.subtract(Math.min(speedX, checkCollision.getMinDistanceLeft()), 0);
        } else {
            position = position.add(Math.min(speedX, checkCollision.getMinDistanceRight()), 0);
        }
        this.shape.setX(position.getX());
        
        while(!checkCollision.getCheckDown()){
            checkCollision = CollisionController.checkCollision(new BoundingBox(
                this.shape.getBoundsInLocal().getMinX(), 
                this.shape.getBoundsInLocal().getMinY()+speedY, 
                this.shape.getBoundsInLocal().getWidth(), 
                this.shape.getBoundsInLocal().getHeight())
            );
            setSpeedY(speedY + (PhysicsVariables.GRAVITY));
            System.out.println("speed:" + speedY);
            position = position.add(0, Math.min(speedY, checkCollision.getMinDistanceDown()));
            System.out.println("Chegou aqui com Y = " + shape.getBoundsInLocal().getMaxY());
            if(!checkCollision.getCheckDown()) this.shape.setY(position.getY());
        }
        System.out.println("Posição X: " + shape.getBoundsInLocal().getMinX() + ". Posição Y: " + shape.getBoundsInLocal().getMinY());
        System.out.println("Shape: (" + shape.getBoundsInLocal().getCenterX() + "/" + shape.getBoundsInLocal().getMaxY() + ")");
        return this;
    }

    public void setSprites(){
        int  i = 0;
        for (i = 0; i <= 17; i++) {
            String spriteName = String.format(sprites.getPrefix() + "_Idle_%03d", i);
            Image image = new Image(getClass().getResourceAsStream("/com/project/game/sprites/player/Idle/" + spriteName + ".png"));
            sprites.addSprite(spriteName, image);
        }
        for (i = 0; i <= 11; i++) {
            String spriteName = String.format(sprites.getPrefix() + "_Running_%03d", i);
            Image image = new Image(getClass().getResourceAsStream("/com/project/game/sprites/player/Running/" + spriteName + ".png"));
            sprites.addSprite(spriteName, image);
        }
    }
}
