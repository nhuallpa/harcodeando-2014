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
public class HillVerifyKeysTest {
    
    public HillVerifyKeysTest() {
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
    public void verificarClaveValida() {
        HillCipher hill = new HillCipher(2);
        assertTrue(hill.check("dcfh")); // 3 2 5 7
    }
    
    @Test
    public void verificarClaveInvalida() {
        HillCipher hill = new HillCipher(2);
        assertFalse(hill.check("bbaa")); // 1 1 0 0
    }
    
    @Test
    public void verificarClaveInvalidaFactorComunCon26() {
        HillCipher hill = new HillCipher(2);
        assertFalse(hill.check("dbbb")); //2 1 1 1
    }
    
    @Test
    public void verificarOtraClaveValida() {
        HillCipher hill = new HillCipher(2);
        assertTrue(hill.check("ejns")); //4 9 13 18
    }
    
    @Test
    public void verificarOtraClaveInvalida() {
        HillCipher hill = new HillCipher(2);
        assertFalse(hill.check("bbbb")); // 1 1 1 1
    }
    
}
