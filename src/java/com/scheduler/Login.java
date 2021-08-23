/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
public class Login extends HttpServlet {

            Connection con = null;
            String dburl = "jdbc:mysql://localhost:3306/scheduler?zeroDateTimeBehavior=CONVERT_TO_NULL";
            String driver = "com.mysql.cj.jdbc.Driver";
            String dbusername = "root";
            String dbpassword = "";
            String msg = null;
            PreparedStatement ps;
            Statement st;
            ResultSet rs;
            String sql;
            int counter = 0;
            
            public void init(){
            try {
                   Class.forName(driver);
                   con = DriverManager.getConnection(dburl,dbusername,dbpassword);
                   
                } catch (ClassNotFoundException e) {
                    msg= e.getMessage().toString();
                    System.out.println(msg);
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try  {
            PrintWriter out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
            String username = request.getParameter("username");
            String password = request.getParameter("username");
                   sql = " SELECT * FROM administrator where  username = '"+username+"' and password = '"+password+"'";
                   st = con.createStatement();
                   rs = st.executeQuery(sql);
                   
            while(rs.next()){
                counter++;
            }
            if(counter == 0){
                response.sendRedirect(response.encodeRedirectURL("login_page.jsp"));
            }
            else{
                response.sendRedirect(response.encodeRedirectURL("admin_page.jsp"));
            }
        }catch(IOException e){
            msg = e.getMessage().toString();
            System.out.println(msg);
        }       catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
