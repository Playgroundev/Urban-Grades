package com.dita.dev.Model

import java.lang.Exception
import java.sql.SQLException
import java.util.*

class Utils : Base() {

    @Throws(SQLException::class)
    fun saveToDatabase(course_code:String,admission_no:String,initial_score:Double,
                       item_1:Double,final_grade:String,_final_mark:Double): Boolean{
        var success :Boolean = true

        try{
            databaseConnection
            val sql :String = "INSERT INTO Grades(Token,Course_Code,Admission_No,Initial_Score,Item_1,Final_Grade,Final_Mark) VALUES(?,?,?,?,?,?,?)"
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, generateToken());
            preparedStatement.setString(2,course_code);
            preparedStatement.setString(3,admission_no);
            preparedStatement.setDouble(4,initial_score);
            preparedStatement.setDouble(5,item_1);
            preparedStatement.setString(6, final_grade);
            preparedStatement.setDouble(7, _final_mark);
            preparedStatement.executeUpdate();

        }catch(ex:Exception ){
            success =false
            ex.printStackTrace();
        }finally {
            closeConnection();
        }

        return success;
    }
    fun generateToken() : String {
        var Token =""
        val chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

        try{
            for(i in 0..31){
                Token += chars[Math.floor(Math.random() * chars.length).toInt()]
            }

        }catch (ex:Exception){
            ex.printStackTrace()
        }
        return Token.substring(0,24)
    }
}