/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_crud_mysql;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
 import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;                
import javax.swing.table.DefaultTableModel;  
import javax.swing.table.TableRowSorter;     
import javax.swing.table.TableModel;  
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
 
 public void ShowStudents(JTable tableStudents){
         Conexion objConnection = new Conexion();
        Connection connection = objConnection.establishConnection();

         DefaultTableModel model = new DefaultTableModel();
         TableRowSorter<TableModel> OrderTable = new TableRowSorter<TableModel>(model);
         tableStudents.setRowSorter(OrderTable);
         
         String sql = "";
         
         model.addColumn("id");
         model.addColumn("name");
         model.addColumn("surnames");
         
         tableStudents.setModel(model);
         
          sql = "SELECT * FROM students;";
         
         String[] data = new String[3];
         
         try {
          Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                data[0] = rs.getString(1); 
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);
                
                model.addRow(data);
            }
            
            tableStudents.setModel(model);
                        
          }catch (Exception e){
     JOptionPane.showMessageDialog(null, "Error to showing student list, error: " + e.toString());
     }
 }
    
    public void SelectStudent (JTable tableStudents, JTextField code, JTextField name, JTextField surnames) {
        
        
        try {
            int row = tableStudents.getSelectedRow();
            
            if(row >= 0){
               code.setText(tableStudents.getValueAt(row, 0).toString());
               name.setText(tableStudents.getValueAt(row, 1).toString());
               surnames.setText(tableStudents.getValueAt(row, 2).toString());
            } else {
                JOptionPane.showConfirmDialog(null, "Row no selected");
            }
            
        } catch (Exception e){
               JOptionPane.showConfirmDialog(null, "Error: " + e.toString());
        }
    }
}
