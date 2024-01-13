/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.homework.services;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HOME
 */
public class JdbcUtils {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(com.homework.services.JdbcUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/quanlysinhvien", "root", "0344142375");
        
    }
}
