/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.hardcodeando.algorithm;

/**
 *
 * @author connie
 */
public class NLFSR {
 
    int longitud;
    int Sem[];

    String SemS;
    String Func;
    String Resultado;
    
    int length;
    int maxKeyLength;
    int seed[];
    String Seed;
    NLFSRFunction Function;
    String Key;
    
    public NLFSR(NLFSRFunction function, String seed){
        
    	this.length = seed.length();
    	this.maxKeyLength = ((int) Math.pow(2, this.length)) -1;
    	this.Function = function;
    	this.Seed = seed;
    	this.Key = new String();
    }
    
    public void Execute(){
    	
    	initSeed();
    	
    	//Calculo clave
    	int newBit;
    	String tempKey = new String();
    	for(int i = 0; i < this.maxKeyLength; i++){
    		//Agregar bit n-1 a clave
    		tempKey = tempKey.concat(String.valueOf(this.seed[this.length - 1]));
    		//Calcular funcion
    		newBit = this.Function.getResult(seed);
        	//Desplazar 1 bit a derecha usando bit calculado como s0    		
    		shiftSeed(newBit);		
    	}
    	
    	//Busco patron
    	String tempkey2 = new String();
    	String pattern = new String();
    	for(int i = 2; i < this.maxKeyLength; i++)
    	{
    		pattern = tempKey.substring(0, i);
    		int times = Math.round(this.maxKeyLength / pattern.length()) + 1;
    		tempkey2 = repeatPattern(pattern, times);
    		if(tempKey.equals(tempkey2.substring(0, this.maxKeyLength))) //patron encontrado
    			break;
    	}
    	
    	this.Key = pattern;    	
    }
    
    private String repeatPattern(String pattern, int times){
    	if(times <= 0)
    		return "";
    	else
    		return pattern + repeatPattern(pattern, times - 1);
    }
    
    private void shiftSeed(int s0){
    	int newSeed[] = new int[this.length];
    	for(int i = 0; i < this.length; i++){
    		if(i == 0)
    			newSeed[i] = s0;
    		else
    			newSeed[i] = this.seed[i-1];
    	}
    	this.seed = newSeed;
    }
    
    private void initSeed(){
    	
    	Character bit;
    	this.seed = new int[Seed.length()];
    	for(int i = 0; i < Seed.length(); i++){
    		bit = Seed.charAt(i);
    		this.seed[i] = Integer.parseInt(bit.toString()); 
    	}
    }
    
    public String getKey(){
    	return this.Key;
    }
}
