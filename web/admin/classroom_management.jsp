<%-- 
    Document   : newjspclassroom_management
    Created on : Aug 22, 2021, 10:50:52 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>classroom management</title>
        <link rel="stylesheet" href="bootstrap-5.1.0-dist/css/bootstrap.min.css">
    </head>
    <body>
        <%@page import="java.sql.*" %>
        <%@include file="admin_header.html" %>
        <%@include file="../dbconnection.jsp" %>
        
        <form action="classroom_management.jsp">
            <label>Room id:</label>
            <input type="text" name="room_id">
            <label>Room Availability:</label>
            <<select name="room_avail">
                <option value="true">Available</option>
                <option value="false">Not Available</option>
            </select>

            <label>Room description:</label>
            <textarea  name="room_desc" rows="5" cols="10"></textarea>
        </form>
    </body>
</html>
