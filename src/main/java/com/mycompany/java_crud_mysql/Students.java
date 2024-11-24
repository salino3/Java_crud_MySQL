/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_crud_mysql;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
 import java.sql.CallableStatement;

/**
 *
 * @author flavi
 */
public class Students {
    
       int code;
    String name;
    String surnames;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }
    
 public void InsertStudent(JTextField variableName,JTextField variableSurnames) {
     
     setName(variableName.getText());
     setSurnames(variableSurnames.getText());
     
     Conexion objConnection = new Conexion();
     
     String consult = ("insert into students(name, surnames) values (?, ? );");
     
     try {
            CallableStatement cs = objConnection.establishConnection().prepareCall(consult);
         
          cs.setString(1, getName());
            cs.setString(2, getSurnames());
         
         cs.execute();
         
           JOptionPane.showConfirmDialog(null, "Student registered!");
            
     } catch (Exception e) {
                  JOptionPane.showConfirmDialog(null,  "Erro registring student, error. " + e.toString());

     }
     
 }
    
    
}
