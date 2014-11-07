/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.hardcodeando.ui;

/**
 *
 * @author connie
 */
public class NLFSRCommon {
    
    public static String getFunctionDescription(int length){
        String function = new String();
        function = "<html>f(";
        for(int i = 0; i < length; i++){
            function = function + (i > 0 ? "," : "") + "r<sub>" + i + "</sub>";
        }
        function = function + ") = </html>";
        
        return function;
    }
    
    public static char getKey(String seed){
        return seed.charAt(seed.length() - 1);
    }
    
    public static String getSeedString(int[] seedArray){
        String result = new String();
        for (int i = 0; i < seedArray.length; i++) {
              result = result.concat(Integer.toString(seedArray[i]));                      
        }
        return result;
    }
    
}
