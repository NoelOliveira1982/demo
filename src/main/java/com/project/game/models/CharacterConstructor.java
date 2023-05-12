package com.project.game.models;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

public class CharacterConstructor {
    private String name;
    private byte life;
    private byte strength;
    private byte resistance;
    protected byte speed;
    protected Point2D position;
    protected ImageView shape;
    protected Sprite sprites;

    public CharacterConstructor withName(String name){
        this.name = name;
        return this;
    }
    
    public CharacterConstructor withLife(byte life){
        this.life = life;
        return this;
    }
    
    public CharacterConstructor withStrength(byte strength){
        this.strength = strength;
        return this;
    }
    
    public CharacterConstructor withResistance(byte resistance){
        this.resistance = resistance;
        return this;
    }
    
    public CharacterConstructor withSpeed(byte speed){
        this.speed = speed;
        return this;
    }
    
    public CharacterConstructor withPosition(Point2D position){
        this.position = position;
        return this;
    }
    
    public CharacterConstructor withShape(ImageView shape){
        this.shape = shape;
        return this;
    }
    
    public CharacterConstructor withSprites(Sprite sprites){
        this.sprites = sprites;
        return this;
    }
    
    public Character build(){
        return new Character(name, shape, speed, position, sprites);
    }
}

