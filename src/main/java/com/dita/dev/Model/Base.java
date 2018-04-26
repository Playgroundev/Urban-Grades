/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dita.dev.Model;

import javax.swing.*;
import java.io.File;
import java.sql.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author brian-kamau
 */
public abstract class Base {
    Properties properties = new Properties();
    InputStream inputStream;
    protected Connection connection = null;
    protected Statement statement = null;
    protected ResultSet result = null;
    protected PreparedStatement preparedStatement = null;
    private String Url  = "jdbc:mysql://localhost:3306/"+getDatabaseCredentials().get(0)+"?useSSL=false";
    
    protected Base(){
        String sql="";
                
        try{
            getDatabaseConnection();
            statement = connection.createStatement();
            sql="CREATE TABLE IF NOT EXISTS Grades("
                    + "id int not null auto_increment,"
                    + "Token varchar(25) not null,"
                    + "Course_Code varchar(12) not null,"
                    + "Admission_No varchar(15) not null,"
                    + "Initial_Score double not null,"
                    + "Item_1 double,"
                    + "Item_2 double,"
                    + "Item_3 double,"
                    + "Final_Grade varchar(5) not null,"
                    + "Final_Mark double not null,"
                    + "unique(Token),"
                    + "primary key(id))";
            statement.addBatch(sql);
            statement.executeBatch();
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Check Database Credentials");
            ex.printStackTrace();
        }finally{
            
            closeConnection();
        }
        
    }
 
    public boolean getDatabaseConnection(){
        boolean success = true;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            connection = DriverManager.getConnection(Url, getDatabaseCredentials().get(1),getDatabaseCredentials().get(2));
        }catch(Exception ex){
            success = false;
            ex.printStackTrace();
        }
        return success;
    }
    public boolean closeConnection(){
       boolean closed = true;
        try{
            if(!connection.isClosed()){
                connection.close();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return closed;
    }
    
    public  ArrayList<String> getDatabaseCredentials(){
        ArrayList<String> details = new ArrayList<>();
        try{
            File file = new File("database.properties");
            inputStream = new FileInputStream(file);
            properties.load(inputStream);
            details.add(properties.getProperty("DATABASE_NAME"));
            details.add(properties.getProperty("DATABASE_USERNAME"));
            details.add(properties.getProperty("DATABASE_PASSWORD"));
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Please Configure Database To Continue");
            ex.printStackTrace();
        }
        return details;
    }
}
