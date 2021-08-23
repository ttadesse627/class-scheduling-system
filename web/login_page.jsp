<%-- 
    Document   : newjsp
    Created on : Aug 21, 2021, 12:54:13 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login</title>
         <link rel="stylesheet" href="bootstrap-5.1.0-dist/css/bootstrap.min.css">
    </head>
    <body>
        <%@include file="header.html" %>
        
        
        <form action="login_authenticate.jsp" method="post">
          <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" name="username" class="form-control" id="username">
          </div>
          <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" name="password" class="form-control" id="pwd">
          </div>
          <div class="checkbox">
            <label><input type="checkbox"> Remember me</label>
          </div>
          <button type="submit" class="btn btn-default">Submit</button>
        </form>
            


        
        <script src="bootstrap-5.1.0-dist\js\bootstrap.min.js"></script>
    </body>
</html>
