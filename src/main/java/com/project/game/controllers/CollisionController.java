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
            System.out.println("-----------------------------------------------------");
            System.out.println(entity.getId());
            System.out.println("X: " + playerBounds.getMinX() + "/" + playerBounds.getMaxX());
            System.out.println("Y: " + playerBounds.getMinY() + "/" + playerBounds.getMaxY());

            if(entity.intersects(playerBounds.getMinX(), playerBounds.getMaxY(), playerBounds.getWidth(), 1)){
                collisionCheck.setMinDistanceDown(Math.abs(entity.getBoundsInParent().getMinY() - playerBounds.getMaxY()));
                collisionCheck.setCheckDown(true);
                System.out.println("----------------------------------------------------------------");
                System.out.println("Colidiu embaixo com distancia Y: " + collisionCheck.getMinDistanceDown());
                System.out.println("----------------------------------------------------------------");
            }
            if(entity.intersects(playerBounds.getMinX(), playerBounds.getMinY(), playerBounds.getWidth(), 1)){
                System.out.println("----------------------------------------------------------------");
                System.out.println("Colidiu em cima com X: " + entity.getBoundsInLocal().getMinX() + "/" + entity.getBoundsInLocal().getMaxX());
                System.out.println("Colidiu em cima com Y: " + entity.getBoundsInLocal().getMinY() + "/" + entity.getBoundsInLocal().getMaxY());
                System.out.println("----------------------------------------------------------------");
                collisionCheck.setMinDistanceUp(Math.abs(playerBounds.getMinY() - entity.getBoundsInParent().getMaxY()));
                collisionCheck.setCheckUp(true);
            }
            if(entity.intersects(playerBounds.getMinX(), playerBounds.getMinY(), 1, playerBounds.getHeight())){
                System.out.println("----------------------------------------------------------------");
                System.out.println("Colidiu esquerda com X: " + entity.getBoundsInLocal().getMinX() + "/" + entity.getBoundsInLocal().getMaxX());
                System.out.println("Colidiu esquerda com Y: " + entity.getBoundsInLocal().getMinY() + "/" + entity.getBoundsInLocal().getMaxY());
                System.out.println("----------------------------------------------------------------");
                collisionCheck.setMinDistanceLeft(Math.abs(playerBounds.getMinX() - entity.getBoundsInParent().getMaxX()));
                collisionCheck.setCheckLeft(true);
            }
            if(entity.intersects(playerBounds.getMaxX(), playerBounds.getMinY(), 1, playerBounds.getHeight())){
                System.out.println("----------------------------------------------------------------");
                System.out.println("Colidiu direita com X: " + entity.getBoundsInLocal().getMinX() + "/" + entity.getBoundsInLocal().getMaxX());
                System.out.println("Colidiu direita com Y: " + entity.getBoundsInLocal().getMinY() + "/" + entity.getBoundsInLocal().getMaxY());
                System.out.println("----------------------------------------------------------------");
                collisionCheck.setMinDistanceRight(Math.abs(entity.getBoundsInParent().getMinX() - playerBounds.getMaxX()));
                collisionCheck.setCheckRight(true);
            }
        }
        return collisionCheck;
    }
}
