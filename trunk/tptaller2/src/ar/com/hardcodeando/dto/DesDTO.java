/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.hardcodeando.dto;

/**
 *
 * @author nacho
 */
public class DesDTO {

    private String type;

    private int currentStep;

    private String message;
    private String key;
    
    
    // Subclaves
    private String kBinario;
    private String pk;
    private String c0;
    private String d0;
    private String c1;
    private String d1;
    private String c2;
    private String d2;
    private String c3;
    private String d3;
    private String c4;
    private String d4;
    private String k1;
    private String k2;
    private String k3;
    private String k4;

    // Ronda inicial
    private String m;
    private String p1;
    private String r0;
    private String l0;

    // Ronda 1
    private String er0;
    private String k1Xer0;
    private String m1;
    private String n1;
    private String s1b1;
    private String sTotal1;
    private String postP1;
    private String pXl0;

    // Ronda 2
    private String er1;
    private String k2Xer1;
    private String m2;
    private String n2;
    private String s2b2;
    private String sTotal2;
    private String postP2;
    private String pXl1;

    // Ronda 3
    private String er2;
    private String k3Xer2;
    private String m3;
    private String n3;
    private String s3b3;
    private String sTotal3;
    private String postP3;
    private String pXl2;

    // Ronda 16
    private String er15;
    private String k16Xer15;
    private String m16;
    private String n16;
    private String s16b16;
    private String sTotal16;
    private String postP16;
    private String pXl15;

    // Final
    private String cFinal;
    private String cipherMessage;

    public String getKBinario() {
        return kBinario;
    }

