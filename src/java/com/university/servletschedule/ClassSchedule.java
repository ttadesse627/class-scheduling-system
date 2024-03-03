/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.university.servletschedule;

import com.university.db.DBMgr;
import com.university.domain.Class;
import com.university.domain.Department;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
public class ClassSchedule extends HttpServlet {

    public static final int POPULATION_SIZE = 10;
    public static final double MUTATION_RATE = 0.1;
    public static final double CROSSOVER_RATE = 0.9;
    public static final int TOURNAMENT_SELECTION_SIZE = 3;
    public static final int NUMB_ELITE_SCHEDULES = 1;
    private int scheduleNumb = 0;
    private int classNumb = 1;
    private DBMgr data;
    private int counter = 1;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        PrintWriter out = response.getWriter();
        ClassSchedule driver = new ClassSchedule();
        driver.data = new DBMgr();
        out.print("here!");
        int generationNumber = 1;
        response.setContentType("text/html;charset=UTF-8");
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<link media=\"screen\" rel=\"stylesheet\" href=\"sevlet_style.css\"/>");
        out.println("<link media=\"print\" rel=\"stylesheet\" href=\"print_style.css\"/>");
        out.print("");
        out.println("</head>");
        out.println("<body>");
        out.print("<div class=\"modal\">");
        out.print("<a href=\"admin/department.jsp\" class=\"close\" title=\"Close\">&times;</a>\n" +
                    "<div class=\"modal-content\" id=\"schedule\">");
        out.print("<div class=\"tab_header\">");
        out.println("<button id=\"printPage\" class=\"print\" title=\"print schedule\" onclick=\"window.print()\"><img src=\"images/print.PNG\"/></button>");
        out.println("<h1>Lecture Schedules</h1>");
        out.print("</div>");
        Algorithm geneticAlgorithm = new Algorithm(driver.data);
        Population population = new Population(ClassSchedule.POPULATION_SIZE, driver.data).sortByFitness();

        if (population.getSchedules().get(0).getFitness() == 1)driver.printEachDeptSchedule(population.getSchedules().get(0), generationNumber,out);
        driver.classNumb = 1;
        while (population.getSchedules().get(0).getFitness() != 1.0) {
            population = geneticAlgorithm.evolve(population).sortByFitness();
            driver.scheduleNumb = 0;
            generationNumber+= 1;
            if (population.getSchedules().get(0).getFitness() == 1)driver.printEachDeptSchedule(population.getSchedules().get(0), generationNumber,out);
            driver.classNumb = 1;
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
                        +"window.alert(\"'"+ex+"'\");\n"
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
                        +"window.alert('"+ex+"');\n" 
                        +"document.location = \"admin\\department.jsp\";\n" 
                        +"</script>");
        } catch (SQLException ex) {
            out.print("<script>\n" 
                        +"window.alert('"+ex+"');\n" 
                        +"document.location = \"admin\\department.jsp\";\n" 
                        +"</script>");
        }
    }

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void printEachDeptSchedule(Schedule schedule1, int generation,PrintWriter out) {
        
        ArrayList<Class> classes = schedule1.getClasses();
        ArrayList<Department> deptList = new ArrayList<>();
        out.print("<table><tr>"
                + "<th>No</th>"
                + "<th colspan=\"3\">Monday</th>"
                + "<th colspan=\"3\">Tuesday</th>"
                + "<th colspan=\"3\">Wednesday</th>"
                + "<th colspan=\"3\">Thursday</th>"
                + "<th colspan=\"3\">Friday</th></tr>");
        counter = 1;
        classes.forEach(x ->{
            classNumb++;
            if(deptList.contains(x.getDept())){
                String classType = null;
                int coursesIndex = data.getCourses().indexOf(x.getCourse());
                int roomsIndex = data.getRooms().indexOf(x.getRoom());
                int instructorsIndex = data.getInstructors().indexOf(x.getInstructor());
                int timePeriodsIndex = data.getTimePeriods().indexOf(x.getTimePeriod());
                if(data.getRooms().get(roomsIndex).getRoomType().equals("lecture")){
                    classType = "";
                }
                else classType = "LAB";
                if(data.getTimePeriods().get(timePeriodsIndex).getPeriodDay().equals("Monday")){
                    out.print("<td>");
                    out.print(String.format(" %1$02d ", classNumb) + "</td>");
                    out.print("<td>");
                    out.print(String.format(" %1$21s ", classType+" "+data.getCourses().get(coursesIndex).getCourseId()+"</td>"));
                    out.print("<td>");
                    out.print(String.format(" %1$15s ", data.getInstructors().get(instructorsIndex).getInstructorId()+"</td>"));
                    out.print("<td>");
                    out.println(data.getTimePeriods().get(timePeriodsIndex).getPeriodTime()+data.getTimePeriods().get(timePeriodsIndex).getPeriodId()+"</td>"
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
                    out.print(String.format(" %1$02d ", classNumb) + "</td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>");
                    out.print("<td>");
                    out.print(String.format(" %1$21s ", classType+" "+data.getCourses().get(coursesIndex).getCourseId()+"</td>"));
                    out.print("<td>");
                    out.print(String.format(" %1$15s ", data.getInstructors().get(instructorsIndex).getInstructorId()+"</td>"));
                    out.print("<td>");
                    out.println(data.getTimePeriods().get(timePeriodsIndex).getPeriodTime()+data.getTimePeriods().get(timePeriodsIndex).getPeriodId()+"</td>"
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
                    out.print(String.format(" %1$02d ", classNumb) + "</td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>");
                    out.print("<td>");
                    out.print(String.format(" %1$21s ", classType+" "+data.getCourses().get(coursesIndex).getCourseId()+"</td>"));
                    out.print("<td>");
                    out.print(String.format(" %1$15s ", data.getInstructors().get(instructorsIndex).getInstructorId()+"</td>"));
                    out.print("<td>");
                    out.println(data.getTimePeriods().get(timePeriodsIndex).getPeriodTime()+data.getTimePeriods().get(timePeriodsIndex).getPeriodId()+"</td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "</tr>");
                }else if(data.getTimePeriods().get(timePeriodsIndex).getPeriodDay().equals("Thursday")){
                    out.print("<td>");
                    out.print(String.format(" %1$02d ", classNumb) + "</td>"
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
                    out.print(String.format(" %1$21s ", classType+" "+data.getCourses().get(coursesIndex).getCourseId()+"</td>"));
                    out.print("<td>");
                    out.print(String.format(" %1$15s ", data.getInstructors().get(instructorsIndex).getInstructorId()+"</td>"));
                    out.print("<td>");
                    out.println(data.getTimePeriods().get(timePeriodsIndex).getPeriodTime()+data.getTimePeriods().get(timePeriodsIndex).getPeriodId()+"</td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "</tr>");
                } else{
                    out.print("<td>");
                    out.print(String.format(" %1$02d ", classNumb) + "</td>"
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
                    out.print(String.format(" %1$21s ", classType+" "+data.getCourses().get(coursesIndex).getCourseId()+"</td>"));
                    out.print("<td>");
                    out.print(String.format(" %1$15s ", data.getInstructors().get(instructorsIndex).getInstructorId()+"</td>"));
                    out.print("<td>");
                    out.println(data.getTimePeriods().get(timePeriodsIndex).getPeriodTime()+data.getTimePeriods().get(timePeriodsIndex).getPeriodId()+"</td>"
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
                String classType = null;
                classNumb = 1;
                if(data.getRooms().get(roomsIndex).getRoomType().equals("lecture")){
                    classType = "";
                }
                else classType = "LAB";
                
                
                if(data.getTimePeriods().get(timePeriodsIndex).getPeriodDay().equals("Monday")){
                    out.print("<tr><td colspan=\"16\" style=\"text-align: center;\"><p><b>"+counter+". ");
                    out.print(data.getDepts().get(majorIndex).getDeptName());
                    out.print(String.format(" %1$10s ",
                            "   </b><span>Lecture Room: "+data.getDepts().get(majorIndex).getRooms().get(0).getRoomId()+"</span>  "));
                    out.print(String.format(" %1$10s ",
                            "<span>Lab Room: "+data.getDepts().get(majorIndex).getRooms().get(1).getRoomId())+"</span></p></td></tr>");
                    
                    
                    out.print("<td>");
                    out.print(String.format(" %1$02d ", classNumb) + "</td>");
                    out.print("<td>");
                    out.print(String.format(" %1$21s ", classType+" "+data.getCourses().get(coursesIndex).getCourseId()+"</td>"));
                    out.print("<td>");
                    out.print(String.format(" %1$15s ", data.getInstructors().get(instructorsIndex).getInstructorId()+"</td>"));
                    out.print("<td>");
                    out.println(data.getTimePeriods().get(timePeriodsIndex).getPeriodTime()+data.getTimePeriods().get(timePeriodsIndex).getPeriodId()+"</td>"
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
                    out.print(data.getDepts().get(majorIndex).getDeptName());
                    out.print(String.format(" %1$10s ",
                            "   </b><span>Lecture Room: "+data.getDepts().get(majorIndex).getRooms().get(0).getRoomId()+"</span>  "));
                    out.print(String.format(" %1$10s ",
                            "<span>Lab Room: "+data.getDepts().get(majorIndex).getRooms().get(1).getRoomId())+"</span></p></td></tr>"); 
                    
                    out.print("<td>");
                    out.print(String.format(" %1$02d ", classNumb) + "</td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>");
                    out.print("<td>");
                    out.print(String.format(" %1$21s ", classType+" "+data.getCourses().get(coursesIndex).getCourseId()+"</td>"));
                    out.print("<td>");
                    out.print(String.format(" %1$15s ", data.getInstructors().get(instructorsIndex).getInstructorId()+"</td>"));
                    out.print("<td>");
                    out.println(data.getTimePeriods().get(timePeriodsIndex).getPeriodTime()+data.getTimePeriods().get(timePeriodsIndex).getPeriodId()+"</td>"
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
                    out.print(data.getDepts().get(majorIndex).getDeptName());
                    out.print(String.format(" %1$10s ",
                            "   </b><span>Lecture Room: "+data.getDepts().get(majorIndex).getRooms().get(0).getRoomId()+"</span>  "));
                    out.print(String.format(" %1$10s ",
                            "<span>Lab Room: "+data.getDepts().get(majorIndex).getRooms().get(1).getRoomId())+"</span></p></td></tr>");   
                    
                    out.print("<td>");
                    out.print(String.format(" %1$02d ", classNumb) + "</td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>");
                    out.print("<td>");
                    out.print(String.format(" %1$21s ", classType+" "+data.getCourses().get(coursesIndex).getCourseId()+"</td>"));
                    out.print("<td>");
                    out.print(String.format(" %1$15s ", data.getInstructors().get(instructorsIndex).getInstructorId()+"</td>"));
                    out.print("<td>");
                    out.println(data.getTimePeriods().get(timePeriodsIndex).getPeriodTime()+data.getTimePeriods().get(timePeriodsIndex).getPeriodId()+"</td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "</tr>");
                }else if(data.getTimePeriods().get(timePeriodsIndex).getPeriodDay().equals("Thursday")){
                    out.print("<tr><td colspan=\"16\" style=\"text-align: center;\"><p><b>"+counter+". ");
                    out.print(data.getDepts().get(majorIndex).getDeptName());
                    out.print(String.format(" %1$10s ",
                            "   </b><span>Lecture Room: "+data.getDepts().get(majorIndex).getRooms().get(0).getRoomId()+"</span>  "));
                    out.print(String.format(" %1$10s ",
                            "<span>Lab Room: "+data.getDepts().get(majorIndex).getRooms().get(1).getRoomId())+"</span></p></td></tr>");
                    
                    out.print("<td>");
                    out.print(String.format(" %1$02d ", classNumb) + "</td>"
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
                    out.print(String.format(" %1$21s ", classType+" "+data.getCourses().get(coursesIndex).getCourseId()+"</td>"));
                    out.print("<td>");
                    out.print(String.format(" %1$15s ", data.getInstructors().get(instructorsIndex).getInstructorId()+"</td>"));
                    out.print("<td>");
                    out.println(data.getTimePeriods().get(timePeriodsIndex).getPeriodTime()+data.getTimePeriods().get(timePeriodsIndex).getPeriodId()+"</td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td></td>"
                            + "</tr>");
                } else{
                    out.print("<tr><td colspan=\"16\" style=\"text-align: center;\"><p><b>"+counter+". ");
                    out.print(data.getDepts().get(majorIndex).getDeptName());
                    out.print(String.format(" %1$10s ",
                            "   </b><span>Lecture Room: "+data.getDepts().get(majorIndex).getRooms().get(0).getRoomId()+"</span>  "));
                    out.print(String.format(" %1$10s ",
                            "<span>Lab Room: "+data.getDepts().get(majorIndex).getRooms().get(1).getRoomId())+"</span></p></td></tr>");
                    out.print("<td>");
                    out.print(String.format(" %1$02d ", classNumb) + "</td>"
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
                    out.print(String.format(" %1$21s ", classType+" "+data.getCourses().get(coursesIndex).getCourseId()+"</td>"));
                    out.print("<td>");
                    out.print(String.format(" %1$15s ", data.getInstructors().get(instructorsIndex).getInstructorId()+"</td>"));
                    out.print("<td>");
                    out.println(data.getTimePeriods().get(timePeriodsIndex).getPeriodTime()+data.getTimePeriods().get(timePeriodsIndex).getPeriodId()+"</td>"
                            + "</tr>"); 
                }
                counter++;
            }
        });
    }
    
}
