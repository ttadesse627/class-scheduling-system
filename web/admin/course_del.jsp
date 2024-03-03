<%-- 
    Document   : course_del
    Created on : Aug 29, 2021, 4:34:47 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%@page import="com.university.db.Connect"%>
<%!
    Connect connect = new Connect();
    Connection con = null;
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
                String course_id = request.getParameter("course_id");
                int counter = 0,counter1 = 0, counter2 = 0;
                try{
                        String sql = "delete from course where course_id='"+course_id+"'";
                        String sql1 = "delete from dept_course where course_id='"+course_id+"'";
                        String sql2 = "delete from instructor_course where course_id='"+course_id+"'";
                        PreparedStatement ps = con.prepareStatement(sql);
                        PreparedStatement ps1 = con.prepareStatement(sql1);
                        PreparedStatement ps2 = con.prepareStatement(sql2);
                        counter = ps.executeUpdate();
                        counter1 = ps1.executeUpdate();
                        counter2 = ps2.executeUpdate();
                        if((counter > 0)||(counter1 > 0)||(counter2 > 0)){%>
                            <script type='text/javascript'>
                                alert("Successfully deleted!");
                                document.location="course.jsp";
                            </script>");
                        <%}else{%>
                            <script type='text/javascript'>
                                alert("Not deleted!");
                                document.location="course.jsp";
                            </script>");
                        <%}
                    }catch (SQLException e){%>
                        <script type='text/javascript'>
                            alert("course table: <%=e%>");
                            document.location="course.jsp";
                        </script>");
                    <%}
            }else{%>
                <script type="text/javascript">
                    window.alert("Connection Failure!");
                    document.location="course.jsp";
                </script>
            <%}%>
    </body>
</html>
