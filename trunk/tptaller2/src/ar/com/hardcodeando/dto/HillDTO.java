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
    private int currentStep;
    
    private String valueScreen1;
    
    private String valueScreen2;

    public void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
    }

    public void setValueScreen1(String valueScreen1) {
        this.valueScreen1 = valueScreen1;
    }

    public void setValueScreen2(String valueScreen2) {
        this.valueScreen2 = valueScreen2;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public String getValueScreen1() {
        return valueScreen1;
    }

    public String getValueScreen2() {
        return valueScreen2;
    }
    
    
}
