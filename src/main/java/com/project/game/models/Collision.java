package com.project.game.models;

public class Collision {
    private boolean checkUp;
    private boolean checkDown;
    private boolean checkLeft;
    private boolean checkRight;
    private double minDistanceUp;
    private double minDistanceDown;
    private double minDistanceRight;
    private double minDistanceLeft;

    public Collision(){
        checkDown = false;
        checkLeft = false;
        checkRight = false;
        checkUp = false;
        minDistanceUp = Double.POSITIVE_INFINITY;
        minDistanceDown = Double.POSITIVE_INFINITY;
        minDistanceLeft = Double.POSITIVE_INFINITY;
        minDistanceRight = Double.POSITIVE_INFINITY;
    }

    public double getMinDistanceDown() {
        return minDistanceDown;
    }

    public double getMinDistanceLeft() {
        return minDistanceLeft;
    }

    public double getMinDistanceRight() {
        return minDistanceRight;
    }

    public double getMinDistanceUp() {
        return minDistanceUp;
    }

    public boolean getCheckDown() {
        return this.checkDown;
    }

    public boolean getCheckLeft() {
        return this.checkLeft;
    }

    public boolean getCheckRight() {
        return this.checkRight;
    }

    public boolean getCheckUp() {
        return this.checkUp;
    }

    public void setMinDistanceDown(double minDistanceDown) {
        this.minDistanceDown = minDistanceDown;
    }

    public void setMinDistanceLeft(double minDistanceLeft) {
        this.minDistanceLeft = minDistanceLeft;
    }

    public void setMinDistanceRight(double minDistanceRight) {
        this.minDistanceRight = minDistanceRight;
    }

    public void setMinDistanceUp(double minDistanceUp) {
        this.minDistanceUp = minDistanceUp;
    }

    public void setCheckDown(boolean checkDown) {
        this.checkDown = checkDown;
    }

    public void setCheckLeft(boolean checkLeft) {
        this.checkLeft = checkLeft;
    }

    public void setCheckRight(boolean checkRight) {
        this.checkRight = checkRight;
    }

    public void setCheckUp(boolean checkUp) {
        this.checkUp = checkUp;
    }
}
