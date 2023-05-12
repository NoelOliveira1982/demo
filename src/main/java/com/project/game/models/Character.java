package com.project.game.models;

import java.util.UUID;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Character extends AnimatedMovable  {

    public static String prefixSprite = "0_Reaper_Man";

    private UUID id;
    private String name;
    private byte life;
    private byte strength;
    private byte resistance;

    public Character(String name, ImageView shape, byte speed, Point2D position, Sprite sprites, byte resistance, byte strength, byte life){
        super(speed, position, shape, sprites);
        this.id = UUID.randomUUID();
        this.name = name;
        this.life = life;
        this.strength = strength;
        this.resistance = resistance;
        setSprites();
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

    public Character regenerate(byte life){
        this.life+= life;
        return  this;
    }

    public void setIdle(boolean isIdle) {
        this.isIdle = isIdle;
    }

    public void setSprites(){
        int  i = 0;
        for (i = 0; i <= 17; i++) {
            String spriteName = String.format(prefixSprite + "_Idle_%03d", i);
            Image image = new Image(getClass().getResourceAsStream("/com/project/game/sprites/player/Idle/" + spriteName + ".png"));
            sprites.addSprite(spriteName, image);
        }
        for (i = 0; i <= 11; i++) {
            String spriteName = String.format(prefixSprite + "_Running_%03d", i);
            Image image = new Image(getClass().getResourceAsStream("/com/project/game/sprites/player/Running/" + spriteName + ".png"));
            sprites.addSprite(spriteName, image);
        }
    }
}
