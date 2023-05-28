package com.project.game.controllers;

import java.util.ArrayList;

import com.project.game.models.Collision;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

public class CollisionController {
    public static boolean isCollision(Point2D from, Point2D to){
        return  (from.getX() == to.getX() || from.getY() == to.getY());
    }

    public static boolean hasCollisionOnlyOneSide(double from, double to){
        return  (from >= to);
    }

    public static Collision checkCollision(Bounds playerBounds) {
        ArrayList<ImageView> entities = EntitiesController.getEntitiesWithXLessThan(playerBounds.getMaxX());
        Collision collisionCheck = new Collision();

        for(ImageView entity: entities){

            if(entity.intersects(playerBounds.getMinX(), playerBounds.getMaxY(), playerBounds.getWidth(), 1)){
                collisionCheck.setMinDistanceDown(Math.abs(entity.getBoundsInParent().getMinY() - playerBounds.getMaxY()));
                collisionCheck.setCheckDown(true);
            }
            if(entity.intersects(playerBounds.getMinX(), playerBounds.getMinY(), playerBounds.getWidth(), 1)){
                collisionCheck.setMinDistanceUp(Math.abs(playerBounds.getMinY() - entity.getBoundsInParent().getMaxY()));
                collisionCheck.setCheckUp(true);
            }
            if(entity.intersects(playerBounds.getMinX(), playerBounds.getMinY(), 1, playerBounds.getHeight())){
                collisionCheck.setMinDistanceLeft(Math.abs(playerBounds.getMinX() - entity.getBoundsInParent().getMaxX()));
                collisionCheck.setCheckLeft(true);
            }
            if(entity.intersects(playerBounds.getMaxX(), playerBounds.getMinY(), 1, playerBounds.getHeight())){
                collisionCheck.setMinDistanceRight(Math.abs(entity.getBoundsInParent().getMinX() - playerBounds.getMaxX()));
                collisionCheck.setCheckRight(true);
            }
        }
        return collisionCheck;
    }
}
