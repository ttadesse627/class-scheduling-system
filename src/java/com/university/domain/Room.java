/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.university.domain;

/**
 *
 * @author user
 */
public class Room {
    private String room_id,room_type;
    public Room(String room_id,String room_type){
        this.room_id = room_id;
        this.room_type = room_type;
    }
    public String getRoomId(){ return room_id;}
    public String getRoomType(){ return room_type;}
}
