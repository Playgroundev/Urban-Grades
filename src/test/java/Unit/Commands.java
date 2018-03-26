/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author brian-kamau
 */
public class Commands {
    
    public Commands() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testCommandCLi(){
        String command = "mysql -u root -p";
        String output = executeCommand(command);
    }
    private String executeCommand(String command){
        StringBuffer output = new StringBuffer();
        
        Process process;
        try{
            process = Runtime.getRuntime().exec(command);
            process.waitFor();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";			
	while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return output.toString();
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
