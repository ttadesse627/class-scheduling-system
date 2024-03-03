/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.university.domain;

import java.util.ArrayList;
public class Course {
    private String courseId = null;
    private String courseName = null;
    private int ects = 0,lec_hr = 0,lab_hr = 0;
    private ArrayList<Instructor>courseInstructors;
    public Course(String courseId,String courseName,int ects,int lec_hr,int lab_hr,ArrayList<Instructor> courseInstructors){
        this.courseId = courseId;
        this.courseName = courseName;
        this.ects = ects;
        this.lec_hr = lec_hr;
        this.lab_hr = lab_hr;
        this.courseInstructors = courseInstructors;
    }
    public String getCourseId(){ return courseId;}
    public String getCourseName(){ return courseName;}
    public int getECTS(){ return ects;}
    public int getLecHours(){ return lec_hr;}
    public int getLabHours(){ return lab_hr;}
    public ArrayList<Instructor> getCourseInstructors(){ return courseInstructors;}
    public String toString(){ return courseName;}
}
