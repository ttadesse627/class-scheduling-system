<%-- 
    Document   : section
    Created on : Aug 27, 2021, 3:17:06 PM
    Author     : user
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="com.university.db.Connect"%>
<%!
    Connect connect = new Connect();
    Connection con = null;
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>instructor</title>
        <link rel="stylesheet" href="admin_style.css"/>
        <style>
            input[type=text], input[type=number],input[type=password] {
                width: 80%;
                padding: 15px;
                margin: 5px 0 22px 0;
                display: inline-block;
                border: none;
                background: #f1f1f1;
            }

            /* Add a background color when the inputs get focus */
            input:focus{
                border: 2px solid #000;
                background-color: #ddd;
                outline: #000;
            }
            .inputcheckbox{
                width: 50%;
                height: 200px;
                margin-left: 100px;
                text-align: left;
                overflow-y: scroll;
                border: 1px solid gray;
            }
             /*Add padding to container elements*/ 
            .container {
                padding: 16px;
            }
            #myInput{
               background: url('../images/searchicon.png') no-repeat;
               background-position: 10px 12px; /* Position the search icon */
               font-size: 16px; /* Increase font-size */
               padding: 12px 20px 12px 40px; /* Add some padding */
               border: 1px none #ddd; /* Add a grey border */
               margin-bottom: 12px; /* Add some space below the input */
           }
           
           .assign_course{
                display: none;
                position: fixed; 
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow-y: scroll; 
                background-color: #474e5d;
                padding-top: 50px;
           }
           .row{
               display: block;
               width: 100%;
               margin: 10px 20px;
           }
           .row select,
           .row textarea{
               width: 70%;
           }
        </style>
    </head>
    <body>
        <%@include file="admin_header.jsp" %>
        <div class="body_container">
             <div class="admin_btns">
                <button onclick="document.getElementById('id01').style.display='block'" style="width:auto;">Add Instructor</button>
                <button onclick="document.getElementById('id02').style.display='block'" style="width:auto;">View Instructor</button>
                <button onclick="document.getElementById('id03').style.display='block'" style="width:auto;">Assign Course for Instructor</button>
            </div>    
    <%
        con = connect.getConnection();
        if(con != null){%>
            <div id="id02" class="modal2">
                <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close">&times;</span>
                <%!
                    String courses,course;
                %>
                <div class="modal-content">
                    <input type="text" id="myInput" onkeyup="searchItem()" placeholder="Search for names..">
                    <table id="myTable">
                            <tr>
                                <th>No</th>
                                <th>Instructor Id</th>
                                <th>Instructor Name</th>
                                <th>Instructing Course/s</th>
                            </tr>
                        <%
                            try {

                                String sql = "SELECT * FROM instructor";
                                Statement st= con.createStatement();
                                ResultSet rs = st.executeQuery(sql);
                                int counter = 0;
                                while (rs.next()) {
                                    counter++;
                                    String instr_id = rs.getString("instr_id");
                                    String first_name = rs.getString("first_name");
                                    String last_name = rs.getString("last_name");
                                    String instr_name = first_name+" "+last_name;
                        %>
                            <tr>
                                <td><%=counter %></td>
                                <td><%=instr_id %></td>
                                <td><%=instr_name %></td>
                                <td>
                                    <%try {

                                            String sql1 = "SELECT * FROM `instructor_course` where instr_id='"+instr_id+"'";
                                            Statement st1 = con.createStatement();
                                            ResultSet rs1 = st1.executeQuery(sql1);
                                           // out.print(rs.next());
                                            while(rs1.next()){
                                                course = rs1.getString("course_id");
                                                if(rs1.isLast()) courses = course;
                                                else courses = course+",";
                                    %>
                                                <%= courses%>
                                            <%}
                                            } catch (SQLException e) {%>
                                        <p>Unable to fetch course/s: <%=e%></p>
                                        <%}%>
                                </td>
                            </tr>
                        <%
                                }
                            }catch(SQLException e){
                                out.print("<p>"+e+"</p>");
                            }
                        %>
                    </table>
                </div>
            </div>
            <div id="id01" class="modal">
              <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close">&times;</span>
              <form class="modal-content" action="instructor_save.jsp" method="post">
                <div class="container">
                  <h1>Add Instructor</h1>
                  <p>Please fill the following instructor's information.</p>
                  <hr>
                  <label for="id"><b>Instructor Id:</b></label>
                  <input type="text" placeholder="Enter instructor's id" name="instr_id" required>

                  <label for="fname"><b>First Name:</b></label>
                  <input type="text" placeholder="Enter instructor's first name" name="first_name" required>

                  <label for="lname"><b>Last Name:</b></label>
                  <input type="text" placeholder="Enter instructor's last name" name="last_name" required>

                  <div class="clearfix">
                    <button type="reset" class="save">Reset</button>
                    <button type="submit" class="save">Save</button>
                  </div>
                </div>
              </form>
            </div>
            <div class="assign_course" id="id03">
                <span onclick="document.getElementById('id03').style.display='none'" class="close" title="Close">&times;</span>
                <form class="modal-content" action="instr_course_assign.jsp">
                    <div class="row">
                        <label>Instructor</label>
                        <select name="instr_id" required>
                            <option value="">select instructor</option>
                        <%
                            try {
                                String sql3 = "SELECT instr_id,first_name,last_name FROM instructor";
                                PreparedStatement ps3 = con.prepareStatement(sql3);
                                ResultSet rs3 = ps3.executeQuery();
                                while(rs3.next()){
                                    String id = rs3.getString("instr_id");
                                    String name = rs3.getString("first_name")+" "+rs3.getString("last_name");%>
                                    <option value="<%=id%>"><%=id+"---"+name%></option>
                                <%}
                }catch (SQLException e){%>
                    <script>
                        window.alert("<%=e%>");
                        document.location = "instructor.jsp";
                    </script>
                <%}%>
                        </select>
                    </div>
                    <div class="row">
                        <label><b>Instructor's course/s:</b></label>
                        <div class="inputcheckbox">
                              <% try {
                                      String sql2 = "select * from course";
                                      Statement st2 = con.createStatement();
                                      ResultSet rs2 = st2.executeQuery(sql2);
                                      while (rs2.next()) {
                                          String course_id = rs2.getString("course_id");
                                          String course_name = rs2.getString("course_name");
                              %>
                              <input type="checkbox" name="courses" value="<%=course_id%>"><%=course_name%> <br>
                              <%
                                      }
                                  } catch (Exception e){
                                        out.print("<p>"+e+"</p>");
                                  }
                              %>
                        </div>
                    </div>
                  <div class="clearfix">
                    <button type="reset" class="save">Reset</button>
                    <button type="submit" class="save">Save</button>
                  </div>
                </form>
            </div>
    <%}else{%>
        <script>
            alert("Connection Failure!");
            document.location = "instructor.jsp";
        </script>
    <%}%>    
        </div>
        <%@include file="../footer.jsp" %>
        <script>
            function searchItem() {
              // Declare variables
              var input, filter, table, tr, td, i, txtValue;
              input = document.getElementById("myInput");
              filter = input.value.toUpperCase();
              table = document.getElementById("myTable");
              tr = table.getElementsByTagName("tr");

              // Loop through all table rows, and hide those who don't match the search query
              for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[2];
                if (td) {
                  txtValue = td.textContent || td.innerText;
                  if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                  } else {
                    tr[i].style.display = "none";
                  }
                }
              }
            }
        </script>
        <script src="admin_script.js"></script>
    </body>
</html>
