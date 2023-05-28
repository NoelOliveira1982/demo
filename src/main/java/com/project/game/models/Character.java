package com.project.game.models;

import java.util.UUID;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

public class Character {

    private UUID id;
    private String name;
    private byte life;
    private byte strength;
    private byte resistance;
    private AnimatedMovable shape;

    public Character(String name, ImageView shape, byte speed, Point2D position, Sprite sprites, byte resistance, byte strength, byte life){
        this.shape = new AnimatedMovable(speed, position, shape, sprites);
        this.id = UUID.randomUUID();
        this.name = name;
        this.life = life;
        this.strength = strength;
        this.resistance = resistance;
    }
    
    

    public Character beat(Character enemy){
        // if(!Collision.isCollision(this.position, enemy.position)) return enemy;
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
        return shape.position;
    }

    public Character regenerate(byte life){
        this.life+= life;
        return  this;
    }

    public void setIdle(boolean isIdle) {
        this.shape.isIdle = isIdle;
    }



    public void moveToUp(String string) {
        shape.moveToUp(string);
    }



    public void moveToDown(String string) {
        shape.moveToDown(string);
    }



    public void moveToLeft(String string) {
        shape.moveToLeft(string);
    }



    public void moveToRight(String string) {
        shape.moveToRight(string);
    }



    public void playIdleAnimation() {
        shape.playIdleAnimation();
    }



    public void stopMoveY() {
        shape.stopMoveY();
    }



    public void stopMoveX() {
        shape.stopMoveX();
    }


}
