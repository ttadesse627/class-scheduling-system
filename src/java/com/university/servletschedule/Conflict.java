/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.university.servletschedule;

import java.util.ArrayList;
import com.university.domain.Class;

public class Conflict {
    static enum ConflictType {InstructorBooking,RoomBooking,DepartmentBooking};
    private Conflict.ConflictType conflictType;
    private ArrayList<Class> conflictBetweenClasses;
    public Conflict(Conflict.ConflictType conflictType, ArrayList<Class> conflictBetweenClasses){
        this.conflictType = conflictType;
        this.conflictBetweenClasses = conflictBetweenClasses;
    }
    public Conflict.ConflictType getConflictType(){ return  conflictType;}
    public ArrayList<Class> getConflictBetweenClasses(){ return  conflictBetweenClasses;}
    public String toString(){ return  new String(this.conflictType+" "+this.conflictBetweenClasses);}
}
