/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dita.dev.Model;

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
    public static String Url  = "jdbc:mysql://localhost:3306/dummy?useSSL=false";
    
    protected Base(){
        
    }
 
    public boolean getDatabaseConnection() throws SQLException{
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
    public boolean closeConnection() throws SQLException{
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
    
    public ArrayList<String> getDatabaseCredentials() throws IOException{
        ArrayList<String> details = new ArrayList<>();
        try{
            File file = new File("database.properties");
            inputStream = new FileInputStream(file);
            properties.load(inputStream);
            details.add(properties.getProperty("DATABASE_NAME"));
            details.add(properties.getProperty("DATABASE_USERNAME"));
            details.add(properties.getProperty("DATABASE_PASSWORD"));
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return details;
    }
}
