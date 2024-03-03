<%--
    Document   : dept_management
    Created on : Aug 22, 2021, 10:50:25 AM
    Author     : user
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%@page import="com.university.db.Connect"%>
<%!
    Connect connect = new Connect();
    Connection con = null;
    String dept_id,dept_name,hidden_id;
    int dept_yr,yr,dept_sem,sem;
    String course_id,course_name;
    int credit_hr; 
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>department</title>
        <link rel="stylesheet" href="admin_style.css"/>
        <style>
            /*Add department form*/
            .add_dept_modal{
                display: none;
                position: fixed; 
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto; 
                background-color: #474e5d;
                padding-top: 50px;
            }

            .add_dept_modal .modal-content {
                background-color: #fefefe;
                margin: 5% auto 15% auto;  /*5% from the top, 15% from the bottom and centered */
                border: 1px solid #888;
                width: 80%;  /*Could be more or less, depending on screen size */
            }
            .inputs{
                text-align: left;
            }
            .modal-content{
                padding-top: 30px;
            }
            .inputs .row{
                display: block;
                width: 100%;
                margin: 20px auto;
            }
            .row label{
                text-align: left;
            }
            .row input,
            .row label{
                display: inline;
            }
            h3{
                margin: auto 5%;
            }
            .row label{
                width: 25%;
                margin: auto 5%;
            }
            .row input[type=text],
            .row input[type=number]{
                width: 50%;
                height: 35px;
            }
            .row input{
                margin-left: 1%;
            }
            .row input:focus{
                border: 2px solid #000;
            }
            
            .admin_btns{
                display: inline-block;
                width: 100%;
                height: 80px;
            }
    .admin_btns form{
        display: inline-block;
        width: 300px;
        height: 50px;
        border-radius: 10%;
        font-size: 1.1rem;
    }
    .admin_btns button{
        display: inline-block;
        width: 100px;
        height: 50px;
        border-radius: 10%;
        font-size: 1.1rem;
    }
        </style>
    </head>
    <body>
        <%@include file="admin_header.jsp"%>
        <div class="body_container">
            <div class="admin_btns">
                <button onclick="document.getElementById('id01').style.display='block'" style="width:auto;">Add Course to departments</button>
                <form action="../ClassSchedule" method="post">
                    <button type="submit" style="width:auto;">Display Class Schedule</button>
                </form>
                <form action="../ExamSchedule" method="post">
                    <button type="submit" style="width:auto;">Display Exam Schedule</button>
                </form>
                <button onclick="document.getElementById('id02').style.display='block'" style="width:auto;">Add new department</button>
            </div>
<%
    con = connect.getConnection();
    if(con != null){%>
            <div id="id01" class="modal">
                <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
                <form class="modal-content" action="course_assign.jsp" method="post">
                    <div class="container">
                        <h1>Add Course</h1>
                        <hr>
                        <label><b>Department:</b></label>
                        <p style="font-size: 0.7rem;"><em> Hint:</em>The last two digits are year number and semester number respectively.</p>
                        <select name="dept_id" class="field" required>
                            <option value="">Select department -</option>
                        <%
                            try {
                                String sql = "select * from department";
                                Statement st = con.createStatement();
                                ResultSet rs = st.executeQuery(sql);
                                while(rs.next()) {
                                    dept_id = rs.getString("dept_id");
                                    dept_yr = Integer.valueOf(rs.getInt("dept_yr"));
                                    dept_sem = Integer.valueOf(rs.getInt("dep_sem"));
                        %>
                        <option value="<%= dept_id%>"><%= dept_id%></option>
                                <%}%>
                        </select>
              <%
                } catch (Exception e) {%>
                    <script type="text/javascript">
                        window.alert("<%=e%>");
                        document.location="department.jsp";
                    </script>
                <%}%>
                        <label><b>Courses: </b></label>
                        <div class="input_cont" style="text-align: left;">
                    <%
                        try {
                            String sql1 = "Select * from course";
                            Statement st1 = con.createStatement();
                            ResultSet rs1 = st1.executeQuery(sql1);
                            while(rs1.next()){
                                course_id = rs1.getString("course_id");
                                course_name = rs1.getString("course_name");;
                    %>
                    <input type="checkbox" name="courses" value="<%=course_id%>" class="field"/><%=course_name%><br>
                                <%}
                } catch (Exception e) {%>
                    <script type="text/javascript">
                        window.alert("<%=e%>");
                        document.location="department.jsp";
                    </script>
                <%}%>
                        </div>
                        <div>
                            <ol id="list">
                                
                            </ol>
                        </div>
                        <div class="lect_room">
                            <label>Lecture Room</label>
                            <select name="lec_room">
                            <option value="">Select lecture room -</option>
                        <%
                            try {
                                String sql = "select * from room where room_type='lecture' and availability=1";
                                Statement st = con.createStatement();
                                ResultSet rs = st.executeQuery(sql);
                                while(rs.next()) {
                                    String room_id = rs.getString("room_id");
                        %>
                        <option value="<%= room_id%>"><%= room_id%></option>
                                <%}%>
                        <%
                          } catch (Exception e) {%>
                              <script type="text/javascript">
                                  window.alert("<%=e%>");
                                  document.location="room.jsp";
                              </script>
                          <%}%>
                            </select>
                        </div>
                        <div class="lab_room">
                            <label>Lab Room</label>
                            <select name="lab_room">
                            <option value="">Select lab room -</option>
                        <%
                            try {
                                String sql = "select * from room where room_type='lab' and availability='1'";
                                Statement st = con.createStatement();
                                ResultSet rs = st.executeQuery(sql);
                                while(rs.next()) {
                                    String room_id = rs.getString("room_id");
                        %>
                        <option value="<%= room_id%>"><%= room_id%></option>
                                <%}%>
                        <%
                          } catch (Exception e) {%>
                              <script type="text/javascript">
                                  window.alert("<%=e%>");
                                  document.location="room.jsp";
                              </script>
                          <%}%>
                            </select>
                        </div>
                        
                    <div class="clearfix">
                        <button type="reset" class="resetbtn">Reset</button>
                        <button type="submit" class="save">Save</button>
                    </div>
                  </div>
                </form>
            </div>
            <div id="id02" class="add_dept_modal">
              <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close">&times;</span>
              <form class="modal-content" action="dept_add.jsp" method="post">
                <div class="container">
                  <h1>Add Department</h1>
                  <p>Please fill the following department's information.</p>
                  <hr>
                  <div class="inputs">
                        <div class="row">
                              <label for="id"><b>Department Id:</b></label>
                              <input type="text" placeholder="Enter department's id" name="dept_id" required>
                        </div>
                        <div class="row">
                              <label for="deptname"><b>Department Name:</b></label>
                              <input type="text" placeholder="Enter department's name" name="dept_name" required>
                        </div>
                        <div class="row">
                              <label for="lyear"><b>Department's long lasting semesters:</b></label>
                              <input type="number" placeholder="Enter department's semesters" name="dept_sem" required>
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
        <script type="text/javascript">
            window.alert("Connection Failure!");
            document.location="department.jsp";
        </script>
    <%}%>
        </div>
        <%@include file="../footer.jsp" %>
        <script src="admin_script.js"></script>
    </body>
</html>
