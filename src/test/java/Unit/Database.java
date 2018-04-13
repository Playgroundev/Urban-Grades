package Unit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.dita.dev.Model.Base;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dita.dev.Model.Utils;
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
public class Database {
    
    public Database() {
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
    public void testFileSystem() throws IOException{
        System.out.println("Testing File SystemHandling");
        Base Implementation = new DatabaseImplementation();
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> expectedResult = new ArrayList<>();
        for(int i = 1;i<=3;i++){
            expectedResult.add("dummy");
        }
        result = Implementation.getDatabaseCredentials();
        assertEquals(expectedResult,result);
    }
    @Test
    public void testConnection() throws SQLException{
        System.out.println("Testing Database Connection");
        Base Implementation = new DatabaseImplementation();
        boolean result = Implementation.getDatabaseConnection();
        boolean expectedResult = true;
        assertEquals(result,expectedResult);
    }
    @Test
    public void testKotlinGen(){
        System.out.println("Testing Password Generator");
        Utils utils = new Utils();
        System.out.println(utils.generateToken().substring(0,25));
    }

    @Test
    public void testBashExecution(){
        try{
            ProcessBuilder processBuilder = new ProcessBuilder("test");
            Process process = processBuilder.start();
            process.waitFor();
            System.out.println("Execution Done ");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    public class DatabaseImplementation extends Base{
        List<Integer> integers = new ArrayList<>();
    }
}
