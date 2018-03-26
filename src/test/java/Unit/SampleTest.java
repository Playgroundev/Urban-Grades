package Unit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.dita.dev.Model.Base;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;
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
public class SampleTest {
    
    public SampleTest() {
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
    public void TestClass(){
        boolean expectedResult = true;
        boolean result = true;
        assertEquals(expectedResult,result);
    }
    @Test
    public void testOutput() throws IOException{
        Base implementation = new BaseImplementation();
        
       System.out.println(implementation.getDatabaseCredentials());
    }
    
    @Test
    public void testRandomGenerator(){
        byte [] array = new byte[7];
        new Random().nextBytes(array);
        String code = new String(array,Charset.forName("UTF-8"));
        System.out.println(code);
    }
    public class BaseImplementation extends Base{
        
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
