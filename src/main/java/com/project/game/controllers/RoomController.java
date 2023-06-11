package com.project.game.controllers;

import java.util.ArrayList;

import com.project.game.models.Room;

public class RoomController {
    private static ArrayList<Room> rooms = new ArrayList<>();

    public static void addRoom(){
        rooms.add(new Room());
    }

    public static void addRoom(String name){
        rooms.add(new Room(name));
    }

    public static ArrayList<Room> getRooms(){
        return new ArrayList<>(rooms);
    }
}
