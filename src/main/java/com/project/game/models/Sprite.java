package com.project.game.models;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class Sprite {
    private String currentSprite;
    private Map<String, Image> sprites;

    public Sprite(Map<String, Image> sprites){
        this.currentSprite = "";
        this.sprites = sprites;
    }

    public Sprite(){
        currentSprite =  "";
        sprites = new HashMap<>();
    }

    public Sprite addSprite(String key, Image img){
        sprites.put(key, img);
        return this;
    }

    public Image getSprite(String key){
        currentSprite = key;
        return sprites.get(key);
    }

    public Image getCurrentSprite(){
        if(currentSprite.length() == 0) return null;
        return sprites.get(currentSprite);
    }
}
