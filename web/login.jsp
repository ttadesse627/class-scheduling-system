<%-- 
    Document   : login2
    Created on : Sep 25, 2021, 11:58:01 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="com.university.db.Connect" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login</title>
    </head>
    <body>
        <%!
            Connect db = new Connect();
            Connection con = null;
            int counter = 0;
            String username, password,firstName, lastName, name;
            char lname;
        %>
        <%
            con = db.getConnection();
            if(con != null){
            username = request.getParameter("uname");
            password = request.getParameter("psw");
            try {
                String sql = " SELECT * FROM admin where  username=? AND password=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    counter++;
                    firstName = rs.getString("first_name");
                    lastName = rs.getString("last_name");
                    lname = lastName.charAt(0);
                    name = firstName+" "+lname;
                }

                 if(counter == 0){%>
                 <script>
                     window.alert("Invalid credentials");
                     document.location = "index.jsp";
                 </script>
                 <%}
                 else{
                    session.setAttribute("username", username);
                    session.setAttribute("name", name);
                    response.sendRedirect(response.encodeURL("admin/admin.jsp"));
                 }
            }catch (SQLException e){%>
                <script>
                    window.alert("<%=e%>");
                    document.location = "index.jsp";
                </script>
            <%}
        }else {%>
            <script>
                window.alert("Connection Failure!");
               document.location = "index.jsp";
           </script>
        <%}%>
    </body>
</html>
