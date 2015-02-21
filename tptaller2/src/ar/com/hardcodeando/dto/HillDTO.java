/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.hardcodeando.dto;


/**
 *
 * @author nestor
 */
public class HillDTO {
    
    private String type;
    
    private int currentStep;
    
    private String message;
    private String cipherMessage;
    
    private int c11;
    private int c12;
    private int c21;
    private int c22;
    

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
    }


    public int getCurrentStep() {
        return currentStep;
    }

    public String getMessage() {
        return message;
    }

    public String getCipherMessage() {
        return cipherMessage;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCipherMessage(String cipherMessage) {
        this.cipherMessage = cipherMessage;
    }

    public int getC11() {
        return c11;
    }

    public int getC12() {
        return c12;
    }

    public int getC21() {
        return c21;
    }

    public int getC22() {
        return c22;
    }

    public void setC11(int c11) {
        this.c11 = c11;
    }

    public void setC12(int c12) {
        this.c12 = c12;
    }

    public void setC21(int c21) {
        this.c21 = c21;
    }

    public void setC22(int c22) {
        this.c22 = c22;
    }
    
    
    
}
