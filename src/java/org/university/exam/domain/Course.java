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
import java.util.ArrayList;
public class Course {
    private String courseId = null;
    private String courseName = null;
    private ArrayList<Instructor>courseInstructors;
    public Course(String courseId,String courseName,ArrayList<Instructor> courseInstructors){
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseInstructors = courseInstructors;
    }
    public String getCourseId(){ return courseId;}
    public String getCourseName(){ return courseName;}
    public ArrayList<Instructor> getCourseInstructors(){ return courseInstructors;}
    public String toString(){ return courseName;}
}
