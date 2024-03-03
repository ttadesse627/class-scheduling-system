<%-- 
    Document   : dept_add
    Created on : Oct 1, 2021, 1:40:23 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.university.db.Connect"%>
<%@page import="java.sql.*"%>
<%!
    Connect connect = new Connect();
    Connection con = null;
%>
<h1>Hello World!</h1>
<%
    int dept_yr,dep_sem;
    String dep_id = request.getParameter("dept_id");
    String dept_name = request.getParameter("dept_name");
    int dept_sem = Integer.parseInt(request.getParameter("dept_sem"));
    con = connect.getConnection();
    if(con !=null){
    try {
        int counter = 0;
        for(int i = 1; i <= dept_sem;i++){
            if(i%2==1)continue;
            else{
                dept_yr = i/2; 
                for(int j = 1; j <= 2; j++){
                    dep_sem = j;
                    String dept_id = dep_id+dept_yr+dep_sem;
                   String sql = "INSERT INTO department values(?,?,?,?,?)";
                   PreparedStatement ps = con.prepareStatement(sql);
                   ps.setString(1, dept_id);
                   ps.setString(2, dept_name);
                   ps.setInt(3, dept_sem);
                   ps.setInt(4, dept_yr);
                   ps.setInt(5, dep_sem);
                   int counter2 = ps.executeUpdate();
                   counter = counter + counter2;
                }
            }
            
        }
        if(counter>=dept_sem){%>
            <script>
                window.alert("Department Added successfully!");
               document.location = "department.jsp";
           </script>
        <%}else {%>
            <script>
                window.alert("Unable to insert department!");
               document.location = "department.jsp";
           </script>
        <%}
    } catch (Exception e) {%>
        <script>
            window.alert("<%=e%>");
           document.location = "department.jsp";
       </script>
    <%}
    }
    else{%>
            <script>
                window.alert("Connection Failure");
               document.location = "department.jsp";
           </script>
    <%}%>
