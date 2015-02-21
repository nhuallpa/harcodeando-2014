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
    private String representationBitsPlusLong;
    private String initialValueA;
    private String initialValueB;
    private String initialValueC;
    private String initialValueD;
    private String resumeValue;
    private String[][] matrixStep5 = new String [3][4];
    private String[][] matrixStep4 = new String[16][4];

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

    public void setRepresentationBitsPlusLong(String representationBitsPlusLong) {
        this.representationBitsPlusLong = representationBitsPlusLong;
    }
    public String getRepresentationBitsPlusLong() {
       return representationBitsPlusLong;
    }

    public String getInitialValueA() {
        return initialValueA;
    }
    
    public void setInitialValueA(String initialValueA) {
        this.initialValueA = initialValueA;
    }
    public String getInitialValueB() {
        return initialValueB;
    }
    
    public void setInitialValueB(String initialValueB) {
        this.initialValueB = initialValueB;
    }
    public String getInitialValueC() {
        return initialValueC;
    }
    
    public void setInitialValueC(String initialValueC) {
        this.initialValueC = initialValueC;
    }
    public String getInitialValueD() {
        return initialValueD;
    }
    
    public void setInitialValueD(String initialValueD) {
        this.initialValueD = initialValueD;
    }

    public String getResumeValue() {
        return resumeValue;
    }
    
    public void setResumeValue(String resumeValue) {
        this.resumeValue = resumeValue;
    }
    
    public String[][] getMatrixStep4() {
        return matrixStep4;
    }
    
    public void setMatrixStep4(String[][] matrixStep4) {
        this.matrixStep4 = matrixStep4;
    }
    
    public String[][] getMatrixStep5() {
        return matrixStep5;
    }
    
    public void setMatrixStep5(String[][] matrixStep5) {
        this.matrixStep5 = matrixStep5;
    }
    
}
