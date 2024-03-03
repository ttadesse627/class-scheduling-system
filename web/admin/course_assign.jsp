<%-- 
    Document   : course_assign
    Created on : Sep 9, 2021, 9:28:06 PM
    Author     : user
--%>


<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%@page import="com.university.db.Connect"%>
<%!
    Connect connect = new Connect();
    Connection con = null;
    int counter = 0,counter1 = 0,counter2 = 0,i = 0,ects = 0;
    int ex_num = 1;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <%
            con = connect.getConnection();
            if(con != null){
                String dept_id = request.getParameter("dept_id");
                String[] courses = request.getParameterValues("courses");
                String lec_room = request.getParameter("lec_room");
                String lab_room = request.getParameter("lab_room");
                String[] rooms  = {lec_room,lab_room};
                
                for(int i = 0;i<courses.length; i++){
                    try {
                        String sql = "select ects from course where course_id='"+courses[i]+"'";
                        Statement st = con.createStatement();
                        ResultSet rs =st.executeQuery(sql);
                        if(rs.next()){
                            ects = rs.getInt("ects");
                        }
                    } catch (Exception e) {%>
                        <script type="text/javascript">
                            window.alert("Unable to get credit hour of the courses: "+"<%=e%>");
                            document.location="department.jsp";
                        </script>
                   <% }
                    try {
                        String sql1 = "Insert into dept_course values(?,?,?)";
                        PreparedStatement ps1 = con.prepareStatement(sql1);
                        ps1.setString(1, dept_id);
                        ps1.setString(2, courses[i]);
                        ps1.setInt(3, ects);
                        counter1 = ps1.executeUpdate();
                        if(counter1>0){%>
                            <script type="text/javascript">
                                window.alert("Data saved!");
                                document.location="department.jsp";
                            </script>
                        <%}
                        else{%>
                            <script type="text/javascript">
                                window.alert("Data could not be saved");
                                document.location="department.jsp";
                            </script>
                        <%}
                    }catch (SQLException e) {%>
                        <script type="text/javascript">
                            window.alert("Unable to insert into table dept_course: "+"<%=e%>");
                            document.location="department.jsp";
                        </script>
                   <%}
                }
                for(int i = 0; i<rooms.length; i++){
                    if(rooms[i] != null){
                        try {
                            String sql1 = "Insert into dept_room values(?,?)";
                            PreparedStatement ps1 = con.prepareStatement(sql1);
                            ps1.setString(1, dept_id);
                            ps1.setString(2, rooms[i]);
                            String sql2 = "UPDATE room SET availability=0 WHERE room_id = '"+rooms[i]+"'";
                            counter1 = ps1.executeUpdate();
                            PreparedStatement ps2 = con.prepareStatement(sql2);
                            counter2 = ps2.executeUpdate();
                            if((counter1>0)&&(counter2>0)){%>
                                <script type="text/javascript">
                                    window.alert("Data saved!");
                                    document.location="department.jsp";
                                </script>
                            <%}
                            else{%>
                                <script type="text/javascript">
                                    window.alert("Data could not be saved");
                                    document.location="department.jsp";
                                </script>
                            <%}
                        }catch (SQLException e) {%>
                            <script type="text/javascript">
                                window.alert("Unable to insert into table dept_room: "+"<%=e%>");
                                document.location="department.jsp";
                            </script>
                       <%}
                    }
                }
            }else{%>
                <script type="text/javascript">
                    window.alert("Connection Failure!");
                    document.location="department.jsp";
                </script>
            <%}%>
    </body>
</html>
