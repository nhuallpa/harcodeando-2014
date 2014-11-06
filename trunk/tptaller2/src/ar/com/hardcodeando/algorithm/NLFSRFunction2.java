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
public class NLFSRFunction2 extends NLFSRFunction {
    
    public NLFSRFunction2(){
        this.min = 2;
        this.description = "r0 & r1";
    }
    
    public boolean calculate(){
        return (seed[0] && seed[1]);
    }    
}
