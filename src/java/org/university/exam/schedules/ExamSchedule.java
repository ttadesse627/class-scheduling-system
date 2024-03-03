/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.university.exam.schedules;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.university.exam.db.Data;
import org.university.exam.domain.Department;
import org.university.exam.domain.Exam;

/**
 *
 * @author user
 */
public class ExamSchedule extends HttpServlet {


    public static final int POPULATION_SIZE = 10;
    public static final double MUTATION_RATE = 0.1;
    public static final double CROSSOVER_RATE = 0.9;
    public static final int TOURNAMENT_SELECTION_SIZE = 3;
    public static final int NUMB_ELITE_SCHEDULES = 1;
    private int scheduleNumb = 0;
    private int examNumb = 1;
    private Data data;
    private int counter = 1;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        
        ExamSchedule driver = new ExamSchedule();
        driver.data = new Data();
        int generationNumber = 0;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<link rel=\"stylesheet\" href=\"sevlet_style.css\"/>");
        out.println("<link media=\"print\" rel=\"stylesheet\" href=\"print_style.css\"/>");
        out.println("</head>");
        out.println("<body>");
        out.print("<div class=\"modal\">");
        out.print("<a href=\"admin/department.jsp\" class=\"close\" title=\"Close\">&times;</a>\n" +
                    "<div class=\"modal-content\" id= \"schedule\">");
        out.print("<div class=\"tab_header\">");
        out.println("<button title=\"print schedule\" onclick=\"window.print()\"><img src=\"images/print.PNG\"/></button>");
        out.println("<h1>Exam Schedules</h1>");
        out.print("</div>");
        Algorithm geneticAlgorithm = new Algorithm(driver.data);
        Population population = new Population(ExamSchedule.POPULATION_SIZE, driver.data).sortByFitness();

        if(population.getSchedules().get(0).getFitness() == 1.0)driver.printEachDeptSchedule(population.getSchedules().get(0), generationNumber,out);
        driver.examNumb = 1;
        while (population.getSchedules().get(0).getFitness() != 1.0) {
            population = geneticAlgorithm.evolve(population).sortByFitness();
            driver.scheduleNumb = 0;
            generationNumber+= 1;
            if (population.getSchedules().get(0).getFitness() == 1)driver.printEachDeptSchedule(population.getSchedules().get(0), generationNumber,out);
            driver.examNumb = 1;
        }
        out.println("</body>");
        out.println("</html>");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            out.print("<script>\n" 
                        +"window.alert(\'"+ex+"\');\n" 
                        +"document.location = \"admin\\department.jsp\";\n" 
                        +"</script>");
        } catch (SQLException ex) {
            out.print("<script>\n" 
                        +"window.alert(\'"+ex+"\');\n" 
                        +"document.location = \"admin\\department.jsp\";\n" 
                        +"</script>");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            out.print("<script>\n" 
                        +"window.alert(\'"+ex+"\');\n" 
                        +"document.location = \"admin\\department.jsp\";\n" 
                        +"</script>");
        } catch (SQLException ex) {
            out.print("<script>\n" 
                        +"window.alert(\'"+ex+"\');\n" 
                        +"document.location = \"admin\\department.jsp\";\n" 
                        +"</script>");
        }
    }


    private void printEachDeptSchedule(Schedule schedule1, int generationNumber,PrintWriter out) {
        ArrayList<Exam> exams = schedule1.getExams();
ArrayList<Department> deptList = new ArrayList<>();
        out.print("<table><tr>"
                + "<th>No</th>"
                + "<th colspan=\"3\">Monday</th>"
                + "<th colspan=\"3\">Tuesday</th>"
                + "<th colspan=\"3\">Wednesday</th>"
                + "<th colspan=\"3\">Thursday</th>"
                + "<th colspan=\"3\">Friday</th></tr>");
        counter = 1;
        exams.forEach(x ->{
            examNumb++;
            if(deptList.contains(x.getDept())){
                int coursesIndex = data.getCourses().indexOf(x.getCourse());
                int roomsIndex = data.getRooms().indexOf(x.getRoom());
                int instructorsIndex = data.getInstructors().indexOf(x.getInstructor());
                int timePeriodsIndex = data.getTimePeriods().indexOf(x.getTimePeriod());
                if(data.getTimePeriods().get(timePeriodsIndex).getPeriodDay().equals("Monday")){
                    out.print("<td>");
                    out.print(String.format(" %1$02d ", examNumb) + "</td>");
                    out.print("<td>");
                    out.print(String.format(" %1$21s ", data.getCourses().get(coursesIndex).getCourseId()+"</td>"));
                    out.print("<td>");
                    out.print(String.format(" %1$15s ", data.getInstructors().get(instructorsIndex).getInstructorId()+"</td>"));
                    out.print("<td>");
                    out.println(data.getTimePeriods().get(timePeriodsIndex).getPeriodTime()+" in "+data.getRooms().get(roomsIndex).getRoomId()+"</td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "</tr>");
                }else if(data.getTimePeriods().get(timePeriodsIndex).getPeriodDay().equals("Tuesday")){
                    out.print("<td>");
                    out.print(String.format(" %1$02d ", examNumb) + "</td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>");
                    out.print("<td>");
                    out.print(String.format(" %1$21s ", data.getCourses().get(coursesIndex).getCourseId()+"</td>"));
                    out.print("<td>");
                    out.print(String.format(" %1$15s ", data.getInstructors().get(instructorsIndex).getInstructorId()+"</td>"));
                    out.print("<td>");
                    out.println(data.getTimePeriods().get(timePeriodsIndex).getPeriodTime()+" in "+data.getRooms().get(roomsIndex).getRoomId()+"</td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "</tr>");
                }else if(data.getTimePeriods().get(timePeriodsIndex).getPeriodDay().equals("Wednesday")){
                    out.print("<td>");
                    out.print(String.format(" %1$02d ", examNumb) + "</td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>");
                    out.print("<td>");
                    out.print(String.format(" %1$21s ", data.getCourses().get(coursesIndex).getCourseId()+"</td>"));
                    out.print("<td>");
                    out.print(String.format(" %1$15s ", data.getInstructors().get(instructorsIndex).getInstructorId()+"</td>"));
                    out.print("<td>");
                    out.println(data.getTimePeriods().get(timePeriodsIndex).getPeriodTime()+" in "+data.getRooms().get(roomsIndex).getRoomId()+"</td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "</tr>");
                }else if(data.getTimePeriods().get(timePeriodsIndex).getPeriodDay().equals("Thursday")){
                    out.print("<td>");
                    out.print(String.format(" %1$02d ", examNumb) + "</td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>");
                    out.print("<td>");
                    out.print(String.format(" %1$21s ", data.getCourses().get(coursesIndex).getCourseId()+"</td>"));
                    out.print("<td>");
                    out.print(String.format(" %1$15s ", data.getInstructors().get(instructorsIndex).getInstructorId()+"</td>"));
                    out.print("<td>");
                    out.println(data.getTimePeriods().get(timePeriodsIndex).getPeriodTime()+" in "+data.getRooms().get(roomsIndex).getRoomId()+"</td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "</tr>");
                } else{
                    out.print("<td>");
                    out.print(String.format(" %1$02d ", examNumb) + "</td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>");
                    out.print("<td>");
                    out.print(String.format(" %1$21s ", data.getCourses().get(coursesIndex).getCourseId()+"</td>"));
                    out.print("<td>");
                    out.print(String.format(" %1$15s ", data.getInstructors().get(instructorsIndex).getInstructorId()+"</td>"));
                    out.print("<td>");
                    out.println(data.getTimePeriods().get(timePeriodsIndex).getPeriodTime()+" in "+data.getRooms().get(roomsIndex).getRoomId()+"</td>"
                            + "</tr>"); 
                }
            }
            else{
                int majorIndex = data.getDepts().indexOf(x.getDept());
                int coursesIndex = data.getCourses().indexOf(x.getCourse());
                int roomsIndex = data.getRooms().indexOf(x.getRoom());
                int instructorsIndex = data.getInstructors().indexOf(x.getInstructor());
                int timePeriodsIndex = data.getTimePeriods().indexOf(x.getTimePeriod());
                
                deptList.add(x.getDept());
                examNumb = 1;
                
                
                if(data.getTimePeriods().get(timePeriodsIndex).getPeriodDay().equals("Monday")){
                    out.print("<tr><td colspan=\"16\" style=\"text-align: center;\"><p><b>"+counter+". ");
                    out.print(data.getDepts().get(majorIndex).getDeptName()+"</b></p></td></tr>");
                    out.print("<td>");
                    out.print(String.format(" %1$02d ", examNumb) + "</td>");
                    out.print("<td>");
                    out.print(String.format(" %1$21s ",data.getCourses().get(coursesIndex).getCourseId()+"</td>"));
                    out.print("<td>");
                    out.print(String.format(" %1$15s ", data.getInstructors().get(instructorsIndex).getInstructorId()+"</td>"));
                    out.print("<td>");
                    out.println(data.getTimePeriods().get(timePeriodsIndex).getPeriodTime()+" in "+data.getRooms().get(roomsIndex).getRoomId()+"</td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "</tr>");
                }else if(data.getTimePeriods().get(timePeriodsIndex).getPeriodDay().equals("Tuesday")){
                    out.print("<tr><td colspan=\"16\" style=\"text-align: center;\"><p><b>"+counter+". ");
                    out.print(data.getDepts().get(majorIndex).getDeptName()+"</b></p></td></tr>");
                    out.print("<td>");
                    out.print(String.format(" %1$02d ", examNumb) + "</td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>");
                    out.print("<td>");
                    out.print(String.format(" %1$21s ",data.getCourses().get(coursesIndex).getCourseId()+"</td>"));
                    out.print("<td>");
                    out.print(String.format(" %1$15s ", data.getInstructors().get(instructorsIndex).getInstructorId()+"</td>"));
                    out.print("<td>");
                    out.println(data.getTimePeriods().get(timePeriodsIndex).getPeriodTime()+" in "+data.getRooms().get(roomsIndex).getRoomId()+"</td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "</tr>");
                }else if(data.getTimePeriods().get(timePeriodsIndex).getPeriodDay().equals("Wednesday")){
                    out.print("<tr><td colspan=\"16\" style=\"text-align: center;\"><p><b>"+counter+". ");
                    out.print(data.getDepts().get(majorIndex).getDeptName()+"</b></p></td></tr>");
                    out.print("<td>");
                    out.print(String.format(" %1$02d ", examNumb) + "</td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>");
                    out.print("<td>");
                    out.print(String.format(" %1$21s ", data.getCourses().get(coursesIndex).getCourseId()+"</td>"));
                    out.print("<td>");
                    out.print(String.format(" %1$15s ", data.getInstructors().get(instructorsIndex).getInstructorId()+"</td>"));
                    out.print("<td>");
                    out.println(data.getTimePeriods().get(timePeriodsIndex).getPeriodTime()+" in "+data.getRooms().get(roomsIndex).getRoomId()+"</td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "</tr>");
                }else if(data.getTimePeriods().get(timePeriodsIndex).getPeriodDay().equals("Thursday")){
                    out.print("<tr><td colspan=\"16\" style=\"text-align: center;\"><p><b>"+counter+". ");
                    out.print(data.getDepts().get(majorIndex).getDeptName()+"</b></p></td></tr>");
                    out.print("<td>");
                    out.print(String.format(" %1$02d ", examNumb) + "</td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>");
                    out.print("<td>");
                    out.print(String.format(" %1$21s ",data.getCourses().get(coursesIndex).getCourseId()+"</td>"));
                    out.print("<td>");
                    out.print(String.format(" %1$15s ", data.getInstructors().get(instructorsIndex).getInstructorId()+"</td>"));
                    out.print("<td>");
                    out.println(data.getTimePeriods().get(timePeriodsIndex).getPeriodTime()+" in "+data.getRooms().get(roomsIndex).getRoomId()+"</td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "</tr>");
                } else{
                    out.print("<tr><td colspan=\"16\" style=\"text-align: center;\"><p><b>"+counter+". ");
                    out.print(data.getDepts().get(majorIndex).getDeptName()+"</b></p></td></tr>");
                    out.print("<td>");
                    out.print(String.format(" %1$02d ", examNumb) + "</td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>");
                    out.print("<td>");
                    out.print(String.format(" %1$21s ", data.getCourses().get(coursesIndex).getCourseId()+"</td>"));
                    out.print("<td>");
                    out.print(String.format(" %1$15s ", data.getInstructors().get(instructorsIndex).getInstructorId()+"</td>"));
                    out.print("<td>");
                    out.println(data.getTimePeriods().get(timePeriodsIndex).getPeriodTime()+" in "+data.getRooms().get(roomsIndex).getRoomId()+"</td>"
                            + "</tr>"); 
                }
                counter++;
            }
        });
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}