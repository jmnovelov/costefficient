/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jmnv.costefficient.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.JDBC;

/**
 *
 * @author jmnov
 */
public class ConnectionSingleton {
    
    private static Connection conn = null;
    
    public static Connection getConnection() throws SQLException {
        if (conn == null) {
            initialize();
        }
        
        int retry = 0;
        
        do {
            try {
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute("PRAGMA foreign_keys = on");
                }
                
                return conn;
            } catch (SQLException ex) {
                initialize();
                ++retry;
            }
        } while (retry <3);
        
        throw new SQLException("Maximum create connection retry reached");
    }
    
    private static void initialize() throws SQLException {
        try {
            Class.forName(JDBC.class.getName());
            conn = DriverManager.getConnection("jdbc:sqlite:recipe.db");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}
