/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.university.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class Connect {
    private Connection con = null;
    private final String dburl = "jdbc:mysql://localhost:3306/scheduler?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private final String dbDriver = "com.mysql.cj.jdbc.Driver";
    private final String dbusername = "root";
    private final String dbpwd = "";
    public Connection getConnection(){
        try {
            Class.forName(dbDriver);
            con = DriverManager.getConnection(dburl,dbusername,dbpwd);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return con;
    }
}
