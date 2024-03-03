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
public class TimePeriod {
    private String period_id;
    private String period_day;
    private String period_time;
    public TimePeriod(String period_id, String period_day, String period_time){
        this.period_id = period_id;
        this.period_day = period_day;
        this.period_time = period_time;
    }
    public String getPeriodId(){ return period_id;}
    public String getPeriodDay(){ return period_day;}
    public String getPeriodTime(){ return period_time;}
}
