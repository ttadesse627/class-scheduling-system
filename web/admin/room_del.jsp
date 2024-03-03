<%-- 
    Document   : room_del
    Created on : Aug 30, 2021, 12:05:14 AM
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
                String room_id = request.getParameter("room_id");
                try{
                    String sql = "delete from room where room_id='"+room_id+"'";
                    String sql1 = "delete from dept_room where room_id='"+room_id+"'";
                    PreparedStatement ps = con.prepareStatement(sql);
                    PreparedStatement ps1 = con.prepareStatement(sql);
                    int counter = ps.executeUpdate();
                    int counter1 = ps1.executeUpdate();
                    if((counter > 0)||(counter1 > 0)){
                    out.print("<script type='text/javascript'>alert('deleted!');"
                    + "</script><script>document.location='room.jsp'</script>");
                    }else out.print("<script type='text/javascript'>alert('could't be deleted!');"
                        + "</script><script>document.location='room.jsp'</script>");
                }catch (SQLException e) {
                    out.print("<script type='text/javascript'>alert('"+e+"');"
                    + "</script><script>document.location='room.jsp'</script>");
                }
            }else{%>
                <script type="text/javascript">
                    window.alert("Connection Failure!");
                    document.location="room.jsp";
                </script>
            <%}%>
    </body>
</html>
