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
        
        // Armar matriz M inversible
        // mcd(det(M),26) = 1 con esto nos asegurarmos de desifrar
        int[][] clave = {{3,2},{5,7}};
       
        assertTrue(hill.validarClave(clave));
    }
    @Test
    public void verificarOtraClaveValida() {
        HillCipher hill = new HillCipher();
        
        // Armar matriz M inversible
        // mcd(det(M),26) = 1 con esto nos asegurarmos de desifrar
        int[][] clave = {{4,9},{13,18}};
        
        hill.validarClave(clave);
        
        assertTrue(hill.validarClave(clave));
    }
    
    @Test
    public void verificarClaveInvalida() {
        HillCipher hill = new HillCipher();
        
        
        int[][] clave = {{1,1},{1,1}};
               
        assertFalse(hill.validarClave(clave));
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
