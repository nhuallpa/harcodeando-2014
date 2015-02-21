/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.hardcodeando.dto;

/**
 *
 * @author estefi
 */
public class MD5DTO {
    
    private String type;   
    private int currentStep;
    private String message;
    private String cipherMessage;
    private String binaryMessage;
    private String representationBitsPlusFiller;

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

    public String getBinaryMessage() {
        return binaryMessage;
    }

    public void setBinaryMessage(String binaryMessage) {
        this.binaryMessage = binaryMessage;
    }

    public void setRepresentationBitsPlusFiller(String representationBitsPlusFiller) {
        this.representationBitsPlusFiller = representationBitsPlusFiller;
    }
    public String getRepresentationBitsPlusFiller() {
       return representationBitsPlusFiller;
    }
}
