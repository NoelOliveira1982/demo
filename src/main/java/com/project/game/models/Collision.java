package com.project.game.models;

import javafx.geometry.Point2D;

public class Collision {
    
    public static boolean isCollision(Point2D from, Point2D to){
        return  (from.getX() == to.getX() && from.getY() == to.getY());
    }
}
