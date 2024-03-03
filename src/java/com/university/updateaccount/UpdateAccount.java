/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.university.updateaccount;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import java.sql.*;
import com.university.db.Connect;
import java.io.InputStream;



/**
 *
 * @author user
 */

public class UpdateAccount extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Connection con = null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connect db = new Connect();
        con = db.getConnection();
        response.setContentType("text/html;charset=UTF-8");
        String prev_id = request.getParameter("prev_id");
        String id = request.getParameter("id");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter(); 

        if(con != null){
            try{
                String sql = "UPDATE admin SET id='"+id+"',first_name='"+firstName+"',last_name='"+lastName+"',username='"+username+"',password='"+password+"' WHERE id='"+prev_id+"'";
                Statement st = con.createStatement();
                int i = st.executeUpdate(sql);
                if(i > 0){
                    out.print("<script>"
                            + "alert('Updated successfully!');"
                            + "document.location = 'admin/admin.jsp'"
                            + "</script>");
                }
                else{
                    out.print("<script>"
                            + "alert('COuldn't be updated!');"
                            + "document.location = 'admin/admin.jsp'"
                            + "</script>");
                }
            }catch(SQLException sqle){
                out.print("<script>"
                        + "alert('"+sqle+"');"
                        + "document.location = 'admin/admin.jsp';"
                        + "</script>");
            }
        }else{
            out.print("<script>"
                    + "alert('Connection Failure');"
                    + "document.location = 'admin/admin.jsp';"
                    + "</script>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
