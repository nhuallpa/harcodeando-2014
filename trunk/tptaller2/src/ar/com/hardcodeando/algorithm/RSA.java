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
    private long intervalo_d_inf;
    private long intervalo_d_sup;
    private long p1q1;
    
    
    public RSA(){
        this.p = 0;
        this.q = 0;
        this.n = 0;
        this.d = 0;
        this.e = 0;
        this.intervalo_d_inf = 0;
        this.intervalo_d_sup = 0;
        this.p1q1 = 0;
        this.max_primo = 31;
    }
    /**
     * verifica si un numero es primo o no
     * @param numero valor a verificar
     * @return true si es primo, false caso contraio
     */    
    private boolean EsNumeroPrimo(long numero){
        boolean ret = false;
        if(numero > 1)
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
     * Algoritmo de euclides para calcular maximo comun divisor
     * @param n1 parametro1
     * @param n2 parametro2
     * @return maximo comun divisor entre n1 y n2
     */
    private long MCD(long n1, long n2){
        long numMayor = Math.max(n1, n2);
        long numMenor = Math.min(n1, n2);
        while(numMenor != 0){
            long resto = numMayor%numMenor;
            numMayor = numMenor;
            numMenor = resto;
        }
        return numMayor;
    }
    
    private static long[] EuclidesExtendido(long a, long b)
    /*  This function will perform the Extended Euclidean algorithm
        to find the GCD of a and b.  We assume here that a and b
        are non-negative (and not both zero).  This function also
        will return numbers j and k such that 
               d = j*a + k*b
        where d is the GCD of a and b.
    */
    { 
        long[] ans = new long[3];
        long q;

        if (b == 0)  {  /*  If b = 0, then we're done...  */
            ans[0] = a;
            ans[1] = 1;
            ans[2] = 0;
        }
        else
            {     /*  Otherwise, make a recursive function call  */ 
               q = a/b;
               ans = EuclidesExtendido (b, a % b);
               long temp = ans[1] - ans[2]*q;
               ans[1] = ans[2];
               ans[2] = temp;
            }

        return ans;
    }
       
    private long InversoMultiplicativo(long valor, long modulo){
        /*long inv = 1;
        boolean flag = false;
        
        while(!flag && inv <= modulo){
            if((valor*inv)%modulo == 1) flag = true;
            inv++;
        }
        if(flag) return inv;
        else return 0;*/
        long inv = 0;
        long resp[] = new long[3];
        resp = EuclidesExtendido(valor, modulo);
        if(resp[0] > 1) return inv;
        else{
            if(resp[1] < 0){
                inv = resp[1] + modulo;                
            }
            else{
                inv = resp[1];
            }
            return inv;
        }            
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
    
    public long GetIntervaloInfD(){
        return this.intervalo_d_inf;
    }
    
    public long GetIntervaloSupD(){
        return this.intervalo_d_sup;
    }
    
    public long GetP1Q1(){
        return this.p1q1;
    }
    
    public long GetD(){
        return this.d;     
    }
    
    public long GetE(){
        return this.e;
    }
    
    public long GetModulo(){
        return this.n;
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
            this.intervalo_d_inf = (this.p > this.q)? p + 1:q + 1;
            this.intervalo_d_sup = this.n - 1;
            this.p1q1 = (this.p -1)*(this.q-1);
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
        this.d = this.intervalo_d_inf;
        while(this.MCD(this.d, this.p1q1)!= 1)
        {
            this.d++;
        }    
    }
    
    /**
     * Generar numero e dentro del rango 1 <= e <= (p-1)*(q-1)
     * Junto con el modulo forman la clave publica
     */
    public void GenerarExponentePublico(){
        //Hallar el inverso modular de d mod (p-1)(q-1)--> de = 1 mod (p-1)(q-1)
        this.e = this.InversoMultiplicativo(this.d, this.p1q1);
        
    }
    
    public void Encriptar(String mensaje){
        
    }
    
    public void Desencriptar(String mensaje){
        
    }
    

}