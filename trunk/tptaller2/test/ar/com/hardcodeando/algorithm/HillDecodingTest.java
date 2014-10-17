/*
 * Referencias:
 * http://en.wikipedia.org/wiki/Minor_(linear_algebra)#Matrix_of_cofactors
 * http://www.codeproject.com/Articles/405128/Matrix-operations-in-Java
 * http://www.unc.edu/~marzuola/Math547_S13/Math547_S13_Projects/R_Doyle_Section001_Cryptography.pdf
 * http://cryptohill.blogspot.com.ar/2011/06/parte-1-cifrado-de-hill-completada.html
 *
 */
package ar.com.hardcodeando.algorithm;

import ar.com.hardcodeando.algorithm.HillCipher;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class HillDecodingTest {
    
    public HillDecodingTest() {
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
    public void decodingTopsecret() {
        HillCipher hill = new HillCipher(2);
        
        String clave = "dcfh";
        String claveInv;
        try {
            claveInv = hill.calculateKeyInv(clave);
            String msgDecoding = hill.decoding("HLDTQIHJDXWQCMAG", claveInv);
            assertEquals("TOPSECRETMESSAGE", msgDecoding.toUpperCase());
        } catch (BadFormedKeyException ex) {
            Logger.getLogger(HillDecodingTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Test
    public void decodingTopsecretMatriz() {
        HillCipher hill = new HillCipher(2);
        int clave[][] = new int[2][2];
        clave[0][0] = 3;
        clave[0][1] = 2;
        clave[1][0] = 5;
        clave[1][1] = 7;
        int claveInv[][] = null;
        try {
            claveInv = hill.calculateKeyInv(clave);
            String msgDecoding = hill.decoding("HLDTQIHJDXWQCMAG", claveInv);
            assertEquals("TOPSECRETMESSAGE", msgDecoding.toUpperCase());
        } catch (BadFormedKeyException ex) {
            Logger.getLogger(HillDecodingTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void decodingHola() {
        HillCipher hill = new HillCipher(2);
        
        String clave = "dcfh";
        String claveInv;
        try {
            claveInv = hill.calculateKeyInv(clave);
            String msgDecoding = hill.decoding("XDHD", claveInv);
            assertEquals("HOLA", msgDecoding.toUpperCase());
        } catch (BadFormedKeyException ex) {
            Logger.getLogger(HillDecodingTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
}
