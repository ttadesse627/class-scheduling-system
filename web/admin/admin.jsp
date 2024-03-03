<%-- 
    Document   : admin_page
    Created on : Aug 21, 2021, 12:10:21 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrations</title>
        <link rel="stylesheet" href="admin_style.css"/>
        <style>
            .body_container{
                padding-top: 2%;
            }
            .content{
                width: 80%;
                margin-top: 0px;
                margin-left: 10%;
            }
            dl{
                text-align: left;
                line-height: 50px;
            }
            dt{
                font-size: 18px;
            }
            dd{
                line-height: 27px;
                padding: auto 15px auto auto;
                font-size: 0.9rem;
            }
            .steps{
                text-align: left;
            }
        </style>
    </head>
    <body>
        <%@include file="admin_header.jsp" %>
        <div class="body_container">
            <div class="content">
                <div class="steps">
                    <h5>Steps to manage the application</h5>
                    <ol>
                        <li>Go to course link and Add Course if it is not available in the database</li>
                        <li>Go to room link and Add Room if not available</li>
                        <li>Go to instructor link and assign course to instructor</li>
                        <li>Go to department link and assign courses and rooms to department</li>
                        <li>Then display schedules</li>
                    </ol>
                    <p>You can add new department whenever you like.</p>
                </div>

                <dl>
                    <dt><b>Departments</b></dt>
                    <dd>Under Departments link, you can assign the courses those belong to the given 
                        department of specific semester within a specific year by selecting a batch those fetched 
                    from database.</dd>
                    <dd> You can display class as well as exam schedules for all departments those have assigned courses. <br> <i style="font-size: 0.8em; text-indent: 10px;">Hint: The department that is displayed while selecting is not full name; it is an id that is combination of department name, its year and specific semester </i> </dd>
                    <dt><b>Courses</b></dt>
                    <dd>Under here, you can add the courses to the list for future assign for department and instructors and also you can delete course from the list</dd>
                    <dt><b>Rooms</b></dt>
                    <dd>Under Rooms link, you can add new rooms and also you can delete the lecture rooms from the list if you have decided  to exclude it from lecture rooms.</dd>
                    <dt><b>Instructors</b></dt>
                    <dd>Under this link, you can add new instructors and assign them to specific courses. You can assign multiple courses for instructor. </dd>

                </dl>
            </div>
        </div>
        <%@include file="../footer.jsp" %>
        <script src="admin_script.js"></script>
    </body>
</html>
