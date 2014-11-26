/**
 * ALGORITMO CRIPTOGRAFICO RSA
 * ASIMETRICO
 * 
 * 
 */
package ar.com.hardcodeando.algorithm;


import java.math.BigInteger;
import java.util.Random;
import static org.eclipse.persistence.platform.database.oracle.plsql.OraclePLSQLTypes.Int;
/**
 *
 * @author Gabo
 */
public class RSA {
    private long p;
    private long q;
    private long n;
    private int long_bloque;
    private long d;
    private long e;
    private final int max_primo;
    private final int min_primo;
    private long intervalo_d_inf;
    private long intervalo_d_sup;
    private long p1q1;
    
    
    public RSA(){
        this.p = 0;
        this.q = 0;
        this.n = 0;
        this.d = 0;
        this.e = 0;
        this.long_bloque = 0;               
        this.intervalo_d_inf = 0;
        this.intervalo_d_sup = 0;
        this.p1q1 = 0;
        this.max_primo = 811;
        this.min_primo = 601;
    }
    /**
     * Para saber con cuantos digitos se va a representar 1 caracter
     */
    private void CalcularTamañoBloque(){
        if(this.n != 0){
            long aux = this.n;
            while(aux != 0){
                aux /= 10;
                this.long_bloque++;
            }
        }
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
        while(this.p < this.min_primo)
                this.p = rnd.nextInt(max_primo);        
        while(!this.EsNumeroPrimo(this.p)){
            this.p++;
        }
        this.q = rnd.nextInt(max_primo);
        while(this.q < this.min_primo)
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
    
    public int GetMinPrimo(){
        return this.min_primo;
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
    
    public int GetLongitudBloque(){
        return this.long_bloque;    
    }
       
    public boolean SetD(long valor){
        boolean ret = false;
        if(valor > this.intervalo_d_inf && valor < this.intervalo_d_sup){
            if(this.MCD(valor, this.p1q1) == 1){
                this.d = valor;
                ret = true;
            }
        }
        return ret;
    }
    
    public boolean SetE(long valor){
        boolean ret = false;
        if(this.d != 0){
            long mult = valor * this.d;
            BigInteger multBE = new BigInteger(Long.toString(mult));
            BigInteger modulo = new BigInteger(Long.toString(this.p1q1));
            multBE = multBE.mod(modulo);
            mult = multBE.longValue();
            if(mult == 1){
                this.e = valor;
                ret = true;
            }
        }
        return ret;
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
            this.CalcularTamañoBloque();
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
     * @return true si pudo generar exponente privado, false si no.
     */
    public boolean GenerarExponentePrivado(){
        boolean ret = false;
        if(this.p1q1 != 0){
            this.d = this.intervalo_d_inf;
            while(this.MCD(this.d, this.p1q1)!= 1)
            {
                this.d++;
            } 
            ret = true;
        }  
        return ret;
    }
    
    /**
     * Generar numero e dentro del rango 1 <= e <= (p-1)*(q-1)
     * Junto con el modulo forman la clave publica
     * @return true si pudo generar el exponente publico, false si no
     */
    public boolean GenerarExponentePublico(){
        //Hallar el inverso modular de d mod (p-1)(q-1)--> de = 1 mod (p-1)(q-1)
        boolean ret = false;
        if(this.d != 0 && this.p1q1 != 0){
            this.e = this.InversoMultiplicativo(this.d, this.p1q1);
            ret = true;
        }
        return ret;
    }
    
    private String GenerarRepresentacionNumerica(String mensaje){
        String aux = "";        
        for(int i = 0;i < mensaje.length();i++){
            char c = mensaje.charAt(i);
            long ascii = c;
            if(ascii < 100) aux += "0";
            aux += Long.toString(ascii);
        }       
        return aux;
    }
    /**
     * Convierte un mensaje a su representacion ASCII
     * @param mensaje mensaje a transformar en representacion ASCII
     * @return represetacion ASCII
     */
    public String Codificar(String mensaje){
        String res = "";
        
        for(int i = 0; i < mensaje.length(); i++){
            char c = mensaje.charAt(i);
            long ascii = c;
            if(ascii < 100) res += "0";
            res += Long.toString(ascii);
        }
        return res;
    }
    
    /**
     * 
     * @param mensaje mensaje a encriptar
     * @param cant_bloques cantidad de caracteres q se toman por ronda
     * @return representacion numerica encriptada
     */
    public String Encrypt(String mensaje, int cant_bloques){
        String encriptado = "";
        String rep_num = this.GenerarRepresentacionNumerica(mensaje);
        int offset = 3*cant_bloques;
        int pos = 0;
        while(pos < rep_num.length()){
            String aux;
            if(pos + offset < rep_num.length())
                aux = rep_num.substring(pos, pos + offset);
            else
                aux = rep_num.substring(pos);
            long cifrado = Long.parseLong(aux);
            BigInteger potencia = new BigInteger(Long.toString(cifrado));
            BigInteger exponente = new BigInteger(Long.toString(this.e));
            BigInteger modulo = new BigInteger(Long.toString(this.n));
            potencia = potencia.modPow(exponente, modulo);
            
            cifrado = potencia.longValue();
            String sub_bloque = Long.toString(cifrado);
            while(sub_bloque.length() < this.long_bloque){
                sub_bloque = "0" + sub_bloque;
            }
            encriptado += sub_bloque;
            pos += offset;
        }        
        return encriptado;
    }
    
    public void LimpiarValores(){
        this.p = 0;
        this.q = 0;
        this.n = 0;
        this.e = 0;
        this.p1q1 = 0;
        this.intervalo_d_inf = 0;
        this.intervalo_d_sup = 0; 
        this.long_bloque = 0;
    }    
    
    /**
     * Convierte una cadena con digitos ascii en su cadena equivalente
     * @param ascii cadena de digitos ascii representando caracteres
     * @return cadena transformada en su equivalente
     */
    public String DecodificarAscii(String ascii){
        int pos = 0;
        int offset = 3;
        String res = "";
        while(pos < ascii.length()){
            String aux = ascii.substring(pos, pos + offset);
            long cifrado = Long.parseLong(aux);
            char c = (char) cifrado;
            res += c;
            pos += offset; 
        }
        return res;
    }
   
   
    public String DesencriptarTodo(String mensaje, int tam_bloque){
        int offset = this.long_bloque;
        int pos = 0;
        String ascii = "";
        while(pos < mensaje.length()){
            String aux;
            if(pos + offset < mensaje.length())
                aux = mensaje.substring(pos, pos + offset);
            else
                aux = mensaje.substring(pos);
            long cifrado = Long.parseLong(aux);
            BigInteger base = new BigInteger(Long.toString(cifrado));
            BigInteger exponente = new BigInteger(Long.toString(this.d));
            BigInteger modulo = new BigInteger(Long.toString(this.n));
            base = base.modPow(exponente, modulo);            
            cifrado = base.longValue();
            
            String sub_bloque = Long.toString(cifrado);
            while(sub_bloque.length() < this.long_bloque && sub_bloque.length() != 3){                
                sub_bloque = "0" + sub_bloque;
            }
            ascii += sub_bloque;
            pos += offset;
        }                
        return ascii;
    }
    

}