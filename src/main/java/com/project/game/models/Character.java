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
    private byte speed;
    private Point2D position;
    private ImageView shape;
    private Sprite sprites;

    public Character(String name){
        this.id = UUID.randomUUID();
        this.name = name;
        this.life = 100;
        this.strength = 30;
        this.speed = 20;
        this.resistance = 10;
        this.sprites = new Sprite();
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
        return this;
    }

    public Character moveToLeft(){
        position.add(-speed, 0);
        shape.setX(position.getX());
        return this;
    }

    public Character moveToRight(){
        position.add(speed, 0);
        shape.setX(position.getX());
        return this;
    }

    public Character moveToUp(){
        position.add(0, speed);
        shape.setY(position.getY());
        return this;
    }

    public Character regenerate(byte life){
        this.life+= life;
        return  this;
    }
}
