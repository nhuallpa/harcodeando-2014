/**
 * ALGORITMO CRIPTOGRAFICO RSA
 * ASIMETRICO
 * 
 * 
 */
package ar.com.hardcodeando.algorithm;


import java.util.Random;
/**
 *
 * @author Gabo
 */
public class RSA {
    private long p;
    private long q;
    private long n;
    private long d;
    private long e;
    private int max_primo;
    
    public RSA(){
        this.p = 0;
        this.q = 0;
        this.n = 0;
        this.d = 0;
        this.e = 0;
        this.max_primo = 31;
    }
    /**
     * verifica si un numero es primo o no
     * @param numero valor a verificar
     * @return true si es primo, false caso contraio
     */    
    private boolean EsNumeroPrimo(long numero){
        boolean ret = false;
        if(numero != 0)
        {
            int i = 1, j = 0;
            while(j <= 2 && i < numero + 1)
            {
                if(numero % i == 0) j++;                
                i++;
            }
            if(j <= 2) ret = true;
        }
        return ret;        
    }
    
    /**
     * Crea los numeros primos p y q que luego se usaran para generar
     * las claves publica y privada
     */
    public void GenerarPrimos(){
        Random rnd = new Random();        
        this.p = rnd.nextInt(max_primo);
        while(!this.EsNumeroPrimo(this.p)){
            this.p++;
        }
        this.q = rnd.nextInt(max_primo);
        while(!this.EsNumeroPrimo(this.q)){
            this.q++;
        }        
    }
    
    /**
     * Setea el parametro p, debe verificarse que sea primo
     * @param valor es un numero primo
     * @return true si el valor ingresado es primo, false si no
     */
    public boolean SetP(long valor){
        boolean ret = this.EsNumeroPrimo(valor);
        if(ret) this.p = valor;
        return ret;
    }
    
    /**
     * setea el parametro q, debe verificarse que sea primo
     * @param valor es un numero primo
     * @return true si el valor ingresado es primo, false si no
     */
    public boolean SetQ(long valor){
        boolean ret = this.EsNumeroPrimo(valor);
        if(ret)this.q = valor;
        return ret;
    }
    
    public long GetP(){
        return this.p;
    }
    
    public long GetQ(){
        return this.q;
    }
    
    public int GetMaxPrimo(){
        return this.max_primo;
    }
    
    
    
    
    /**
     * Calcula el modulo a traves de los numeros primos
     * que luego sera utilizado como parte de las claves
     * publica y privada
     * @return true si se cargaron los valores p y q y se calculo el modulo
     * false si no.
     */
    public boolean GenerarModulo(){
        boolean ret = false;
        if(this.p != 0 && this.q != 0){
            this.n = this.p * this.q;
            ret = true;
        }
        return ret;
    }
    
    public long ObtenerModulo(){
        return this.n;
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