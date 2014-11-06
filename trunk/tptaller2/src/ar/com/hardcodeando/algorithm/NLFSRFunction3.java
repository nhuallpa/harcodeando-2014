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
public class NLFSRFunction3 extends NLFSRFunction {
    
    public NLFSRFunction3(){
        this.min = 3;
        this.description = "(r0 | r2) & r1";
    }

    @Override
    public boolean calculate() {
        return (seed[0] || seed[2]) && seed[1];
    }
    
}
