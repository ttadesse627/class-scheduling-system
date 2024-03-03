<%-- 
    Document   : about
    Created on : Sep 15, 2021, 9:11:57 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>about</title>
        <link rel="stylesheet" href="home_style.css"/>
        <style>
            .bars a:hover{
                width: 45px;
                height: 35px;
            }
            .body_container{
                text-align: left;
                padding: auto auto auto 15%;
            }
            .text{
                margin-left: 15%;
            }
        </style>
    </head>
    <body>
        <%@include file="home_header.html" %>
        <div class="body_container">
            <div class="text">
                <h2>About Developer</h2>
                <p>This application is used to generate class and exam schedules.
                    And is developed by Tesfaye Tadesse;<br>
                    a student of Software Engineering (Bachelor degree third year)  
                    at Jimma University  Jimma,Oromia,Ethiopia.  </p>
                <h2>About Application</h2>
                <p>This application is my first web application <br><i>Version 1.0 (Official Build)</i> </p>
            </div>
        </div>
        <%@include file="footer.jsp" %>
        <script src="home_script.js"></script>
    </body>
</html>
