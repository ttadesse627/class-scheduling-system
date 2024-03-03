<%-- 
    Document   : admin_header
    Created on : Aug 21, 2021, 11:07:36 AM
    Author     : user
--%>
<div class="header">
    <div class="header_nav" id="navLinks">
        <span onclick="document.getElementById('navLinks').style.display='none'" class="close_btn" title="Close" id="closeBtn">&times;</span>
        <div class="header_nav_content navbars">
            <a href="admin.jsp">Admin home</a>
            <a href="department.jsp">Departments</a>
            <a href="course.jsp">Courses</a>
            <a href="room.jsp">Rooms</a>
            <a href="instructor.jsp">Instructors</a>
        </div>
    </div>
    <div class="bars" id="humberger" onclick="myFunction()">
        <a href="#">
            <div></div>
            <div></div>
            <div></div>
        </a>
    </div>
    <div class="chip dropbtn" ondrag="document.getElementById('adminAccount').style.display = 'block';" onclick="myFunc()">
        <img src="../images/img_avatar.png" alt="Person" width="96" height="96" class="picture">
        <%=session.getAttribute("name")%>
        <!--</div>-->
        <div id="myDropdown" class="dropdown-content">
            <span><a href="#" onclick="document.getElementById('adminAccount').style.display = 'block';" title="This will alter your account" style="font-size: 0.8rem;">Update profile</a></span>
            <span class="logout"><a onclick="spinner()" href="../index.jsp" title="Leave the page" id="backtoHome" style="font-size: 0.8rem;">Logout</a></span>
        </div>
    </div>
    <div class="loader" id="loadSpin"></div>
    <div class="account" id="adminAccount">
        <form action="../UpdateAccount"  method="post" enctype="multipart/form-data" class="form_content">
            <span onclick="document.getElementById('adminAccount').style.display = 'none';" class="close_prf" title="Close">&times;</span>
            <h3>Previous Account Information</h3>
            <div class="row">
                <label><b>Previous Id:</b></label>
                <input type="text" name="prev_id" placeholder="Enter your previous id" required>
            </div>
            <hr>
            <div class="row">
                <label> <b>New Id:</b></label>
                <input type="text" name="id" required>
            </div>
            <div class="row">
                <label><b>First Name:</b></label>
                <input type="text" name="firstName" id="firstName" onkeypress="nameValidate(event,this)" required>
            </div>
            <div class="row">
                <label><b>Last Name:</b></label>
                <input type="text" name="lastName" id="lastName" required>
            </div>
            <div class="row">
                <label><b>Username:</b></label>
                <input type="text" name="username" required>
            </div>
            <div class="row">
                <label><b>Password:</b></label>
                <input type="password" name="password" pattern="" required>
            </div>
            <div class="row">
                <input type="reset" value="Reset">
                <input type="submit" value="Update">
            </div>
        </form>
        <script>
            function nameValidate(event,field){
//                var fname = document.getElementById("firstName");
//                var lname = document.getElementById("lastName");
                if((event.keyCode > 65)&&(event.keyCode < 97)){
                    return true;
                }
                else{
                    return false;
                }
            }
        </script>
    </div>
</div>
