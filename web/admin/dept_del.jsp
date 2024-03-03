<%-- 
    Document   : dept_delete
    Created on : Aug 25, 2021, 8:04:50 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../dbconnection.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>delete department</title>
    </head>
    <body>
        <%
           
            String dept_id = request.getParameter("dept_id");
            try{
                    String sql = "delete from department where dept_id='"+dept_id+"'";
                    PreparedStatement ps = con.prepareStatement(sql);
                    int counter = ps.executeUpdate();
                    if(counter > 0){
                    out.print("<script type='text/javascript'>alert('deleted!');"
                    + "</script><script>document.location='department.jsp'</script>");
                    }else {
                        out.print("<script type='text/javascript'>alert('could't be deleted!');"
                    + "</script><script>document.location='department.jsp'</script>");
                    }
                }catch (SQLException e) {
                    out.print("<script type='text/javascript'>alert('"+e+"');"
                    + "</script><script>document.location='department.jsp'</script>");
                }
        %>
    </body>
</html>