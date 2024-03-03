<%-- 
    Document   : instr_course_assign
    Created on : Oct 1, 2021, 5:44:11 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%@page import="com.university.db.Connect" %>
<%!
            Connect connect = new Connect();
            Connection con = null;
            int counter = 0,counter1 = 0,credit_hr = 0;
            int ex_num = 1;
        %>
        <%
            con = connect.getConnection();
        if(con != null){
            String instr_id = request.getParameter("instr_id");
            String[] courses = request.getParameterValues("courses");
            for(int i = 0;i<courses.length; i++){
                try {
                    String sql = "INSERT INTO instructor_course VALUES(?,?) ";
                    String sql2 = "UPDATE instructor SET instr_avail=0 WHERE instr_id =? or room_id = ?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, instr_id);
                    ps.setString(2, courses[i]);
                    int counter = ps.executeUpdate();
                    if(counter > 0){%>
                        <script type="text/javascript">
                            window.alert("Assignment successful!");
                            document.location="instructor.jsp";
                        </script>
                    <%}else{%>
                        <script type="text/javascript">
                            window.alert("Unable to assign!");
                            document.location="instructor.jsp";
                        </script>
                    <%}
                } catch (Exception e) {%>
                            <script type="text/javascript">
                                window.alert("Unable to get credit hour of the courses: "+"<%=e%>");
                                document.location="instructor.jsp";
                            </script>
               <% }
            }
        }else{%>
            <script type="text/javascript">
                window.alert("Connection Failure");
                document.location="instructor.jsp";
            </script>
        <%}%>
