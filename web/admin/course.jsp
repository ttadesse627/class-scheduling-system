<%-- 
    Document   : subject
    Created on : Aug 27, 2021, 11:18:14 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="com.university.db.Connect"%>
<%!
    Connect connect = new Connect();
    Connection con = null;
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>course</title>
        <link rel="stylesheet" href="admin_style.css"/>
        <style>
            .row{
                width: 100%;
                height: 80px;
                display: block;
            }
            .row input[type=text],
            .row input[type=number]{
                width: 50%;
                padding: 15px;
                margin: auto 2%;
                display: inline-block;
                border: none;
                background: #f1f1f1;
            }
            .cname span,
            .credit span{
                font-weight: 900;
            }

            /* Add a background color when the inputs get focus */
            input:focus{
                border: 2px solid #000;
                background-color: #ddd;
                outline: #000;
            }
            .clearfix button{
                width: 20%;
                display: inline-block;
            }
            .clearfix button[type=submit],
            .clearfix button[type=reset]{
                margin-left: 15%;
            }
            .modal2_content {
                background-color: #fefefe;
                margin: 5% auto 15% auto;  /*5% from the top, 15% from the bottom and centered */
                border: 1px solid #888;
                width: 80%;  /*Could be more or less, depending on screen size */
            }
            .modal2_content table{
                widows: 50%;
                height: auto;
            }
            td form button{
                width: 20px;
                height: 20px;
                padding: 0 3px 3px 0;
            }
            table {
              border-collapse: collapse;
              border-spacing: 0;
              width: 100%;
              border: 1px solid #ddd;
            }

            th, td {
              text-align: left;
              padding: 8px;
            }

            tr:nth-child(even){background-color: #f2f2f2}
            #myInput{
                background: url('../images/searchicon.png') no-repeat;
                background-position: 10px 12px; /* Position the search icon */
                font-size: 16px; /* Increase font-size */
                padding: 12px 20px 12px 40px; /* Add some padding */
                border: 1px none #ddd; /* Add a grey border */
                margin: auto auto 12px 0; /* Add some space below the input */
                float: left;
                width: 80%;
            }
            
            .admin_btns button{
                margin: auto 5%;
                width: 50px;
            }
            
        </style>
    </head>
    <body>
        <%@include file="admin_header.jsp" %>
        <div class="body_container">
            <div class="admin_btns">
                <button onclick="document.getElementById('id01').style.display='block'" style="width:auto;">Add Course</button>
                <button onclick="document.getElementById('id02').style.display='block'" style="width:auto;">View Courses</button>
            </div>
    <%
        con = connect.getConnection();
        if(con != null){%>
           <div id="id02" class="modal2">
                <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close">&times;</span>
            <%!
                String course_id = null;
            %>
            <div class="modal2_content" style="overflow-x:auto;">
                <input type="text" id="myInput" onkeyup="searchItem()" placeholder="Search for names..">
                <table id="myTable">
                        <tr>
                            <th>No.</th>
                            <th>Course id</th>
                            <th>Course name</th>
                            <th>Contact Hours per Week</th>
                            <th>Update</th>
                        </tr>
                <% 
                    try {
                        String sql = "SELECT * FROM course";
                        Statement st= con.createStatement();
                        ResultSet rs = st.executeQuery(sql);
                        int counter = 0;
                        while (rs.next()) { 
                            counter++;
                            course_id = rs.getString("course_id");
                            String course_name = rs.getString("course_name");
                            int contact_hr = rs.getInt("ects");

                        %>
                        <tr>
                            <td><%=counter%></td>
                            <td><%=course_id%></td>
                            <td><%=course_name%></td>
                            <td><%=contact_hr%></td>
                            <td>
                                <form action="course_del.jsp">
                                    <input type="hidden" name="course_id" value="<%=course_id%>">
                                    <button type="submit" title="delete"><img src="../images/delete.PNG" width="20" height="20" alt="delete"/></button>
                                </form>
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
              <form class="modal-content" action="course_save.jsp" method="post">
                <div class="container">
                  <h1>Add Course</h1>
                  <p>Please fill the following course information.</p>
                  <hr>
                  <div class="cname">
                      <span>Course Name</span>
                      <div class="row">
                        <label for="cid">Course id:</label>
                        <input type="text" placeholder="Enter course id" name="course_id" required>
                      </div>
                      <div class="row">
                        <label for="cname">Course name:</label>
                        <input type="text" placeholder="Enter course name" name="course_name" required>
                      </div>
                  </div>
                  <div class="credit">
                    <span>Course contact hours per week</span>
                    <div class="row">
                        <label for="lechour">Lecture hour/week:</label>
                        <input type="number" placeholder="Enter lecture hour" name="lec_hr" required>
                    </div>
                    <div class="row">
                        <label for="labhour">Lab hour/week:</label>
                        <input type="number" placeholder="Enter lab hour" name="lab_hr" required>
                    </div>
                  </div>
                  <div class="clearfix">
                    <button type="reset" class="save">Reset</button>
                    <button type="submit" class="save">Save</button>
                  </div>
                </div>
              </form>
            </div>
    <%}else{%>
        <script>
            alert("Connection Failure!");
            document.location = "course.jsp";
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


