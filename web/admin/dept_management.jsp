<%-- 
    Document   : dept_management
    Created on : Aug 22, 2021, 10:50:25 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>department management</title>
        <link rel="stylesheet" href="bootstrap-5.1.0-dist/css/bootstrap.min.css">
    </head>
    <body>
        <%@page import="java.sql.*" %>
        <%@include file="admin_header.html" %>
        <%@include file="../dbconnection.jsp" %>
        
        <form action="dept_management.jsp" method="post">
            <label for="">Department name:</label>
            <<input type="text" name="dept_name" required="required"> <br/>
            <label for="dept_admin">Department Administrator:</label>
            <input name="dept_admin" class="depart_admin"> <br/>
            <label>Number of semesters :</label>
            <<select name="num_of_sems">
                <option value="6">6</option>
                <option value="8">8</option>
                <option value="10">10</option>
                <option value="12">12</option>
                <option value="14">14</option>
            </select>
            <label>Department id:</label>
            <input type="text" name="dept_id" required="required"> <br/>            
        </form>
    </body>
</html>
