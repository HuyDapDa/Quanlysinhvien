/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.homework.runDemo;

import com.homework.services.Menu;
import java.sql.SQLException;
import java.text.ParseException;

/**
 *
 * @author HOME
 */
public class runDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ParseException {
        // TODO code application logic here
        Menu menu = new Menu();
        menu.Menu();
    }
    
}
