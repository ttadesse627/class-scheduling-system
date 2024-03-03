/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.university.exam.schedules;

/**
 *
 * @author user
 */
import java.util.ArrayList;
import org.university.exam.domain.Exam;
public class Conflict {
    static enum ConflictType {InstructorBooking,RoomBooking,DepartmentBooking};
    private Conflict.ConflictType conflictType;
    private ArrayList<Exam> conflictBetweenExams;
    public Conflict(Conflict.ConflictType conflictType, ArrayList<Exam> conflictBetweenExams){
        this.conflictType = conflictType;
        this.conflictBetweenExams = conflictBetweenExams;
    }
    public Conflict.ConflictType getConflictType(){ return  conflictType;}
    public ArrayList<Exam> getConflictBetweenExams(){ return  conflictBetweenExams;}
    public String toString(){ return  new String(this.conflictType+" "+this.conflictBetweenExams);}
    
}
