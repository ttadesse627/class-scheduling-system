<%-- 
    Document   : footer
    Created on : Sep 18, 2021, 2:21:24 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="page-footer">
    <p>This application is just developed by beginner. It is more useful for classes and exams' periods of universities and colleges.</p>
    <p>Copyright &copy; <i id="yr"></i>
    <script>
        var date = new Date();
        var yr = date.getFullYear();
        document.getElementById("yr").innerHTML = yr;
    </script> by Tesfaye Tadesse. All Rights Reserved.</p>
</div>
