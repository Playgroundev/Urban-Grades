/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dita.dev.Model;
import java.sql.*;
/**
 *
 * @author brian-kamau
 */
public class Utilities  extends Base{
    
    public boolean addGrades(String admission
    ,String course_code,double items,String _final, String _grade) throws SQLException{
        try{
            getDatabaseConnection();
            
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }       
        return true;
    }
}
