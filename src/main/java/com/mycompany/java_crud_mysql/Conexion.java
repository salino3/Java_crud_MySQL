/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_crud_mysql;

import java.sql.Connection;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
/**
 *
 * @author flavi
 */
public class Conexion {
    
    private Connection connection = null;
    
    // Cargar las variables de entorno
    public Dotenv dotenv = Dotenv.load();
    
    public String user = dotenv.get("DB_USER");   
    public String password = dotenv.get("DB_PASSWORD"); 
    public String db = dotenv.get("DB_NAME");   
    public String host = dotenv.get("DB_HOST");  
    public String port = dotenv.get("DB_PORT");
    
    public String chain = "jdbc:mysql://" + host + ":" + port + "/" + db;
    
    public Connection  establishConnection () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(chain, user, password);
            JOptionPane.showConfirmDialog(null, "Connected!");
            
           
            
        }catch (Exception e) {
                        JOptionPane.showConfirmDialog(null, "Error connection database, error: " + e.toString());

         }
        return connection;
    }
}
