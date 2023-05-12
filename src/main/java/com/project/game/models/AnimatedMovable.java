package com.project.game.models;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public abstract class AnimatedMovable {
    protected boolean isIdle;
    protected byte speed;
    protected Point2D position;
    protected ImageView shape;
    protected Sprite sprites;
    protected byte moveX;
    protected byte moveY;
    protected boolean spriteReflected;

    public AnimatedMovable(byte speed, Point2D position, ImageView shape, Sprite sprites){
        this.speed = speed;
        this.isIdle = true;
        this.position = position;
        this.shape = shape;
        this.shape.setX(position.getX());
        this.shape.setY(position.getY());
        this.sprites = sprites;
        this.moveX = 0;
        this.moveY = 0;
        this.spriteReflected = false;
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
        setMoveY(speed);
        if(isIdle) {
            animateSprites(prefix, 11, 100);
            this.setIdle(false);
        }
        position = position.add(moveX, moveY);
        this.shape.setX(position.getX());
        this.shape.setY(position.getY());
        return this;
    }

    public AnimatedMovable moveToLeft(String prefix){
        setMoveX((byte) -speed);
        setSpriteReflected(true);
        if(isIdle) {
            animateSprites(prefix, 11, 100);
            this.setIdle(false);
        }
        position = position.add(moveX, moveY);
        this.shape.setX(position.getX());
        this.shape.setY(position.getY());
        return this;
    }

    public AnimatedMovable moveToRight(String prefix){
        setMoveX(speed);
        setSpriteReflected(false);
        if(isIdle) {
            animateSprites(prefix, 11, 100);
            this.setIdle(false);
        }
        position = position.add(moveX, moveY);
        this.shape.setX(position.getX());
        this.shape.setY(position.getY());
        return this;
    }

    public AnimatedMovable moveToUp(String prefix){
        setMoveY((byte) -speed);
        if(isIdle) {
            animateSprites(prefix, 11, 100);
            this.setIdle(false);
        }
        position = position.add(moveX, moveY);
        this.shape.setX(position.getX());
        this.shape.setY(position.getY());
        return this;
    }

    public void playIdleAnimation() {
        setIdle(true);
        animateSprites("Idle_", 17, 100);
    }

    protected void setIdle(boolean idle) {
        this.isIdle = idle;
    }

    public void setMoveX(byte moveX) {
        this.moveX = moveX;
    }

    public void setMoveY(byte moveY) {
        this.moveY = moveY;

    }

    public void setSpriteReflected(boolean spriteReflected) {
        this.spriteReflected = spriteReflected;
    }

    public AnimatedMovable stopMoveY(){
        setMoveY((byte) 0);
        return this;
    }

    public AnimatedMovable stopMoveX(){
        setMoveX((byte) 0);
        return this;
    }

    public abstract void setSprites();
}
