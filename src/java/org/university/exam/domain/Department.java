/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.university.exam.domain;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Department {
    private String deptName;
    private ArrayList<Course>deptCourses;
    public Department(String deptName,ArrayList<Course> deptCourses){
        this.deptName = deptName;
        this.deptCourses = deptCourses;
    }
    public String getDeptName(){ return deptName;}
    public ArrayList<Course> getCourses(){ return deptCourses;}
}
