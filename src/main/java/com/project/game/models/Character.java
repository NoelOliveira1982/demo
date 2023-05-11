package com.project.game.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Character {

    public static String prefixSprite = "0_Reaper_Man";

    private UUID id;
    private String name;
    private byte life;
    private byte strength;
    private byte resistance;
    private byte speed;
    private Point2D position;
    private ImageView shape;
    public Sprite sprites;

    public Character(String name, ImageView shape){
        this.id = UUID.randomUUID();
        this.name = name;
        this.life = 100;
        this.strength = 30;
        this.speed = 20;
        this.resistance = 10;
        this.sprites = new Sprite();
        setSprites();
        this.position = new Point2D(100, 100);
        this.shape = shape;
        this.shape.setX(position.getX());
        this.shape.setY(position.getY());
        this.shape.setFitHeight(256);
        this.shape.setFitWidth(256);
    }

    public void animateSprites(String prefix, int numSprites, int duration) {

        List<KeyFrame> frames = new ArrayList<>();
        for (int i = 0; i <= numSprites; i++) {
            String spriteName = prefixSprite + prefix + String.format("%03d", i);
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
        Timeline timeline = new Timeline(frames.toArray(new KeyFrame[0]));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    

    public Character beat(Character enemy){
        if(!Collision.isCollision(this.position, enemy.position)) return enemy;
        return enemy.getBeaten(this.strength);
    }

    public Character getBeaten(byte damage){
        this.life -= (byte) (this.resistance - damage);
        return this;
    }

    public UUID getId() {
        return id;
    }

    public byte getLife() {
        return life;
    }

    public String getName() {
        return name;
    }

    public Point2D getPosition() {
        return position;
    }

    public Character moveToDown(){
        position.add(0, -speed);
        shape.setY(position.getY());
        playIdleAnimation();
        return this;
    }

    public Character moveToLeft(){
        position.add(-speed, 0);
        shape.setX(position.getX());
        playIdleAnimation();
        return this;
    }

    public Character moveToRight(){
        position.add(speed, 0);
        shape.setX(position.getX());
        playIdleAnimation();
        return this;
    }

    public Character moveToUp(){
        position.add(0, speed);
        shape.setY(position.getY());
        playIdleAnimation();
        return this;
    }

    public void playIdleAnimation() {
        animateSprites("_Idle_", 17, 100);
    }    

    public Character regenerate(byte life){
        this.life+= life;
        return  this;
    }

    public void setSprites(){
        for (int i = 0; i <= 17; i++) {
            String spriteName = String.format(prefixSprite + "_Idle_%03d", i);
            Image image = new Image(getClass().getResourceAsStream("/com/project/game/sprites/player/Idle/" + spriteName + ".png"));
            sprites.addSprite(spriteName, image);
        }
    }
}
