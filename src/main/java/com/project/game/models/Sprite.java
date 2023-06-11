package com.project.game.models;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class Sprite {
    private String currentSprite;
    private String prefix;
    private String folder;
    private Map<String, Image> sprites;

    public Sprite(Map<String, Image> sprites, String prefix, String folder){
        this.currentSprite = "";
        this.sprites = sprites;
        this.prefix = prefix;
        this.folder = folder;
    }

    public Sprite(String prefix, String folder){
        this.currentSprite = "";
        this.sprites = new HashMap<>();
        this.prefix = prefix;
        this.folder = folder;
    }

    public Sprite(){
        currentSprite =  "";
        sprites = new HashMap<>();
    }

    public Sprite addSprite(String key, Image img){
        sprites.put(key, img);
        return this;
    }

    public String getFolder() {
        return folder;
    }

    public Image getSprite(String key){
        currentSprite = key;
        return sprites.get(key);
    }

    public Image getCurrentSprite(){
        if(currentSprite.length() == 0) return null;
        return sprites.get(currentSprite);
    }

    public String getPrefix() {
        return prefix;
    }
}
