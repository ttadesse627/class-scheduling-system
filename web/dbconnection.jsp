<%-- 
    Document   : dbconnection
    Created on : Aug 21, 2021, 1:55:09 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
        <%! 
            Connection con = null;
            String dburl = "jdbc:mysql://localhost:3306/scheduler?zeroDateTimeBehavior=CONVERT_TO_NULL";
            String driver = "com.mysql.cj.jdbc.Driver";
            String dbusername = "root";
            String dbpassword = "";
            String msg = null;
            PreparedStatement ps;
            Statement st;
            ResultSet rs;
            String sql;
            int counter = 0;
        %>
        <%
            try {
                   Class.forName(driver);
                   con = DriverManager.getConnection(dburl,dbusername,dbpassword);
                   
                } catch (ClassNotFoundException e) {
                    msg= e.getMessage().toString();
                    out.print("<h2 style = 'color:red;'>"+msg+"</h2>");
                }
                
        %>
