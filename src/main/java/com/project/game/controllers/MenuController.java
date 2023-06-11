package com.project.game.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.project.game.models.Room;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class MenuController implements Initializable{

    @FXML
    private ListView<Room> roomList;
    @FXML
    private Button createRoomButton;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        roomList.getItems().setAll(RoomController.getRooms());
    }

    @FXML
    public void handleCreateRoom(){
        RoomController.addRoom();
        roomList.getItems().setAll(RoomController.getRooms());
    }
    
}
