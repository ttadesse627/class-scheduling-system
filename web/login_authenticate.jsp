<%-- 
    Document   : logi_authenticate
    Created on : Aug 21, 2021, 12:45:56 PM
    Author     : user
--%>

<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Authentication</title>
        <link rel="stylesheet" href="bootstrap-5.1.0-dist/css/bootstrap.min.css">
    </head>
    <body>
        
        <%@include file="dbconnection.jsp" %>
        <%!
            
        %>
        <%
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            try {
                   sql = " SELECT * FROM administrator where  username = '"+username+"' and password = '"+password+"'";
                   st = con.createStatement();
                   rs = st.executeQuery(sql);
                   while(rs.next()){
                    counter++;
                   }
                    if(counter == 0){
                    
                        msg = "Access denied!";
                        %>
                        <div class="alert alert-danger">
                            <strong><%= msg %></strong> 
                        </div>
                        
                        <div class="w3-clear nextprev">
                            <ul class="pager">
                                <li class="previous"><a href="login_page.jsp">❮ Back</a></li>
                                <li class="next"><a href="index.html">Go to Home</a></li>
                            </ul>
                        </div>


                        <%
                    
                    }
                    else {
                        msg = "Login succeed!";
                        %>
                        <div class="alert alert-success">
                            <strong><%= msg %></strong> 
                        </div>

                        <%
                        response.sendRedirect(response.encodeRedirectURL("admin/admin_page.jsp"));
                    }
                        } catch (SQLException e) {
                        msg = e.getMessage().toString();
                        out.print("<h2 style = 'color:red;'>"+msg+"</h2>");
                        }
                        %>
        
    </body>
</html>
