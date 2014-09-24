/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.hardcodeando.algorithm;

import ar.com.hardcodeando.algorithm.HillCipher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nestor
 */
public class HillTest {
    
    public HillTest() {
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
    public void encriptarUnaLetra() {
        HillCipher hill = new HillCipher();
        int[][] clave = null;
        String msgEncriptado = hill.encriptar("A", clave);
        assertEquals("ASD", msgEncriptado);
    }
}
