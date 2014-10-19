/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.hardcodeando.algorithm;

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
 * @author Nestor
 */
public class MD5Test {
    
    public MD5Test() {
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
    public void md5Of1234() {
        char chrTestData[] = new char[64];
        MD5 md5Test = new MD5();
        
        String strTestData = "1234";
        chrTestData = strTestData.toCharArray();
        md5Test.update(chrTestData, chrTestData.length);
        md5Test.md5final(); 
        assertEquals("81dc9bdb52d04dc20036dbd8313ed055", md5Test.toHexString().toString());
    }
    @Test
    public void palabras64TallerII() {
        char chrTestData[] = new char[64];
        MD5 md5Test = new MD5();  
        
        String strTestData = "tallerIItallerIItallerIItallerIItallerIItallerIItallerIItallerII";
        chrTestData = strTestData.toCharArray();
        md5Test.update(chrTestData, chrTestData.length);
        md5Test.md5final(); 
        assertEquals("18bc68c3e834a6a8de350959f4c78e04", md5Test.toHexString().toString());
    }
    @Test
    public void palabras128TallerII() {
        char chrTestData[] = new char[128];
        MD5 md5Test = new MD5();  
        
        String strTestData = "tallerIItallerIItallerIItallerIItallerIItallerIItallerIItallerIItallerIItallerIItallerIItallerIItallerIItallerIItallerIItallerII";
        chrTestData = strTestData.toCharArray();
        md5Test.update(chrTestData, chrTestData.length);
        md5Test.md5final(); 
        assertEquals("29cea83a33881360fb380198e15359be", md5Test.toHexString().toString());
    }
}
