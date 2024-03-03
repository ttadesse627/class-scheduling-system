/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.university.domain;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Department {
    private String deptName;
    private ArrayList<Course>deptCourses;
    private ArrayList<Room>deptRooms;
    public Department(String deptName,ArrayList<Course> deptCourses,ArrayList<Room> deptRooms){
        this.deptName = deptName;
        this.deptCourses = deptCourses;
        this.deptRooms = deptRooms;
    }
    public String getDeptName(){ return deptName;}
    public ArrayList<Course> getCourses(){ return deptCourses;}
    public ArrayList<Room> getRooms(){ return deptRooms;}
}
