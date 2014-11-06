/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.hardcodeando.ui;

/**
 *
 * @author javier
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
    
}
