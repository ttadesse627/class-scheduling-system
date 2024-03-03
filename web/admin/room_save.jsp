<%-- 
    Document   : room_save
    Created on : Aug 30, 2021, 12:05:29 AM
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
                int block_no =Integer.parseInt(request.getParameter("block_no"));
                int room_no = Integer.parseInt(request.getParameter("room_no"));
                String room_type = request.getParameter("room_type");
                int availability = Integer.parseInt(request.getParameter("availability"));
                String room_id = "B"+block_no+"R"+room_no;
                try {

                        String sql = "Insert into room values(?,?,?,?,?)";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1, room_id);
                        ps.setInt(2, block_no);
                        ps.setInt(3, room_no);
                        ps.setString(4, room_type);
                        ps.setInt(5, availability);
                        int counter = ps.executeUpdate();
                        if(counter >0)
                             out.print("<script type='text/javascript'>alert('Saved!');"
                            + "</script><script>document.location='room.jsp'</script>");
                        else out.print("<script type='text/javascript'>alert('Unable to save!');"
                            + "</script><script>document.location='room.jsp'</script>");
                }catch (Exception e) { 
                    out.print("<script type='text/javascript'>alert('"+e+"');"
                    + "</script><script>document.location='room.jsp'</script>");
                }
            }else{%>
                <script type="text/javascript">
                    window.alert("Connection Failure!");
                    document.location="room.jsp";
                </script>
            <%}
        %>
    </body>
</html>

