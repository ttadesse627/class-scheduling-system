<%-- 
    Document   : services
    Created on : Sep 15, 2021, 9:11:00 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Services</title>
        <link rel="stylesheet" href="home_style.css"/>
        <style>
            .body_container h1,p {
                text-align: left;
                margin-left: 5%;
            }
            ul{
                margin: auto 6%;
                text-align: left;
            }
            ul li{
                font-size: 0.9rem;
                font-style: inherit;
                list-style-type: lower-roman;
                line-height: 25px;
            }
            .bars a:hover{
                width: 45px;
                height: 35px;
            }
        </style>
    </head>
    <body>
        <%@include file="home_header.html" %>
        <div class="body_container">
            <h1>Application Services</h1>
            <p><b>Some of the application services are:</b></p>
            <ul>
                <li>Generating the schedule for lecture classes and exams.</li>
                <li>Viewing all courses in course list</li>
                <li>Adding new courses to course lists</li>
                <li>Adding specific courses to the department</li>
                <li>Registering instructors and assigning the courses to them</li>
                <li>Adding new rooms to lecture room lists and <i>&ExponentialE;&tcaron;&cacute;</i></li>
            </ul>
        </div>
        <%@include file="footer.jsp" %>
        <script src="home_script.js"></script>
    </body>
</html>
