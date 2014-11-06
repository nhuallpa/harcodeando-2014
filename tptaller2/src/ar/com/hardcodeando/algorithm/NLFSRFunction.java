package ar.com.hardcodeando.algorithm;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public abstract class NLFSRFunction {

    String description;
    boolean seed[];
    int min;
    
    public int getResult(int[] seed){
        toBooleanSeed(seed);
        return calculate() ? 1 : 0;
    }
    
    public abstract boolean calculate();
            
    public int getMin(){
        return min;
    }
    
    public static Map<Integer,NLFSRFunction> getFunctions(){
        Map<Integer, NLFSRFunction> dictionary = new HashMap<>();
        dictionary.put(0, new NLFSRFunction4());
        dictionary.put(1, new NLFSRFunction3());
        dictionary.put(2, new NLFSRFunction2());
        return dictionary;
    }
	
    protected void toBooleanSeed(int[] s){
	this.seed = new boolean[s.length];
	for(int i = 0; i < s.length; i++ ){
		if(s[i] == 0)
			this.seed[i] = false;
		else
			this.seed[i] = true;
	}
    }

    public String getDescription() {
        return this.description;
    }

}
