/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.university.exam.db;

import java.util.ArrayList;
import java.sql.*;
import org.university.exam.domain.Course;
import org.university.exam.domain.Department;
import org.university.exam.domain.Instructor;
import org.university.exam.domain.TimePeriod;
import org.university.exam.domain.Room;

/**
 *
 * @author user
 */
public class Data {
    private final String dburl = "jdbc:mysql://localhost:3306/scheduler?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private final String dbDriver = "com.mysql.cj.jdbc.Driver";
    private final String dbusername = "root";
    private final String dbpwd = "";
    private ArrayList<Room> rooms;
    private ArrayList<Department> depts;
    private ArrayList<Course> courses;
    private ArrayList<Instructor> instructors;
    private ArrayList<TimePeriod> timePreiod;
    private int numberOfExams = 0;
    public Data() throws ClassNotFoundException, SQLException{initializie();}
    private Data initializie() throws ClassNotFoundException, SQLException {
        Class.forName(dbDriver);
        Connection con = DriverManager.getConnection(dburl,dbusername,dbpwd);
        
        rooms = selectRooms(con);
        timePreiod = selectTimePeriods(con);
        instructors = selectInstructors(con);
        courses = selectCourses(con);
        depts =selectDepartments(con);
        depts.forEach(x -> numberOfExams+=x.getCourses().size());
        return this;
        
        
    }
    public ArrayList<Room> selectRooms(Connection con) throws SQLException{
        ArrayList<Room> roomse = new ArrayList<Room>();
        ResultSet roomRS = con.createStatement().executeQuery("SELECT *FROM room WHERE room_type='lecture'");
        while(roomRS.next()) roomse.add(new Room(roomRS.getString("room_id")));
        return roomse;
    }
    public ArrayList<TimePeriod> selectTimePeriods(Connection con) throws SQLException{
        ArrayList<TimePeriod> timePeriodss = new ArrayList<TimePeriod>();
        ResultSet meetingTimeRS = con.createStatement().executeQuery("SELECT *FROM yeroo");
        while(meetingTimeRS.next()) timePeriodss.add(new TimePeriod(meetingTimeRS.getString("period_id"), meetingTimeRS.getString("period_day"), meetingTimeRS.getString("period_time")));
        return timePeriodss;
    }
    public ArrayList<Instructor> selectInstructors(Connection con) throws SQLException{
        ArrayList<Instructor> instructorss = new ArrayList<Instructor>();
        ResultSet instructorRS = con.createStatement().executeQuery("SELECT *FROM instructor");
        while(instructorRS.next()){
            String instr_id = instructorRS.getString("instr_id");
            String instr_first_name = instructorRS.getString("first_name");
            String instr_last_name = instructorRS.getString("last_name");
            String instr_name = instr_first_name+" "+instr_last_name;
            instructorss.add(new Instructor(instr_id,instr_name));
        }
        return instructorss;
    }
    private ArrayList<Course> selectCourses(Connection con) throws SQLException {
        ArrayList<Course> coursess = new ArrayList<Course>();
        ResultSet courseRS = con.createStatement().executeQuery("SELECT * FROM course ORDER BY course_id ASC");
        while(courseRS.next()){
            ResultSet courseInstructorRS = con.createStatement().executeQuery("SELECT *FROM instructor_course where course_id='"+courseRS.getString("course_id")+"'");
            ArrayList<Instructor> courseInstructors = new ArrayList<Instructor>();
            while(courseInstructorRS.next())
                for(int i = 0; i < instructors.size();i++)
                    if(instructors.get(i).getInstructorId().equals(courseInstructorRS.getString("instr_id")))
                        courseInstructors.add(instructors.get(i));
            coursess.add(new Course(courseRS.getString("course_id"), courseRS.getString("course_name"), courseInstructors));
        }
        return coursess;
    }
    
    private ArrayList<Department> selectDepartments(Connection con)throws SQLException {
        ArrayList<Department> depts = new ArrayList<Department>();
        ResultSet deptRS = con.createStatement().executeQuery("SELECT * FROM department");
        while(deptRS.next()){
            ResultSet deptCourseRS = con.createStatement().executeQuery("SELECT *FROM dept_course where dept_id='"+deptRS.getString("dept_id")+"'");
            ArrayList<Course> deptCourse = new ArrayList<>();
            while(deptCourseRS.next())
                for(int i = 0; i < courses.size();i++)
                    if(courses.get(i).getCourseId().equals(deptCourseRS.getString("course_id")))
                        deptCourse.add(courses.get(i));
            depts.add(new Department(deptRS.getString("dept_id"), deptCourse));
        }
        return depts;
    }  
    public ArrayList<Room> getRooms(){return rooms;}
    public ArrayList<Instructor> getInstructors(){return instructors;}
    public ArrayList<Course> getCourses(){return courses;}
    public ArrayList<Department> getDepts(){return depts;}
    public ArrayList<TimePeriod> getTimePeriods(){return timePreiod;}
    public int getNumberOfExams(){return this.numberOfExams;}
}

