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
public class NLFSRFunction4 extends NLFSRFunction{
    
    public NLFSRFunction4(){
        this.min = 4;
        this.description = "(r0 & r2) | r3";
    }
    
    @Override
    public boolean calculate(){
        return (seed[0] && seed[2]) || seed [3];
    }
}

