/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.university.exam.schedules;

import java.util.ArrayList;
import org.university.exam.db.Data;
import org.university.exam.domain.Department;
import org.university.exam.domain.Exam;

/**
 *
 * @author user
 */
public class Schedule {
    private ArrayList<Exam> exams;
    private boolean isFitnessChanged = true;
    private double fitness = -1;
    private ArrayList<Conflict> conflicts;
    private int numbOfConflicts = 0;
    private Data data;
    private int examNumb = 0;
    public Data getData(){return data;}
    public Schedule(Data data){
        this.data = data;
        this.exams = new ArrayList<Exam>(data.getNumberOfExams()); ;
    }  
    public Schedule(Schedule schedule){
        this.data = schedule.data;
        this.exams = new ArrayList<Exam>(data.getNumberOfExams());
        this.exams.addAll(schedule.getExams());
        findAllConflicts();
    }
    public Schedule initialize(){
        new ArrayList<Department>(data.getDepts()).forEach(dept ->{
            dept.getCourses().forEach(course ->{
                Exam newExam = new Exam(examNumb++,dept,course);
                newExam.setTimePeriod(data.getTimePeriods().get((int)(data.getTimePeriods().size() * Math.random())));
                newExam.setRoom(data.getRooms().get((int)(data.getRooms().size() * Math.random())));
                newExam.setInstructor(course.getCourseInstructors().get((int)(course.getCourseInstructors().size() * Math.random())));
                exams.add(newExam);
            }); 
        });
        return this;
    }
    
    
    
    public Schedule findAllConflicts(){
        conflicts = new ArrayList<Conflict>();
        exams.forEach(exam ->{
            exams.stream().filter( y -> exams.indexOf(y) >= exams.indexOf(exam)).forEach( y ->{
                if(exam.getTimePeriod()== y.getTimePeriod()&& exam.getExamId() != y.getExamId()){
                    if(exam.getRoom() == y.getRoom()){
                        ArrayList<Exam> conflictBetweenExams = new ArrayList<Exam>();
                        conflictBetweenExams.add(exam);
                        conflictBetweenExams.add(y);
                        Conflict conflict = new Conflict(Conflict.ConflictType.RoomBooking,conflictBetweenExams);
                        conflicts.add(conflict);
                    }
                    if(exam.getInstructor()== y.getInstructor()){
                        ArrayList<Exam> conflictBetweenExams = new ArrayList<Exam>();
                        conflictBetweenExams.add(exam);
                        conflictBetweenExams.add(y);
                        Conflict conflict = new Conflict(Conflict.ConflictType.InstructorBooking,conflictBetweenExams);
                        conflicts.add(conflict);
                    }
                    if(exam.getDept()== y.getDept()){
                        ArrayList<Exam> conflictBetweenExams = new ArrayList<Exam>();
                        conflictBetweenExams.add(exam);
                        conflictBetweenExams.add(y);
                        Conflict conflict = new Conflict(Conflict.ConflictType.DepartmentBooking,conflictBetweenExams);
                        conflicts.add(conflict);
                    }
                }
            });
        });
        return this;
    }
    public ArrayList<Exam> getExams(){
        isFitnessChanged = true;
        return exams;
    }
    public int getNumbOfConflicts(){ return numbOfConflicts;}
    public double getFitness(){
        if(isFitnessChanged == true){
            fitness = calculateFitness();
            isFitnessChanged = false;
        }
        return fitness;
    }
    public ArrayList<Conflict> getConflicts(){return conflicts;}
    private double calculateFitness(){
        numbOfConflicts = 0;
        exams.forEach(x ->{
            exams.stream().filter( y -> exams.indexOf(y)>=exams.indexOf(x)).forEach( y -> {
                if(x.getTimePeriod()== y.getTimePeriod()&& x.getExamId()!= y.getExamId()){
                    if(x.getRoom() == y.getRoom()) numbOfConflicts++;
                    if(x.getInstructor()== y.getInstructor()) numbOfConflicts++;
                    if(x.getDept() == y.getDept()) numbOfConflicts++;
            }
            });
        });
        return 1/(double)(numbOfConflicts +1);
    } 
    public String toString(){
        String returnValue = new String();
        for(int x = 0; x < exams.size() - 1;x++) returnValue+=exams.get(x)+",";
        returnValue +=exams.get(exams.size() - 1);
        return returnValue;
    }
}

