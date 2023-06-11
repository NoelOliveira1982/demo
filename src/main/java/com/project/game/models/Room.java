package com.project.game.models;

import java.util.UUID;

public class Room {
    public static byte maxPlayers = 2;
    private UUID id;
    private String name;
    private byte quantityPlayers;

    public Room(String name){
        this.id = UUID.randomUUID();
        this.name = name;
        this.quantityPlayers = 0;
    }

    public Room(){
        this.id = UUID.randomUUID();
        this.name =  "Room";
        this.quantityPlayers = 0;
    }

    @Override
    public String toString() {
        return "ID: " + id + ".\nROOM NAME: " + name + " (" + quantityPlayers + "/" + maxPlayers + ")";
    }
}
