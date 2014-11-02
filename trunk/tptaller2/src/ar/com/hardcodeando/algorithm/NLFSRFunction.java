package ar.com.hardcodeando.algorithm;

public abstract class NLFSRFunction {

	String description;
    boolean seed[];
    
	public abstract int getResult(int[] seed);
	
	protected void toBooleanSeed(int[] s){
		this.seed = new boolean[s.length];
		for(int i = 0; i < s.length; i++ ){
			if(s[i] == 0)
				this.seed[i] = false;
			else
				this.seed[i] = true;
		}
	}

}
