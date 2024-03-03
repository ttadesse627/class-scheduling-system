/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.university.db;
import java.sql.*;
import java.util.ArrayList;
import com.university.domain.Course;
import com.university.domain.Department;
import com.university.domain.Instructor;
import com.university.domain.TimePeriod;
import com.university.domain.Room;
public class DBMgr {
    private ArrayList<Room> rooms;
    private ArrayList<Department> depts;
    private ArrayList<Course> courses;
    private ArrayList<Instructor> instructors;
    private ArrayList<TimePeriod> timePeriods;
    private int numberOfClasses = 0;
    public DBMgr() throws ClassNotFoundException, SQLException{initializie();}
    private DBMgr initializie() throws ClassNotFoundException, SQLException {
        Connect dbcon = new Connect();
        Connection con = dbcon.getConnection();
        rooms = selectRooms(con);
        timePeriods = selectTimePeriods(con);
        instructors = selectInstructors(con);
        courses = selectCourses(con);
        depts =selectDepartments(con);
        depts.forEach(x -> numberOfClasses+=x.getCourses().size());
        return this;
        
        
    }
    public ArrayList<Room> selectRooms(Connection con) throws SQLException{
        ArrayList<Room> roomse = new ArrayList<Room>();
        ResultSet roomRS = con.createStatement().executeQuery("SELECT *FROM room");
        while(roomRS.next()) roomse.add(new Room(roomRS.getString("room_id"),roomRS.getString("room_type")));
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
        ResultSet courseRS = con.createStatement().executeQuery("SELECT *FROM course ORDER BY course_id ASC");
        while(courseRS.next()){
            ResultSet courseInstructorRS = con.createStatement().executeQuery("SELECT *FROM instructor_course where course_id='"+courseRS.getString("course_id")+"'");
            ArrayList<Instructor> courseInstructors = new ArrayList<Instructor>();
            while(courseInstructorRS.next())
                for(int i = 0; i < instructors.size();i++)
                    if(instructors.get(i).getInstructorId().equals(courseInstructorRS.getString("instr_id")))
                        courseInstructors.add(instructors.get(i));
            coursess.add(new Course(courseRS.getString("course_id"), courseRS.getString("course_name"), courseRS.getInt("ects"),courseRS.getInt("lec_hr"),courseRS.getInt("lab_hr"), courseInstructors));
        }
        return coursess;
    }
    
    private ArrayList<Department> selectDepartments(Connection con)throws SQLException {
        ArrayList<Department> depts = new ArrayList<Department>();
        ResultSet deptRS = con.createStatement().executeQuery("SELECT * FROM department");
        while(deptRS.next()){
            ResultSet deptCourseRS = con.createStatement().executeQuery("SELECT *FROM dept_course where dept_id='"+deptRS.getString("dept_id")+"'");
            ArrayList<Course> deptCourse = new ArrayList<>();
            ResultSet deptRoomRS = con.createStatement().executeQuery("SELECT *FROM dept_room where dept_id='"+deptRS.getString("dept_id")+"'");
            ArrayList<Room> deptRoom = new ArrayList<>();
            while(deptRoomRS.next()) 
                for(int i = 0; i < rooms.size();i++)
                    if(rooms.get(i).getRoomId().equals(deptRoomRS.getString("room_id")))
                        deptRoom.add(rooms.get(i));
            while(deptCourseRS.next())
                for(int i = 0; i < courses.size();i++)
                    if(courses.get(i).getCourseId().equals(deptCourseRS.getString("course_id")))
                        deptCourse.add(courses.get(i));
            depts.add(new Department(deptRS.getString("dept_id"), deptCourse,deptRoom));
        }
        return depts;
    }  
    public ArrayList<Room> getRooms(){return rooms;}
    public ArrayList<Instructor> getInstructors(){return instructors;}
    public ArrayList<Course> getCourses(){return courses;}
    public ArrayList<Department> getDepts(){return depts;}
    public ArrayList<TimePeriod> getTimePeriods(){return timePeriods;}
    public int getNumberOfClasses(){return this.numberOfClasses;}
}
