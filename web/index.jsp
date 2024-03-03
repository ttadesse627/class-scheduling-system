<%-- 
    Document   : index
    Created on : Aug 25, 2021, 7:06:51 AM
    Author     : user
--%>


    <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>home</title>
            <link rel="stylesheet" href="home_style.css" />
            <style>
                .bars a:hover {
                    width: 45px;
                    height: 35px;
                }
                
                #showPwd {
                    margin-left: -75px;
                    margin-top: 10px;
                    position: absolute;
                }
            </style>
            <script>
                function togglePwd() {
                    var x = document.getElementById('psw');
                    var y = document.getElementById("showPwd");
                    if (x.type === 'password') {
                        y.title = 'hide password';
                        x.type = 'text';
                    } else {
                        y.title = "show password";
                        x.type = 'password';
                    }
                }
            </script>
        </head>

        <body>
            <%@include file="home_header.html"%>
                <div class="body_container">
                    <div id="id01" class="modal">
                        <form class="modal-content animate" action="login.jsp" method="post">
                            <div class="imgcontainer">
                                <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
                                <img src="images/img_avatar.png" alt="Avatar" class="avatar">
                            </div>
                            <div class="container">
                                <div class="username">
                                    <label for="uname"><b>Username</b></label>
                                    <input type="text" placeholder="Enter Username" name="uname" required>
                                </div>
                                <div class="password">
                                    <label for="psw"><b>Password</b></label>
                                    <input type="password" placeholder="Enter password" id="psw" name="psw" required>
                                    <img src="images/show_pwd.PNG" alt="Show Password" id="showPwd" onclick="togglePwd()" title="show password" />
                                </div>
                                <div class="login_btns">
                                    <button type="reset"><b>Reset</b></button>
                                    <button type="submit"><b>Login</b></button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="login-box">
                        <p>Just click <b>Login</b> button and fill the necessary information to get into schedules generator</p>
                        <button onclick="document.getElementById('id01').style.display='block'" style="width:15%; height: 44px; right: 25px; background-color: #00ff22;" id="loginBtn"><b style="font-size: 1.5rem;">Login</b></button>
                    </div>
                    <p id="message"></p>
                </div>
                <%@include file="footer.jsp"%>
                    <script src="home_script.js"></script>
        </body>

        </html>