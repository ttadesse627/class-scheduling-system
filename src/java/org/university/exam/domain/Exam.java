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
public class Exam {
    private int examId;
    private Department dept;
    private Course course;
    private Instructor instructor;
    private TimePeriod timePeriod;
    private Room room;
    public Exam(int examId, Department dept,Course course){
        this.examId = examId;
        this.dept = dept;
        this.course = course;
    }
    public void setInstructor(Instructor instructor){ this.instructor = instructor;}
    public void setTimePeriod(TimePeriod timePeriod){ this.timePeriod = timePeriod;}
    public void setRoom(Room room){ this.room = room;}
    public int getExamId(){return examId;}
    public Department getDept(){return dept;}
    public Course getCourse(){return course;}
    public Instructor getInstructor(){return instructor;}
    public TimePeriod getTimePeriod(){return timePeriod;}
    public Room getRoom(){return room;}
    public String toString(){
        return "["+dept.getDeptName()+", "+course.getCourseId()+", "+room.getRoomId()+", "+instructor.getInstructorId()+", "+timePeriod.getPeriodId()+"]";
    }
}
