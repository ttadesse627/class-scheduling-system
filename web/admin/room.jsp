<%-- 
    Document   : room
    Created on : Aug 29, 2021, 11:53:48 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%@page import="com.university.db.Connect" %>
<%!
    Connect connect = new Connect();
    Connection con = null;
    int room_no,block_no;
    String room_id,room_type;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>rooms</title>
        <link rel="stylesheet" href="admin_style.css"/>
        <style>
            input[type=number]{
                width: 80%;
                padding: 15px;
                margin: 5px 0 22px 0;
                display: inline-block;
                border: none;
                background: #f1f1f1;
            }

            /* Add a background color when the inputs get focus */
            input:focus{
                border: 2px solid #000;
                background-color: #ddd;
                outline: #000;
            }
            #myInput{
                width: 100%;
                background: url('../images/searchicon.png') no-repeat;
                background-position: 10px 12px; /* Position the search icon */
                font-size: 16px; /* Increase font-size */
                padding: 12px 20px 12px 40px; /* Add some padding */
                border: 1px none #ddd; /* Add a grey border */
                margin-bottom: 12px; /* Add some space below the input */
            }
           .admin_btns button{
                margin: auto 5%;
                width: 50px;
            }
            td form button{
                width: 20px;
                height: 20px;
                padding: 0 3px 3px 0;
            }
        </style>
    </head>
    <body>
        <%@include file="admin_header.jsp" %>
        <div class="body_container">
            <div class="admin_btns">
                <button onclick="document.getElementById('id01').style.display='block'" style="width:auto;">Add Room</button>
                <button onclick="document.getElementById('id02').style.display='block'" style="width:auto;">View Rooms</button>
            </div>
            <%
                con = connect.getConnection();
                if(con != null){%>
            <div id="id02" class="modal2">
                <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close">&times;</span>

                <div class="modal-content">
                    <input type="text" id="myInput" onkeyup="searchItem()" placeholder="Search for names..">
                    <table id="myTable">
                           <tr>
                               <th>No.</th>
                               <th>Room Id </th>
                               <th>Block Number</th>
                               <th>Room Number</th>
                               <th>Room Type</th>
                               <th>Update</th>
                          </tr>
                            <%
                                try {
                                        String sql = "select * from room";
                                        Statement st = con.createStatement();
                                        ResultSet rs = st.executeQuery(sql);
                                        int counter = 0;
                                        while(rs.next()) {
                                            counter = counter+1;
                                            room_id = rs.getString("room_id");
                                            block_no = rs.getInt("block_no");
                                            room_no = rs.getInt("room_no");
                                            room_type = rs.getString("room_type");
                            %>
                            <tr>
                                <td><%= counter%></td>
                                <td><%= room_id %></td>
                                <td><%= block_no %></td>
                                <td><%= room_no%></td>
                                <td><%= room_type%></td>
                                <td>
                                    <form action="room_del.jsp">
                                        <input type="hidden" name="room_id" value="<%=room_id%>">
                                        <button type="submit" title="delete"><img src="../images/delete.PNG" width="20" height="20" alt="delete"/></button>
                                    </form>
                                </td>
                            </tr>
                        <%
                                }
                            }catch(SQLException e){%>
                            <tr><td><%= e %></td></tr>
                            <%}

                        %>
                    </table>
                </div>
            </div>
            <div id="id01" class="modal">
              <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
              <form class="modal-content" action="room_save.jsp" method="post">
                <div class="container">
                  <h1>Add Room</h1>
                  <p>Please fill the following room information.</p>
                  <hr>
                  <label for="bno"><b>Block No.</b></label>
                  <input type="number" placeholder="Enter block number of the room" name="block_no" required>

                  <label for="rno"><b>Room No.</b></label>
                  <input type="number" placeholder="Enter room number of the room" name="room_no" required>
                  
                  <label for="rtype"><b>Room type:</b></label>
                  <select name="room_type" required>
                      <option value="">select room type</option>
                      <option value="lecture">Lecture room</option>
                      <option value="lab">Lab room</option>
                  </select>
                  <input type="hidden" name="availability" value="1">
                  <div class="clearfix">
                    <button type="button" class="resetbtn">Reset</button>
                    <button type="submit" onclick="roomva" class="save">Save</button>
                  </div>
                </div>
              </form>
            </div> 
                <%}else{%>
                    <script type="text/javascript">
                        window.alert("Connection Failure!");
                        document.location="room.jsp";
                    </script>
                <%}%>
        </div>
            
        <%@include file="../footer.jsp" %>
        <script>
            function searchItem() {
              // Declare variables
              var input, filter, table, tr, td, i, txtValue;
              input = document.getElementById("myInput");
              filter = input.value.toUpperCase();
              table = document.getElementById("myTable");
              tr = table.getElementsByTagName("tr");

              // Loop through all table rows, and hide those who don't match the search query
              for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[i];
                if (td) {
                  txtValue = td.textContent || td.innerText;
                  if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                  } else {
                    tr[i].style.display = "none";
                  }
                }
              }
            }
            
        </script>
        <script src="admin_script.js"></script>
    </body>
</html>