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
import java.util.stream.IntStream;
import org.university.exam.db.Data;

/**
 *
 * @author user
 */
public class Algorithm {
    private Data data;
    public Algorithm(Data data){
        this.data = data;
    }
    public Population evolve(Population population){ 
        return mutatePopulation(crossoverPopulation(population));
    }
    Population crossoverPopulation(Population population){
        Population crossoverPopulation = new Population(population.getSchedules().size(),data);
        IntStream.range(0,ExamSchedule.NUMB_ELITE_SCHEDULES ).forEach( x -> crossoverPopulation.getSchedules().set(x, population.getSchedules().get(x)));
        IntStream.range(ExamSchedule.NUMB_ELITE_SCHEDULES, population.getSchedules().size()).forEach( x -> {
            if(ExamSchedule.CROSSOVER_RATE > Math.random()){
                Schedule schedule1 = selectTournamentPopulation(population).sortByFitness().getSchedules().get(0);
                Schedule schedule2 = selectTournamentPopulation(population).sortByFitness().getSchedules().get(0);
                crossoverPopulation.getSchedules().set(x, crossoverSchedule(schedule1, schedule2));
            } else crossoverPopulation.getSchedules().set(x, population.getSchedules().get(x));
        });
        return crossoverPopulation;
    }
    Schedule crossoverSchedule(Schedule schedule1, Schedule schedule2){
        Schedule crossoverSchedule = new Schedule(data).initialize();
        IntStream.range(0, crossoverSchedule.getExams().size()).forEach( x -> {
            if(Math.random() > 0.5) crossoverSchedule.getExams().set(x,schedule1.getExams().get(x));
            else crossoverSchedule.getExams().set(x,schedule2.getExams().get(x));
        });
        return crossoverSchedule;
    }
    Population mutatePopulation(Population population){
        Population mutatePopulation = new Population(population.getSchedules().size(),data);
        ArrayList<Schedule> schedules = mutatePopulation.getSchedules();
        IntStream.range(0,ExamSchedule.NUMB_ELITE_SCHEDULES ).forEach( x -> schedules.set(x, population.getSchedules().get(x)));
        IntStream.range(ExamSchedule.NUMB_ELITE_SCHEDULES, population.getSchedules().size()).forEach( x -> {
            schedules.set(x, mutateSchedule(population.getSchedules().get(x)));
        });
        return mutatePopulation;
    }
    Schedule mutateSchedule(Schedule mutateSchedule){
        Schedule schedule = new Schedule(data).initialize();
        IntStream.range(0, mutateSchedule.getExams().size()).forEach( x -> {
            if(ExamSchedule.MUTATION_RATE > Math.random()) mutateSchedule.getExams().set(x, schedule.getExams().get(x));
        });
        return mutateSchedule;
    }
    Population selectTournamentPopulation(Population population){
        Population tournamentPopulation = new Population(ExamSchedule.TOURNAMENT_SELECTION_SIZE,data);
        IntStream.range(0, ExamSchedule.TOURNAMENT_SELECTION_SIZE).forEach( x -> {
            tournamentPopulation.getSchedules().set(x, population.getSchedules().get((int)(Math.random() * population.getSchedules().size())));
        });
        return tournamentPopulation;
    }
    
}
