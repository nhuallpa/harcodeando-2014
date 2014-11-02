/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.hardcodeando.algorithm;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author connie
 */
public class NLFSRTest {
    
    public NLFSRTest() {
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
    public void executingFor4BitsWithFunction4() {
        
    	NLFSRFunction4 function = new NLFSRFunction4();
    	NLFSR nlfsr = new NLFSR(function, "1100");
    	
    	nlfsr.Execute();
    	
    	assertEquals("0011", nlfsr.getKey());        
    }
}
