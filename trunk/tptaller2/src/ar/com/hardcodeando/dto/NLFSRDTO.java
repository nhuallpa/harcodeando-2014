/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.hardcodeando.dto;

/**
 *
 * @author connie
 */
public class NLFSRDTO {
    
    private int currentStep;
    private String seed;
    private String register;
    private int selectedIndex;
    private String[] rowStep3 = new String[3];
    private String[] rowStep4 = new String[4];
    private String[][] tableStep5 = new String[16][4];
    private String key;
    private String period;
    private String keyDecimal;
    
    public void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
    }

    public int getCurrentStep() {
        return currentStep;
    }
    
    public void setSeed(String seed) {
        this.seed = seed;
    }

    public String getSeed() {
        return seed;
    }

    public void setRegister(String register) {
        this.register = register;
    }
    
    public String getRegister() {
        return register;
    }

    public void setFunctionId(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }
    
    public int getFunctionId(){
        return selectedIndex;
    }

    public void setRowStep3(String[] list) {
        this.rowStep3 = list;
    }
    
    public String[] getRowStep3(){
        return rowStep3;
    }

    public void setRowStep4(String[] list) {
        this.rowStep4 = list;
    }
    
    public String[] getRowStep4(){
        return rowStep4;
    }
    
    public void setTableStep5(String[][] table){
        this.tableStep5 = table;
    }
    
    public String[][] getTableStep5(){
        return tableStep5;
    }

    public void setKey(String key) {
        this.key = key;
    }
    
    public String getKey(){
        return key;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
    
    public String getPeriod(){
        return period;
    }

    public void setKeyDecimal(String keyDecimal) {
        this.keyDecimal = keyDecimal;
    }
    
    public String getKeyDecimal(){
        return keyDecimal;
    }
}
