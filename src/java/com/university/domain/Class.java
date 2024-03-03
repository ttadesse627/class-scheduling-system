/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.university.domain;

public class Class {
    private int classId;
    private Department dept;
    private Course course;
    private Instructor instructor;
    private TimePeriod timePeriod;
    private Room room;
    public Class(int classId, Department dept,Course course,Room room){
        this.classId = classId;
        this.dept = dept;
        this.course = course;
        this.room = room;
    }
    public void setInstructor(Instructor instructor){ this.instructor = instructor;}
    public void setTimePeriod(TimePeriod timePeriod){ this.timePeriod = timePeriod;}
    public void setRoom(Room room){ this.room = room;}
    public int getClassId(){return classId;}
    public Department getDept(){return dept;}
    public Course getCourse(){return course;}
    public Instructor getInstructor(){return instructor;}
    public TimePeriod getTimePeriod(){return timePeriod;}
    public Room getRoom(){return room;}
    public String toString(){
        return "["+dept.getDeptName()+", "+course.getCourseId()+", "+room.getRoomId()+", "+instructor.getInstructorId()+", "+timePeriod.getPeriodId()+"]";
    }
}
