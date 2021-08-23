<%-- 
    Document   : view_details
    Created on : Aug 22, 2021, 10:51:44 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>view details</title>
        <link rel="stylesheet" href="../bootstrap-5.1.0-dist/css/bootstrap.min.css">
    </head>
    <body>
        <%@page import="java.sql.*" %>
        <%@include file="../dbconnection.jsp" %>
        <h3>Department Details</h3>
        
                
        <div class="table-responsive">
          <table class="table table-bordered">
              <th>Department name</th>
              <th>Department Administrator</th>
              <th>Number of semesters</th>
              <th>Department id</th>
              <th>Update</th>
        <%
            try {
                   sql = " SELECT * FROM department";
                   st = con.createStatement();
                   rs = st.executeQuery(sql);
                   while(rs.next()){
                   String dept_name = rs.getString("dept_name");
                   String dept_admin = rs.getString("privelege_to");
                   int num_of_sems = rs.getInt("num_of_semisters");
                   String dept_id = rs.getString("dept_id");
        %>
              <tr>
                  <td><%= dept_name%></td>
                  <td><%= dept_admin%></td>
                  <td><%= num_of_sems%></td>
                  <td><%= dept_id%></td>
                  <<td><span><a href="url">Edit</a></span><span><a href="url">Delete</a></span></td>
              </tr>
                   <%}%>
                   
          </table>
        </div>
                <%} catch (Exception e) {
                
                }
        %>

        <h3>Classroom Details</h3>
        <div class="table-responsive">
          <table class="table table-bordered">
              <tr>
                  <td></td>
              </tr>

          </table>
        </div>
    </body>
</html>
