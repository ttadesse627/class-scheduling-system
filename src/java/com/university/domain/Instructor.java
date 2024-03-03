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
public class Instructor {
    private String instructorId;
    private String instructorName;
    public Instructor(String instructorId,String instructorName){
        this.instructorId = instructorId;
        this.instructorName = instructorName;
    }
    public String getInstructorId(){ return instructorId;}
    public String getInstructorName(){ return instructorName;}
    public String toString(){ return instructorName;}
}
