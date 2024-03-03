/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.university.exam.domain;

/**
 *
 * @author user
 */
public class Room {
    private String room_id;
    public Room(String room_id){
        this.room_id = room_id;
    }
    public String getRoomId(){ return room_id;}
}
