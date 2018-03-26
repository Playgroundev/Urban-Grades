/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dita.dev.Model;

import java.io.File;
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
