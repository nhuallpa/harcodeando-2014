/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.hardcodeando.dto;

/**
 *
 * @author Gabo
 */
public class RSADTO {
    private String type;   
    private int currentStep;
    private String p_ci;
    private String q_ci;
    private String n_ci;
    private String e_ci;
    private String d_ci;
    private String mens_encriptar;
    private String tam_bloque;
    private String resultado_encriptar_todo;
    private String pos_encriptar;
    private String resultado_encriptar_bloques;
    private String resultado_encriptar_parcial;
    private String resultado_desencriptar_todo;
    private String pos_desencriptar;
    private String resultado_desenriptar_bloques;
    private String reultado_desencriptar_parcial;
    
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
    
    public void setP_CI(String p){
        this.p_ci = p;
    }
    
    public String getP_CI(){
        return this.p_ci;
    }
    
    public void setQ_CI(String q){
        this.q_ci = q;
    }
    
    public String getQ_CI(String q){
        return this.q_ci;
    }
    
    public void setN_CI(String n){
        this.n_ci = n;
    }
    
    public String getN_CI(){
        return this.n_ci;
    }
    
    public void setE_CI(String e){
        this.e_ci = e;
    }
    
    public String getE_CI(){
        return this.e_ci;
    }
    
    public void setD_CI(String d){
        this.d_ci = d;
    }
    
    public String getD_CI(){
        return this.d_ci;
    }
    
    public void setMensajeEncriptar(String mens){
        this.mens_encriptar = mens;
    }
    
    public String getMensajeEncritar(){
        return this.mens_encriptar;
    }
    
    public void setTamBloque(String t){
        this.tam_bloque = t;
    }
    
    public String getTamBloque(){
        return this.tam_bloque;
    }
    
    public void setResultadoEncriptarTodo(String m){
        this.resultado_encriptar_todo = m;
    }
    
    public String getResultadoEncriptarTodo(){
        return this.resultado_encriptar_todo;
    }
    
    public void setPosEncriptar(String p){
        this.pos_encriptar = p;
    }
    
    public String getPosEncriptar(){
        return this.pos_encriptar;
    }
    
    public void setResultadoEncriptarBloques(String b){
        this.resultado_encriptar_bloques = b;
    }
    
    public String getResultadoEncriptarBloques(){
        return this.resultado_encriptar_bloques;
    }
    
    public void setResultadoParcialEncriptar(String r){
        this.resultado_encriptar_parcial = r;
    }
    
    public String getResultadoParcialEncriptar(){
        return this.resultado_encriptar_parcial;
    }
    
    public void setResultadoDesencriptarTodo(String d){
        this.resultado_desencriptar_todo = d;
    }
    
    public String getResultadoDesencriptarTodo(){
        return this.resultado_desencriptar_todo;
    }
    
    public void setPosDesencriptar(String p){
        this.pos_desencriptar = p;
    }
    
    public String getPosDesencriptar(){
        return this.pos_desencriptar;
    }
    
    public void setResultadoDesencriptarBloques(String d){
        this.resultado_desenriptar_bloques = d;
    }
    
    public String getResultadoDesencriptarBloques(){
        return this.resultado_desenriptar_bloques;
    }
    
    public void setResultadoParcialDesencriptar(String d){
        this.reultado_desencriptar_parcial = d;
    }
    
    public String getResultadoParcialDesencriptar(){
        return this.reultado_desencriptar_parcial;
    }
}
