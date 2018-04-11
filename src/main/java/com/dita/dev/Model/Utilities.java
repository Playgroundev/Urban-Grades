/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dita.dev.Model;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.Random;
/**
 *
 * @author brian-kamau
 */
public class Utilities  extends Base{
   /*Polymorphic Functions
    */ 
    
    public final boolean addGrades(String course_code,String admission_no
            ,double initial_score,double item_1,String final_grade,double _final_mark) throws SQLException{
        String sql="";
        boolean success = true;
        try{
            getDatabaseConnection();
            sql="INSERT INTO Grades(Token,Course_Code,Admission_No,Initial_Score,Item_1,Final_Grade,Final_Mark) VALUES(?,?,?,?,?,?,?)";
            
            preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1, generateToken());
            preparedStatement.setString(2,course_code);
            preparedStatement.setString(3,admission_no);
            preparedStatement.setDouble(4,initial_score);
            preparedStatement.setDouble(5,item_1);
            preparedStatement.setString(6, final_grade);
            preparedStatement.setDouble(7, _final_mark);
            preparedStatement.executeUpdate();
            
        }catch(Exception ex){
            success = false;
            ex.printStackTrace();
        }finally{
            closeConnection();
        }       
        return success;
    }
    
    
    public boolean addGrades(String items){
        return true;
    }
    
    public boolean addGrades(String items,String item1){
        return true;
    }
    
    public String generateToken(){
        String token ="";
        try{
            byte[] array = new byte[7];
            new Random().nextBytes(array);
            token = new String(array,Charset.forName("UTF-8"));
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return token;
    }
}
