/**
 * ALGORITMO CRIPTOGRAFICO RSA
 * ASIMETRICO
 * 
 * 
 */
package ar.com.hardcodeando.algorithm;

import java.math.BigInteger;
/**
 *
 * @author Gabo
 */
public class RSA {
    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger d;
    private BigInteger e;
    
    /**
     * Crea los numeros primos p y q que luego se usaran para generar
     * las claves publica y privada
     */
    public void GenerarPrimos(){
        
    }
    
    
    /**
     * Calcula el modulo a traves de los numeros primos
     * que luego sera utilizado como parte de las claves
     * publica y privada
     */
    public void GenerarModulo(){
        
    }
    
    /**
     *  Generar numero relativamente primo a (p-1)*(q-1)
     *  Junto con el modulo forman la clave privada
     */
    public void GenerarExponentePrivado(){
    
    }
    
    /**
     * Generar numero e dentro del rango 1 <= e <= (p-1)*(q-1)
     * Junto con el modulo forman la clave publica
     */
    public void GenerarExponentePublico(){
        
    }
    
    public void Encriptar(String mensaje){
        
    }
    
    public void Desencriptar(String mensaje){
        
    }
    

}