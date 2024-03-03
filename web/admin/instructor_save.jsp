<%-- 
    Document   : instructor_save
    Created on : Sep 5, 2021, 12:08:38 PM
    Author     : user
--%>

<%@page import="javax.servlet.jsp.tagext.IterationTag"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>
<%@page import="java.sql.*" %>
<%@page import="com.university.db.Connect" %>
<%!
    Connect connect = new Connect();
    Connection con = null;
    int counter = 0,counter1 = 0;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>course management</title>
    </head>
    <body>
        <%
            con = connect.getConnection();
            if(con != null){
                String instr_id = request.getParameter("instr_id");
                String first_name = request.getParameter("first_name");
                String last_name = request.getParameter("last_name");
                String instr_name = first_name+" "+last_name;
                String[] course_id = request.getParameterValues("course");
                try {

                        String sql = "Insert into instructor values(?,?,?)";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1, instr_id);
                        ps.setString(2, first_name);
                        ps.setString(3, last_name);
                        counter = ps.executeUpdate();
                        if(counter > 0)
                             out.print("<script type='text/javascript'>alert('Instructor account Saved!');"
                            + "</script><script>document.location='instructor.jsp'</script>");
                        else out.print("<script type='text/javascript'>alert('Unable to save Instructor account!');"
                            + "</script><script>document.location='instructor.jsp'</script>");
                        for(int i = 0; i < course_id.length; i++) {
                            try {
                                String sql1 = "Insert into instructor_course values(?,?)";
                                PreparedStatement ps1 = con.prepareStatement(sql1);
                                ps1.setString(1, instr_id);
                                ps1.setString(2, course_id[i]);
                                counter1 = ps1.executeUpdate();
                            }catch (SQLException e) { 
                                out.print("<script type='text/javascript'>alert('Course Assignment error: "+e+"');"
                                + "</script><script>document.location='instructor.jsp'</script>");
                            }

                        }
                                if(counter1 > 0)
                                     out.print("<script type='text/javascript'>alert('You assigned instructor to course!');"
                                    + "</script><script>document.location='instructor.jsp'</script>");
                                else out.print("<script type='text/javascript'>alert('Unable to assign the instructor to course!');"
                                    + "</script><script>document.location='instructor.jsp'</script>");
                    }catch (Exception e) { 
                        out.print("<script type='text/javascript'>alert('"+e+"');"
                        + "</script><script>document.location='instructor.jsp'</script>");
                    }

                }else{%>
                    <script>
                        alert("Connection Failure!");
                        document.location = "instructor.jsp";
                    </script>
                <%}%> 

                %>
        
    </body>
</html>
