/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.university.servletschedule;
import com.university.db.DBMgr;
import java.util.ArrayList;
import com.university.domain.Class;
import com.university.domain.Department;
import com.university.domain.Room;
public class Schedule {
    private ArrayList<Class> classes;
    private boolean isFitnessChanged = true;
    private double fitness = -1;
    private ArrayList<Conflict> conflicts;
    private int numbOfConflicts = 0;
    private DBMgr data;
    private int classNumb = 0;
    public DBMgr getData(){return data;}
    public Schedule(DBMgr data){
        this.data = data;
        this.classes = new ArrayList<Class>(data.getNumberOfClasses()); ;
    }  
    public Schedule(Schedule schedule){
        this.data = schedule.data;
        this.classes = new ArrayList<Class>(data.getNumberOfClasses());
        this.classes.addAll(schedule.getClasses());
        findAllConflicts();
    }
    public Schedule initialize(){
        new ArrayList<Department>(data.getDepts()).forEach(dept ->{
            dept.getCourses().forEach(course ->{
                int lec_hr = course.getLecHours();
                int lab_hr = course.getLabHours();
                for(int i = 0; i < lec_hr; i++){
                    Room room = dept.getRooms().get(0);
                    Class newClass = new Class(classNumb++,dept,course,room);
                    newClass.setTimePeriod(data.getTimePeriods().get((int)(data.getTimePeriods().size() * Math.random())));
//                    newClass.setRoom(data.getRooms().get((int)(data.getRooms().size() * Math.random())));
                    newClass.setInstructor(course.getCourseInstructors().get((int)(course.getCourseInstructors().size() * Math.random())));
                    classes.add(newClass);
                }
                for(int i = 0; i < lab_hr; i++){
                    Room room = dept.getRooms().get(1);
                    Class newClass = new Class(classNumb++,dept,course,room);
                    newClass.setTimePeriod(data.getTimePeriods().get((int)(data.getTimePeriods().size() * Math.random())));
//                    newClass.setRoom(data.getRooms().get((int)(data.getRooms().size() * Math.random())));
                    newClass.setInstructor(course.getCourseInstructors().get((int)(course.getCourseInstructors().size() * Math.random())));
                    classes.add(newClass);
                }
            }); 
        });
        return this;
    }
    
    public Schedule findAllConflicts(){
        conflicts = new ArrayList<Conflict>();
        classes.forEach(classe ->{
            classes.stream().filter( y -> classes.indexOf(y) >= classes.indexOf(classe)).forEach( y ->{
                if(classe.getTimePeriod()== y.getTimePeriod()&& classe.getClassId() != y.getClassId()){
                    if(classe.getRoom() == y.getRoom()){
                        ArrayList<Class> conflictBetweenClasses = new ArrayList<Class>();
                        conflictBetweenClasses.add(classe);
                        conflictBetweenClasses.add(y);
                        Conflict conflict = new Conflict(Conflict.ConflictType.RoomBooking,conflictBetweenClasses);
                        conflicts.add(conflict);
                    }
                    if(classe.getInstructor()== y.getInstructor()){
                        ArrayList<Class> conflictBetweenClasses = new ArrayList<Class>();
                        conflictBetweenClasses.add(classe);
                        conflictBetweenClasses.add(y);
                        Conflict conflict = new Conflict(Conflict.ConflictType.InstructorBooking,conflictBetweenClasses);
                        conflicts.add(conflict);
                    }
                    if(classe.getDept() == y.getDept()){
                        ArrayList<Class> conflictBetweenClasses = new ArrayList<Class>();
                        conflictBetweenClasses.add(classe);
                        conflictBetweenClasses.add(y);
                        Conflict conflict = new Conflict(Conflict.ConflictType.DepartmentBooking,conflictBetweenClasses);
                        conflicts.add(conflict);
                    }
                }
            });
        });
        return this;
    }
    public ArrayList<Class> getClasses(){
        isFitnessChanged = true;
        return classes;
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
        classes.forEach(x ->{
            classes.stream().filter( y -> classes.indexOf(y)>=classes.indexOf(x)).forEach( y -> {
                if(x.getTimePeriod()== y.getTimePeriod()&& x.getClassId()!= y.getClassId()){
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
        for(int x = 0; x < classes.size() - 1;x++) returnValue+=classes.get(x)+",";
        returnValue +=classes.get(classes.size() - 1);
        return returnValue;
    }
}
