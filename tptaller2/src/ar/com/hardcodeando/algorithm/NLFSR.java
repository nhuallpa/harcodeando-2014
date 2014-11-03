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
    	
    	this.seed = getSeedArray(this.Seed);
    	
    	//Calculo clave
    	int newBit;
    	String tempKey = new String();
    	for(int i = 0; i < this.maxKeyLength; i++){
    		//Agregar bit n-1 a clave
    		tempKey = tempKey.concat(String.valueOf(this.seed[this.length - 1]));
    		//Calcular funcion
    		newBit = this.Function.getResult(seed);
        	//Desplazar 1 bit a derecha usando bit calculado como s0    		
    		this.seed = shiftSeed(this.seed, newBit);
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
    
    public String getKey(){
    	return this.Key;
    }
    
    public int[] getSeedArray(String seed){
        Character bit;
        int seedArray[];
    	seedArray = new int[seed.length()];
    	for(int i = 0; i < seed.length(); i++){
    		bit = seed.charAt(i);
    		seedArray[i] = Integer.parseInt(bit.toString()); 
    	}
        return seedArray;
    }
    
    public int[] shiftSeed(int oldSeed[], int s0){
        int newSeed[] = new int[oldSeed.length];
        for(int i = 0; i < oldSeed.length; i++){
    		if(i == 0)
    			newSeed[i] = s0;
    		else
    			newSeed[i] = oldSeed[i-1];
    	}
        return newSeed;
    }
}
