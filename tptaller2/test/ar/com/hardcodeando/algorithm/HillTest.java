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
    public void verificarClaveValida() {
        HillCipher hill = new HillCipher();
        assertTrue(hill.check("dcfh",2)); // 3257
    }
    
    @Test
    public void verificarClaveInvalida() {
        HillCipher hill = new HillCipher();
        assertFalse(hill.check("bbaa",2)); // 1100
    }
    
    @Test
    public void verificarClaveInvalidaFactorComunCon26() {
        HillCipher hill = new HillCipher();
        assertFalse(hill.check("dbbb",2)); //2111
    }
    
    @Test
    public void verificarOtraClaveValida() {
        HillCipher hill = new HillCipher();
        assertTrue(hill.check("ejns", 2)); //4 9 13 18
    }
    
    @Test
    public void verificarOtraClaveInvalida() {
        HillCipher hill = new HillCipher();
        assertFalse(hill.check("bbbb",2)); // 1 1 1 1
    }
    
    
    @Test
    public void encriptarHola() {
        HillCipher hill = new HillCipher();
        
        int[][] clave = null;
        
        hill.validarClave(clave);
        
        String msgEncriptado = hill.encriptar("HOLAS", clave);
        assertEquals("ASD", msgEncriptado);
    }
    
    @Test
    public void desencriptarHola() {
        HillCipher hill = new HillCipher();
        
        int[][] clave = null;
       
        String msgEncriptado = hill.desencriptar("A", clave);
        assertEquals("ASD", msgEncriptado);
    }
    
    @Test
    public void encriptarTallerII() {
        HillCipher hill = new HillCipher();
        
        int[][] clave = null;
        
        hill.validarClave(clave);
        
        String msgEncriptado = hill.encriptar("TALLERII", clave);
        assertEquals("ASD", msgEncriptado);
    }
    
    @Test
    public void desencriptarTallerII() {
        HillCipher hill = new HillCipher();
        
        int[][] clave = null;
       
        String msgEncriptado = hill.desencriptar("A", clave);
        assertEquals("ASD", msgEncriptado);
    }
    
}
