package com.project.game.controllers;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javafx.scene.image.ImageView;

public class EntitiesController {
    private static ArrayList<ImageView> entities = new ArrayList<>();

    public static void addEntity(ImageView entity) {
        entities.add(entity);
    }

    public static ArrayList<ImageView> getEntitiesWithXLessThan(double key) {
        // return entities.stream().filter(entity -> entity.getBoundsInLocal().getMinX() <= key).collect(Collectors.toCollection(ArrayList::new));
        return entities;
    }
    

}
