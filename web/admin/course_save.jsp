<%-- 
    Document   : sub_save
    Created on : Aug 29, 2021, 5:05:14 PM
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
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>course</title>
    </head>
    <body>

        <%@include file="admin_header.jsp" %>
    <%
        con = connect.getConnection();
        if(con != null){
            String course_id = request.getParameter("course_id");
            String course_name = request.getParameter("course_name");
            int lec_hr = Integer.parseInt(request.getParameter("lec_hr"));
            int lab_hr = Integer.parseInt(request.getParameter("lab_hr"));
            int credit_hr = lec_hr + lab_hr;
            try {
                String sql = "Insert into course values (?,?,?,?,?)";
                PreparedStatement ps= con.prepareStatement(sql);
                ps.setString(1, course_id);
                ps.setString(2, course_name);
                ps.setInt(3, credit_hr);
                ps.setInt(4, lec_hr);
                ps.setInt(5, lab_hr);
                int i = ps.executeUpdate();
                if(i>0){
                  out.print("<script type='text/javascript'>alert('Saved!');</script>"
                  + "<script>document.location='course.jsp'</script>");
                }
                else{
                      out.print("<script type='text/javascript'>alert('failed!');</script>"
                  + "<script>document.location='course.jsp'</script>");
                }
            } catch (SQLException e) {
                  out.print("<script type='text/javascript'>alert('"+e+"');</script>"
                  + "<script>document.location='course.jsp'</script>");
            }
        }else{%>
            <script type="text/javascript">
                window.alert("Connection Failure!");
                document.location="course.jsp";
            </script>
        <%}%>
    </body>
</html>
