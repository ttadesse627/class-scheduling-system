/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.university.servletschedule;

import com.university.db.DBMgr;
import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 *
 * @author user
 */

public class Population {
    private ArrayList<Schedule> schedules;
    public Population(int size, DBMgr data){
        schedules = new ArrayList<Schedule>(size);
        IntStream.range(0, size).forEach( x -> schedules.add(new Schedule(data).initialize()));
    }
    public ArrayList<Schedule> getSchedules(){ return this.schedules;}
    public Population sortByFitness(){
        schedules.sort((schedule1,schedule2) -> {
            int returnValue = 0;
            if(schedule1.getFitness() > schedule2.getFitness()) returnValue = -1;
            else if(schedule1.getFitness() < schedule2.getFitness()) returnValue = 1;
            return returnValue;
        });
        return this;
    }
}