    public void setKBinario(String kBinario) {
        this.kBinario = kBinario;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getC0() {
        return c0;
    }

    public void setC0(String c0) {
        this.c0 = c0;
    }

    public String getD0() {
        return d0;
    }

    public void setD0(String d0) {
        this.d0 = d0;
    }

    public String getC1() {
        return c1;
    }

    public void setC1(String c1) {
        this.c1 = c1;
    }

    public String getD1() {
        return d1;
    }

    public void setD1(String d1) {
        this.d1 = d1;
    }

    public String getC2() {
        return c2;
    }

    public void setC2(String c2) {
        this.c2 = c2;
    }

    public String getD2() {
        return d2;
    }

    public void setD2(String d2) {
        this.d2 = d2;
    }

    public String getC3() {
        return c3;
    }

    public void setC3(String c3) {
        this.c3 = c3;
    }

    public String getD3() {
        return d3;
    }

    public void setD3(String d3) {
        this.d3 = d3;
    }

    public String getC4() {
        return c4;
    }

    public void setC4(String c4) {
        this.c4 = c4;
    }

    public String getD4() {
        return d4;
    }

    public void setD4(String d4) {
        this.d4 = d4;
    }

    public String getK1() {
        return k1;
    }

    public void setK1(String k1) {
        this.k1 = k1;
    }

    public String getK2() {
        return k2;
    }

    public void setK2(String k2) {
        this.k2 = k2;
    }

    public String getK3() {
        return k3;
    }

    public void setK3(String k3) {
        this.k3 = k3;
    }

    public String getK4() {
        return k4;
    }

    public void setK4(String k4) {
        this.k4 = k4;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getR0() {
        return r0;
    }

    public void setR0(String r0) {
        this.r0 = r0;
    }

    public String getL0() {
        return l0;
    }

    public void setL0(String l0) {
        this.l0 = l0;
    }

    public String getEr0() {
        return er0;
    }

    public void setEr0(String er0) {
        this.er0 = er0;
    }

    public String getK1Xer0() {
        return k1Xer0;
    }

    public void setK1Xer0(String k1Xer0) {
        this.k1Xer0 = k1Xer0;
    }

    public String getM1() {
        return m1;
    }

    public void setM1(String m1) {
        this.m1 = m1;
    }

    public String getN1() {
        return n1;
    }

    public void setN1(String n1) {
        this.n1 = n1;
    }

    public String getS1b1() {
        return s1b1;
    }

    public void setS1b1(String s1b1) {
        this.s1b1 = s1b1;
    }

    public String getsTotal1() {
        return sTotal1;
    }

    public void setsTotal1(String sTotal1) {
        this.sTotal1 = sTotal1;
    }

    public String getPostP1() {
        return postP1;
    }

    public void setPostP1(String postP1) {
        this.postP1 = postP1;
    }

    public String getpXl0() {
        return pXl0;
    }

    public void setpXl0(String pXl0) {
        this.pXl0 = pXl0;
    }

    public String getEr1() {
        return er1;
    }

    public void setEr1(String er1) {
        this.er1 = er1;
    }

    public String getK2Xer1() {
        return k2Xer1;
    }

    public void setK2Xer1(String k2Xer1) {
        this.k2Xer1 = k2Xer1;
    }

    public String getM2() {
        return m2;
    }

    public void setM2(String m2) {
        this.m2 = m2;
    }

    public String getN2() {
        return n2;
    }

    public void setN2(String n2) {
        this.n2 = n2;
    }

    public String getS2b2() {
        return s2b2;
    }

    public void setS2b2(String s2b2) {
        this.s2b2 = s2b2;
    }

    public String getsTotal2() {
        return sTotal2;
    }

    public void setsTotal2(String sTotal2) {
        this.sTotal2 = sTotal2;
    }

    public String getPostP2() {
        return postP2;
    }

    public void setPostP2(String postP2) {
        this.postP2 = postP2;
    }

    public String getpXl1() {
        return pXl1;
    }

    public void setpXl1(String pXl1) {
        this.pXl1 = pXl1;
    }

    public String getEr2() {
        return er2;
    }

    public void setEr2(String er2) {
        this.er2 = er2;
    }

    public String getK3Xer2() {
        return k3Xer2;
    }

    public void setK3Xer2(String k3Xer2) {
        this.k3Xer2 = k3Xer2;
    }

    public String getM3() {
        return m3;
    }

    public void setM3(String m3) {
        this.m3 = m3;
    }

    public String getN3() {
        return n3;
    }

    public void setN3(String n3) {
        this.n3 = n3;
    }

    public String getS3b3() {
        return s3b3;
    }

    public void setS3b3(String s3b3) {
        this.s3b3 = s3b3;
    }

    public String getsTotal3() {
        return sTotal3;
    }

    public void setsTotal3(String sTotal3) {
        this.sTotal3 = sTotal3;
    }

    public String getPostP3() {
        return postP3;
    }

    public void setPostP3(String postP3) {
        this.postP3 = postP3;
    }

    public String getpXl2() {
        return pXl2;
    }

    public void setpXl2(String pXl2) {
        this.pXl2 = pXl2;
    }

    public String getEr15() {
        return er15;
    }

    public void setEr15(String er15) {
        this.er15 = er15;
    }

    public String getK16Xer15() {
        return k16Xer15;
    }

    public void setK16Xer15(String k16Xer15) {
        this.k16Xer15 = k16Xer15;
    }

    public String getM16() {
        return m16;
    }

    public void setM16(String m16) {
        this.m16 = m16;
    }

    public String getN16() {
        return n16;
    }

    public void setN16(String n16) {
        this.n16 = n16;
    }

    public String getS16b16() {
        return s16b16;
    }

    public void setS16b16(String s16b16) {
        this.s16b16 = s16b16;
    }

    public String getsTotal16() {
        return sTotal16;
    }

    public void setsTotal16(String sTotal16) {
        this.sTotal16 = sTotal16;
    }

    public String getPostP16() {
        return postP16;
    }

    public void setPostP16(String postP16) {
        this.postP16 = postP16;
    }

    public String getpXl15() {
        return pXl15;
    }

    public void setpXl15(String pXl15) {
        this.pXl15 = pXl15;
    }

    public String getcFinal() {
        return cFinal;
    }

    public void setcFinal(String cFinal) {
        this.cFinal = cFinal;
    }

    public String getCipherMessage() {
        return cipherMessage;
    }

    public void setCipherMessage(String cipherMessage) {
        this.cipherMessage = cipherMessage;
    }
    
    
    
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }    

    public void setMessage(String message) {
        this.message = message;
    }
}
