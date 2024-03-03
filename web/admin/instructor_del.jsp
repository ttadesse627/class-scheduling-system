<%-- 
    Document   : intructor_del
    Created on : Sep 5, 2021, 2:00:42 PM
    Author     : user
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%@page import="com.university.db.Connect" %>
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
           
            String instr_id = request.getParameter("instr_id");
            try{
                    String sql = "delete from instructor where instr_id='"+instr_id+"'";
                    PreparedStatement ps = con.prepareStatement(sql);
                    String sql1 = "delete from instructor_course where instr_id='"+instr_id+"'";
                    PreparedStatement ps1 = con.prepareStatement(sql1);
                    int counter = ps.executeUpdate();
                    int counter1 = ps1.executeUpdate();
                    if((counter > 0) || (counter1 > 0)){
                    out.print("<script type='text/javascript'>alert('deleted!');</script>"
                            +"<script>document.location='instructor.jsp'</script>");
                    }else out.print("could't be deleted!");
                }catch (SQLException e) {
                    out.print("<script type='text/javascript'>alert('"+e+"');</script>"
                            +"<script>document.location='instructor.jsp'</script>");
                }
        %>
    </body>
</html>
