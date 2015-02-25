/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.hardcodeando.ui;

import ar.com.hardcodeando.algorithm.RSA;
import ar.com.hardcodeando.dto.RSADTO;
import ar.com.hardcodeando.ui.utils.AlgorithmStateStorage;
import java.awt.Color;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabo
 */
public class RSAPanelAutoevaluar extends javax.swing.JPanel {

    /**
     * Creates new form RSAPanelAutoevaluar
     */
    private final RSA rsa_evaluar;
    private int pos_encriptar;
    private int pos_desencriptar;
    String parcial_desenc_numerico;
    String parcial_desenc_ascii;
    private boolean modulo_generado;
    private final short max_lenght_encriptar = 18;
    
    public RSAPanelAutoevaluar() {
        initComponents();
        this.rsa_evaluar = new RSA();
        this.pos_encriptar = 0;
        this.pos_desencriptar = 0;
        this.RSAEvaluarPanel.setEnabledAt(1, false);
        this.RSAEvaluarPanel.setEnabledAt(2, false);
        this.RSAEvaluarPanel.setEnabledAt(3, false);
        this.labelCorreccionN.setVisible(false);
        this.labelCorreccionE.setVisible(false);
        this.labelCorreccionD.setVisible(false);
        this.labelCorreccionEncriptarTodo.setVisible(false);
        this.labelCorreccionEncriptarBloques.setVisible(false);
        this.labelCorreccionDesTodo.setVisible(false);
        this.labelCorreccionDesencBloque.setVisible(false);
        this.modulo_generado = false;
        this.parcial_desenc_numerico = "";
        this.parcial_desenc_ascii = "";
    }    
    
    private String GenerarString(int longitud){
        String cadenaAleatoria = "";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while ( i < longitud){
            char c = (char)r.nextInt(255);
            if ( (c >= '0' && c <='9') || (c >='A' && c <='Z')  || (c >= 'a' && c <= 'z')){
                cadenaAleatoria += c;
                i ++;
            }
        }
        return cadenaAleatoria;
    }
    
    private void ResetearPreguntas(){
        this.rbotCorrecto1.setForeground(Color.black);
        this.rbotCorrecto2.setForeground(Color.black);
        this.rbotCorrecto3.setForeground(Color.black);
        this.rbotCorrecto4.setForeground(Color.black);
        this.rbotCorrecto5.setForeground(Color.black);
        this.rbotCorrecto6.setForeground(Color.black);
        this.rbotCorrecto7.setForeground(Color.black);
        this.rbotCorrecto8.setForeground(Color.black);
        this.rbotCorrecto9.setForeground(Color.black);
        this.rbotCorrecto10.setForeground(Color.black);
        this.grupoPreg1.clearSelection();
        this.grupoPreg2.clearSelection();
        this.grupoPreg3.clearSelection();
        this.grupoPreg4.clearSelection();
        this.grupoPreg5.clearSelection();
        this.grupoPreg6.clearSelection();
        this.grupoPreg7.clearSelection();
        this.grupoPreg8.clearSelection();
        this.grupoPreg9.clearSelection();
        this.grupoPreg10.clearSelection();
        this.botContinuarPreguntas.setEnabled(false);
        this.botCorregirPreguntas.setEnabled(true);
        this.botReintentarPreguntas.setEnabled(false);
        this.botResolverPreguntas.setEnabled(true);
    }

    private boolean esNumero(String cadena){
        try{ 
            int i;            
            for(i = 0; i < cadena.length();i++){
                String aux = "";
                aux += cadena.charAt(i);
                Long.parseLong(aux);
            }                        
            return true;
        }catch (NumberFormatException nfe){
            return false;
        }
    }
    
    private boolean CorregirModulo(){
        boolean res = false;
        this.labelCorreccionN.setText("");
        int min_primo = this.rsa_evaluar.GetMinPrimo();
        int max_primo = this.rsa_evaluar.GetMaxPrimo();
        String p = this.text_p_ev.getText();
        String q = this.text_q_ev.getText();
        long pe = Long.parseLong(p);
        long qu = Long.parseLong(q);
        if(pe > max_primo || qu > max_primo || pe < min_primo || qu < min_primo){
            this.labelCorreccionN.setText("Los valores ingresados no están dentro del intervalo establecido.");            
            this.labelCorreccionN.setForeground(Color.red);            
        }
        else{
            if(pe == qu){
                this.labelCorreccionN.setText("Los valores p y q deben ser distintos.");                
                this.labelCorreccionN.setForeground(Color.red);
            }
            else{
                boolean flag = false;
                if(!this.rsa_evaluar.SetP(pe)){//Si pe no es primo informo el error
                    this.labelCorreccionN.setText(p + " no es número primo. ");
                    this.labelCorreccionN.setForeground(Color.red);                    
                    flag = true;
                }            
                if(!this.rsa_evaluar.SetQ(qu)){
                    String texto = this.labelCorreccionN.getText();
                    this.labelCorreccionN.setText(texto  + q + " no es número primo.");
                    this.labelCorreccionN.setForeground(Color.red);                   
                    if(!flag) flag = true;                    
                }
                if(!flag){
                    this.rsa_evaluar.GenerarModulo();
                    this.labelCorreccionN.setText("Correcto.");
                    this.labelCorreccionN.setForeground(Color.green);
                    res = true;
                }                 
            }                        
        }
        return res;
    }
    
    private void CorregirD(){
        String texto = this.textClavePrivadaDEv.getText();
        long valor = Long.parseLong(texto);
        if(!this.rsa_evaluar.SetD(valor)){
            this.labelCorreccionD.setText("Incorrecto.");
            this.labelCorreccionD.setForeground(Color.red);
        }
        else{
            this.labelCorreccionD.setText("Correcto.");
            this.labelCorreccionD.setForeground(Color.green);
        }
    }
    
    private void CorregirE(){
        String texto = this.textClavePublicaEEv.getText();
        long valor = Long.parseLong(texto);
        if(!this.rsa_evaluar.SetE(valor)){
            this.labelCorreccionE.setText("Incorrecto.");
            this.labelCorreccionE.setForeground(Color.red);
        }
        else{
            this.labelCorreccionE.setText("Correcto.");
            this.labelCorreccionE.setForeground(Color.green);
        }
    }
    
    private void NuevoRSA(){
        this.rsa_evaluar.GenerarPrimos();
        this.rsa_evaluar.GenerarModulo();
        this.rsa_evaluar.GenerarExponentePublico();
        this.rsa_evaluar.GenerarExponentePrivado();        
    }
    
    private void LimpiarCalculosIniciales(){
        this.text_p_ev.setText("");
        this.text_q_ev.setText("");
        this.text_modulo_ev.setText("");
        this.textClavePrivadaDEv.setText("");
        this.textClavePublicaEEv.setText("");
        this.panClavePublicaEv.setEnabled(false);
        this.textClavePublicaEEv.setText("");
        this.textClavePublicaEEv.setEnabled(false);
        this.botAceptarClavePublicaEv.setEnabled(false);
        this.panClavePrivadaEv.setEnabled(false);
        this.textClavePrivadaDEv.setText("");
        this.textClavePrivadaDEv.setEnabled(false);
        this.botAceptarClavePrivadaEv.setEnabled(false);
        this.botCalcularModuloEv.setEnabled(true);
        this.botContinuarCI.setEnabled(false);
        this.botReintentarCI.setEnabled(false);
        this.botResolverCI.setEnabled(true);
        this.botCorregirCI.setEnabled(false);
        this.botNuevoCI.setEnabled(false);
        this.labelCorreccionN.setVisible(false);
        this.labelCorreccionE.setVisible(false);
        this.labelCorreccionD.setVisible(false);
        this.modulo_generado = false;
    }
    
    private void LimpiarEncriptar(){
        this.pos_encriptar = 0;
        this.rsa_evaluar.LimpiarValores();        
        this.NuevoRSA();
        long e = this.rsa_evaluar.GetE();
        long n = this.rsa_evaluar.GetModulo();
        this.text_e_encriptar.setText(Long.toString(e));
        this.text_modulo_encriptar.setText(Long.toString(n));
        this.botEncriptarBloques.setEnabled(true);
        this.botEncriptarTodo.setEnabled(true);
        this.botNuevoEncriptar.setEnabled(false);
        this.spinTamBloque.setValue(1);
        this.spinTamBloque.setEnabled(true);
        this.text_mens_encriptar.setText("Prueba");
        this.panEncriptarBloques.setEnabled(false);
        this.textResultadoBloque.setEnabled(false);
        this.textResultadoBloque.setText("");
        this.textBloqueActual.setEnabled(false);
        this.textBloqueActual.setText("");
        this.text_ascii_enc.setEnabled(false);
        this.text_ascii_enc.setText("");
        this.textResParcial.setEnabled(false);
        this.textResParcial.setText("");
        this.botAceptarBloques.setEnabled(false);
        this.botSiguienteBloque.setEnabled(false);
        this.panEncriptarTodo.setEnabled(false);
        this.textResultadoTodo.setEnabled(false);
        this.textResultadoTodo.setText("");
        this.botAceptarTodo.setEnabled(false);
        this.botCorregirEncriptar.setEnabled(false);
        this.botResolverEncriptar.setEnabled(false);
        this.botContinuarEncriptar.setEnabled(false);
        this.botReintenterEncriptar.setEnabled(false);
        this.labelCorreccionEncriptarTodo.setVisible(false);
        this.labelCorreccionEncriptarBloques.setVisible(false);
        this.text_repnum_encriptar.setText("");
        this.text_mens_encriptar.setEnabled(true);
    }
    
    private void LimpiarDesencriptar(){
        this.pos_desencriptar = 0;
        this.parcial_desenc_ascii = "";
        this.parcial_desenc_numerico = "";
        //this.rsa_evaluar.LimpiarValores();
        //this.NuevoRSA();
        this.text_d_desenc.setText(Long.toString(this.rsa_evaluar.GetD()));
        this.text_n_desenc.setText(Long.toString(this.rsa_evaluar.GetModulo()));
        /*Random rnd = new Random();
        int val = 0;
        while(val == 0) val = rnd.nextInt(3);
        this.spinTamBloqueDes.setValue(val);
        val = rnd.nextInt(this.max_lenght_encriptar + 1);
        String mensaje = this.GenerarString(val);
        this.text_mens_encriptado.setText(this.rsa_evaluar.Encrypt(mensaje, (int)this.spinTamBloqueDes.getValue()));*/
        if(this.panEncriptarTodo.isEnabled())
            this.text_mens_encriptado.setText(this.textResultadoTodo.getText());
        else{
            if(this.panEncriptarBloques.isEnabled())
                this.text_mens_encriptado.setText(this.textResParcial.getText());
        }
        this.spinTamBloqueDes.setValue((int)this.spinTamBloque.getValue());
        this.botDesencTodo.setEnabled(true);
        this.botDesencBloques.setEnabled(true);        
        this.panDesencTodo.setEnabled(false);
        this.text_resultado_des_todo.setEnabled(false);
        this.text_resultado_des_todo.setText("");
        this.text_res_decodificado_todo.setEnabled(false);
        this.text_res_decodificado_todo.setText("");
        this.labelCorreccionDesTodo.setVisible(false);
        this.botAceptarDesTodo.setEnabled(false);
        this.panDesenBloques.setEnabled(false);
        this.text_resultado_des_bloque.setEnabled(false);
        this.text_resultado_des_bloque.setText("");
        this.text_parcial_des_bloque.setEnabled(false);
        this.text_parcial_des_bloque.setText("");
        this.text_string_resultante_Des.setEnabled(false);
        this.text_string_resultante_Des.setText("");
        this.text_bloque_des_bloque.setEnabled(false);
        this.text_bloque_des_bloque.setText("");
        this.labelCorreccionDesencBloque.setVisible(false);
        this.botAceptarDesBloque.setEnabled(false);
        this.botSiguienteDesBloque.setEnabled(false);
        this.botCorregirDesencriptar.setEnabled(false);
        this.botReintentarDesencriptar.setEnabled(false);
        this.botResolverDesencriptar.setEnabled(false);
    }
    
    private boolean ValidarTextoEncriptar(String texto){
        return texto.length() <= this.max_lenght_encriptar;
    }
    
    private void LlenarDTOCalculosIniciales(RSADTO rsaDTO){
        rsaDTO.setP_CI(this.text_p_ev.getText());
        rsaDTO.setQ_CI(this.text_q_ev.getText());
        rsaDTO.setN_CI(this.text_modulo_ev.getText());
        rsaDTO.setE_CI(this.textClavePublicaEEv.getText());
        rsaDTO.setD_CI(this.textClavePrivadaDEv.getText());        
    }
    
    private void LlenarDTOEncriptar(RSADTO rsaDTO){
        rsaDTO.setMensajeEncriptar(this.text_mens_encriptar.getText());
        int value = (int) this.spinTamBloque.getValue();
        rsaDTO.setTamBloque(Integer.toString(value));
        rsaDTO.setResultadoEncriptarTodo(this.textResultadoTodo.getText());
        rsaDTO.setPosEncriptar(Long.toString(this.pos_encriptar));
        rsaDTO.setResultadoEncriptarBloques(this.textResultadoBloque.getText());
        rsaDTO.setResultadoParcialEncriptar(this.textResParcial.getText());        
    }
    
    private void LlenarDTODesencriptar(RSADTO rsaDTO){
        rsaDTO.setResultadoDesencriptarTodo(this.text_resultado_des_todo.getText());
        rsaDTO.setPosDesencriptar(Long.toString(this.pos_desencriptar));
        rsaDTO.setResultadoEncriptarBloques(this.text_resultado_des_bloque.getText());
        rsaDTO.setResultadoParcialDesencriptar(this.text_parcial_des_bloque.getText());        
    }
    
    private RSADTO generarJsonCalculosIniciales(){
        RSADTO rsaDTO = new RSADTO();
        rsaDTO.setCurrentStep(1);
        this.LlenarDTOCalculosIniciales(rsaDTO);
        return rsaDTO;
    }
    
        private RSADTO generarJsonPasoEncriptar(){
        RSADTO rsaDTO = new RSADTO();
        rsaDTO.setCurrentStep(2);
        this.LlenarDTOCalculosIniciales(rsaDTO);
        this.LlenarDTOEncriptar(rsaDTO);
        return rsaDTO;
    }
        
    private RSADTO generarJsonPasoDesencriptar(){
        RSADTO rsaDTO = new RSADTO();
        rsaDTO.setCurrentStep(3);
        this.LlenarDTOCalculosIniciales(rsaDTO);
        this.LlenarDTOEncriptar(rsaDTO);
        this.LlenarDTODesencriptar(rsaDTO);
        return rsaDTO;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoPreg1 = new javax.swing.ButtonGroup();
        grupoPreg2 = new javax.swing.ButtonGroup();
        grupoPreg3 = new javax.swing.ButtonGroup();
        grupoPreg4 = new javax.swing.ButtonGroup();
        grupoPreg5 = new javax.swing.ButtonGroup();
        grupoPreg6 = new javax.swing.ButtonGroup();
        grupoPreg7 = new javax.swing.ButtonGroup();
        grupoPreg8 = new javax.swing.ButtonGroup();
        grupoPreg9 = new javax.swing.ButtonGroup();
        grupoPreg10 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        RSAEvaluarPanel = new javax.swing.JTabbedPane();
        panelPreguntas = new javax.swing.JPanel();
        panelBotonesPreguntas = new javax.swing.JPanel();
        botCorregirPreguntas = new javax.swing.JButton();
        botResolverPreguntas = new javax.swing.JButton();
        botContinuarPreguntas = new javax.swing.JButton();
        botReintentarPreguntas = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        rbotCorrecto1 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jRadioButton4 = new javax.swing.JRadioButton();
        rbotCorrecto2 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jRadioButton7 = new javax.swing.JRadioButton();
        rbotCorrecto3 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        rbotCorrecto4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton10 = new javax.swing.JRadioButton();
        rbotCorrecto5 = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jRadioButton11 = new javax.swing.JRadioButton();
        rbotCorrecto6 = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        jRadioButton13 = new javax.swing.JRadioButton();
        rbotCorrecto7 = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        rbotCorrecto8 = new javax.swing.JRadioButton();
        jRadioButton14 = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        jRadioButton12 = new javax.swing.JRadioButton();
        rbotCorrecto9 = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        rbotCorrecto10 = new javax.swing.JRadioButton();
        jRadioButton15 = new javax.swing.JRadioButton();
        panelCalculosInicialesEv = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        text_p_ev = new javax.swing.JTextField();
        text_q_ev = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        text_modulo_ev = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        botCalcularModuloEv = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        labelCorreccionN = new javax.swing.JLabel();
        panClavePublicaEv = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        textClavePublicaEEv = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        botAceptarClavePublicaEv = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        labelCorreccionE = new javax.swing.JLabel();
        panClavePrivadaEv = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        textClavePrivadaDEv = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        botAceptarClavePrivadaEv = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        labelCorreccionD = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        botReintentarCI = new javax.swing.JButton();
        botCorregirCI = new javax.swing.JButton();
        botResolverCI = new javax.swing.JButton();
        botContinuarCI = new javax.swing.JButton();
        botNuevoCI = new javax.swing.JButton();
        botGuardarCalculosIniciales = new javax.swing.JButton();
        panelEncriptarEv = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        panEncriptarTodo = new javax.swing.JPanel();
        textResultadoTodo = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        botAceptarTodo = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        labelCorreccionEncriptarTodo = new javax.swing.JLabel();
        panEncriptarBloques = new javax.swing.JPanel();
        textResultadoBloque = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        textBloqueActual = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        botAceptarBloques = new javax.swing.JButton();
        botSiguienteBloque = new javax.swing.JButton();
        textResParcial = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        labelCorreccionEncriptarBloques = new javax.swing.JLabel();
        text_ascii_enc = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        botEncriptarTodo = new javax.swing.JButton();
        botEncriptarBloques = new javax.swing.JButton();
        text_repnum_encriptar = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        text_mens_encriptar = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        text_e_encriptar = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        text_modulo_encriptar = new javax.swing.JTextField();
        spinTamBloque = new javax.swing.JSpinner();
        jLabel30 = new javax.swing.JLabel();
        botNuevoEncriptar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        botCorregirEncriptar = new javax.swing.JButton();
        botReintenterEncriptar = new javax.swing.JButton();
        botResolverEncriptar = new javax.swing.JButton();
        botContinuarEncriptar = new javax.swing.JButton();
        botGuardarEncriptar = new javax.swing.JButton();
        panelDesencriptarEv = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        text_mens_encriptado = new javax.swing.JTextField();
        text_d_desenc = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        text_n_desenc = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        botDesencTodo = new javax.swing.JButton();
        botDesencBloques = new javax.swing.JButton();
        spinTamBloqueDes = new javax.swing.JSpinner();
        jLabel42 = new javax.swing.JLabel();
        panDesencTodo = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        text_resultado_des_todo = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        labelCorreccionDesTodo = new javax.swing.JLabel();
        botAceptarDesTodo = new javax.swing.JButton();
        text_res_decodificado_todo = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        panDesenBloques = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        text_resultado_des_bloque = new javax.swing.JTextField();
        text_parcial_des_bloque = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        text_bloque_des_bloque = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        botAceptarDesBloque = new javax.swing.JButton();
        botSiguienteDesBloque = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        labelCorreccionDesencBloque = new javax.swing.JLabel();
        text_string_resultante_Des = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        botCorregirDesencriptar = new javax.swing.JButton();
        botReintentarDesencriptar = new javax.swing.JButton();
        botResolverDesencriptar = new javax.swing.JButton();
        botGuardarDesencriptar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(860, 679));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("RSA - Modo Evaluar");

        panelBotonesPreguntas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        botCorregirPreguntas.setText("Corregir");
        botCorregirPreguntas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botCorregirPreguntasMousePressed(evt);
            }
        });

        botResolverPreguntas.setText("Resolver");
        botResolverPreguntas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botResolverPreguntasMousePressed(evt);
            }
        });

        botContinuarPreguntas.setText("Continuar");
        botContinuarPreguntas.setEnabled(false);
        botContinuarPreguntas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botContinuarPreguntasMousePressed(evt);
            }
        });

        botReintentarPreguntas.setText("Reintentar");
        botReintentarPreguntas.setEnabled(false);
        botReintentarPreguntas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botReintentarPreguntasMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelBotonesPreguntasLayout = new javax.swing.GroupLayout(panelBotonesPreguntas);
        panelBotonesPreguntas.setLayout(panelBotonesPreguntasLayout);
        panelBotonesPreguntasLayout.setHorizontalGroup(
            panelBotonesPreguntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotonesPreguntasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBotonesPreguntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botContinuarPreguntas, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBotonesPreguntasLayout.createSequentialGroup()
                        .addComponent(botCorregirPreguntas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botReintentarPreguntas)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botResolverPreguntas)
                .addGap(103, 103, 103))
        );
        panelBotonesPreguntasLayout.setVerticalGroup(
            panelBotonesPreguntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotonesPreguntasLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(panelBotonesPreguntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botCorregirPreguntas)
                    .addComponent(botResolverPreguntas)
                    .addComponent(botReintentarPreguntas))
                .addGap(3, 3, 3)
                .addComponent(botContinuarPreguntas)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 0, 102), null));

        grupoPreg1.add(jRadioButton1);
        jRadioButton1.setText("Aleatorio");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        grupoPreg1.add(jRadioButton2);
        jRadioButton2.setText("Lineal");

        grupoPreg1.add(rbotCorrecto1);
        rbotCorrecto1.setText("Exponencial");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel2.setText("<html>1 - El problema de la factorización entera requiere un tiempo de cómputo, en función<br> del tamaño de la entrada, de tipo:</html>");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel3.setText("2 - Si una clave RSA usa p = 7, q = 11 y  d = 7, calcuando mentalmente, el valor de e es:");

        grupoPreg2.add(jRadioButton4);
        jRadioButton4.setText("13");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        grupoPreg2.add(rbotCorrecto2);
        rbotCorrecto2.setText("43");

        grupoPreg2.add(jRadioButton6);
        jRadioButton6.setText("63");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel4.setText("3 - Si tenemos p = 3, q = 5, d= 3, e = 3, entonces la clave pública es:");

        grupoPreg3.add(jRadioButton7);
        jRadioButton7.setText("(15, 5)");

        grupoPreg3.add(rbotCorrecto3);
        rbotCorrecto3.setText("(15, 3)");

        grupoPreg3.add(jRadioButton9);
        jRadioButton9.setText("(3, 3)");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel5.setText("<html>4 - Si con el valor n = 46031, producto de dos primos, ciframos el valor 48065, ¿qué valor<br> real estamos cifrando?</html>");

        grupoPreg4.add(rbotCorrecto4);
        rbotCorrecto4.setText("2034");

        grupoPreg4.add(jRadioButton5);
        jRadioButton5.setText("-2034");

        grupoPreg4.add(jRadioButton8);
        jRadioButton8.setText("48065");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel6.setText("5 - Si se desea realizar la firma sobre un número con RSA, deberá usarse:");

        grupoPreg5.add(jRadioButton3);
        jRadioButton3.setText("La clave pública del receptor");

        grupoPreg5.add(jRadioButton10);
        jRadioButton10.setText("La clave pública del emisor");

        grupoPreg5.add(rbotCorrecto5);
        rbotCorrecto5.setText("La clave privada del emisor");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(rbotCorrecto1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(rbotCorrecto2)
                                .addComponent(jRadioButton4)
                                .addComponent(jRadioButton6))
                            .addComponent(jRadioButton7)
                            .addComponent(rbotCorrecto3)
                            .addComponent(jRadioButton9)
                            .addComponent(rbotCorrecto4)
                            .addComponent(jRadioButton5)
                            .addComponent(jRadioButton8)
                            .addComponent(jRadioButton3)
                            .addComponent(jRadioButton10)
                            .addComponent(rbotCorrecto5)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 13, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbotCorrecto1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbotCorrecto2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbotCorrecto3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbotCorrecto4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbotCorrecto5))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 0, 102), null));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel7.setText("<html>Considerando  n = 2773,  e = 17, d = 157. <br> Responder Verdadero o Falso. </html>");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel9.setText("1 - Solo se pueden cifrar números en RSA, por lo tanto no se puede encriptar texto. ");

        grupoPreg6.add(jRadioButton11);
        jRadioButton11.setText("Verdadero");

        grupoPreg6.add(rbotCorrecto6);
        rbotCorrecto6.setText("Falso");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel10.setText("<html>2- La cuenta para cifrar un valor M es: C = M<sup>157</sup>mod2773.</html>");

        grupoPreg7.add(jRadioButton13);
        jRadioButton13.setText("Verdadero");

        grupoPreg7.add(rbotCorrecto7);
        rbotCorrecto7.setText("Falso");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel11.setText("3- El resultado de encriptar el caracter 'K' usando código ASCII es 1982.");

        grupoPreg8.add(rbotCorrecto8);
        rbotCorrecto8.setText("Verdadero");

        grupoPreg8.add(jRadioButton14);
        jRadioButton14.setText("Falso");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel12.setText("4 - El resultado de desencriptar el valor 505 es el caracter 'h'.");

        grupoPreg9.add(jRadioButton12);
        jRadioButton12.setText("Verdadero");

        grupoPreg9.add(rbotCorrecto9);
        rbotCorrecto9.setText("Falso");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel13.setText("5 - Si elegimos p = 17 y q = 23, entonces podemos elegir d = 77.");

        grupoPreg10.add(rbotCorrecto10);
        rbotCorrecto10.setText("Verdadero");

        grupoPreg10.add(jRadioButton15);
        jRadioButton15.setText("Falso");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addComponent(jRadioButton11)
                                .addGap(32, 32, 32)
                                .addComponent(rbotCorrecto6))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(124, 124, 124)
                                .addComponent(jRadioButton13)
                                .addGap(32, 32, 32)
                                .addComponent(rbotCorrecto7))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(124, 124, 124)
                                .addComponent(rbotCorrecto8)
                                .addGap(29, 29, 29)
                                .addComponent(jRadioButton14))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addComponent(jRadioButton12)
                                .addGap(30, 30, 30)
                                .addComponent(rbotCorrecto9))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(122, 122, 122)
                                .addComponent(rbotCorrecto10)
                                .addGap(30, 30, 30)
                                .addComponent(jRadioButton15)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton11)
                    .addComponent(rbotCorrecto6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbotCorrecto7)
                    .addComponent(jRadioButton13))
                .addGap(23, 23, 23)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbotCorrecto8)
                    .addComponent(jRadioButton14))
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbotCorrecto9)
                    .addComponent(jRadioButton12))
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton15)
                    .addComponent(rbotCorrecto10))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelPreguntasLayout = new javax.swing.GroupLayout(panelPreguntas);
        panelPreguntas.setLayout(panelPreguntasLayout);
        panelPreguntasLayout.setHorizontalGroup(
            panelPreguntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPreguntasLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPreguntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBotonesPreguntas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelPreguntasLayout.setVerticalGroup(
            panelPreguntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPreguntasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelPreguntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelPreguntasLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(panelBotonesPreguntas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(180, Short.MAX_VALUE))
        );

        RSAEvaluarPanel.addTab("Preguntas", panelPreguntas);

        jLabel8.setText("<html>En esta etapa deberá definir los parámetros <strong>n, d y e</strong> a partir de los números primos <strong>p y q</strong></html>.");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Calcular Módulo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(51, 0, 51)));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel14.setText("p");

        jLabel15.setText("q");

        jLabel16.setText("<html>Ingresar números primos <strong>p</strong> y <strong>q</strong> en el intervalo <strong>(601, 811)</strong> para calcular el módulo <strong>n</strong>.</html>");

        text_modulo_ev.setEditable(false);

        jLabel17.setText("n");

        botCalcularModuloEv.setText("Validar");
        botCalcularModuloEv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botCalcularModuloEvMousePressed(evt);
            }
        });

        labelCorreccionN.setText("Corrección:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCorreccionN, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelCorreccionN)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel16)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botCalcularModuloEv, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                    .addComponent(text_p_ev))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(text_q_ev, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(text_modulo_ev, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(190, 190, 190))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(text_q_ev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(text_modulo_ev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel17))
                            .addComponent(botCalcularModuloEv)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(text_p_ev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        panClavePublicaEv.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Definir clave pública", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(51, 0, 51)));
        panClavePublicaEv.setEnabled(false);

        jLabel18.setText("<html>En base al <strong>módulo</strong> calculado, calcule el valor de la <br>componente de la clave pública e.<br>Recuerde que debe pertenecer al intervalo<br><strong>[max(p,q)+1,n-1]</strong> y que <strong>MCD(e, (p-1)&times;(q-1)) = 1</strong></html>");

        textClavePublicaEEv.setEnabled(false);

        jLabel19.setText("e:");

        botAceptarClavePublicaEv.setText("Aceptar");
        botAceptarClavePublicaEv.setEnabled(false);
        botAceptarClavePublicaEv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botAceptarClavePublicaEvMousePressed(evt);
            }
        });

        labelCorreccionE.setText("Corrección:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCorreccionE, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCorreccionE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panClavePublicaEvLayout = new javax.swing.GroupLayout(panClavePublicaEv);
        panClavePublicaEv.setLayout(panClavePublicaEvLayout);
        panClavePublicaEvLayout.setHorizontalGroup(
            panClavePublicaEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panClavePublicaEvLayout.createSequentialGroup()
                .addGroup(panClavePublicaEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panClavePublicaEvLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panClavePublicaEvLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(panClavePublicaEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panClavePublicaEvLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textClavePublicaEEv, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botAceptarClavePublicaEv)))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        panClavePublicaEvLayout.setVerticalGroup(
            panClavePublicaEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panClavePublicaEvLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panClavePublicaEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textClavePublicaEEv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(botAceptarClavePublicaEv))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panClavePrivadaEv.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Definir clave privada", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(51, 0, 51)));
        panClavePrivadaEv.setEnabled(false);

        jLabel20.setText("<html>Teniendo en cuenta el valor <strong>e</strong> calculado, calcule el valor de la <br>componente de la clave privada d.<br>Recuerde que <strong>d</strong> es el multiplicador modular inverso de <br><strong>e mod (p-1)&times;(q-1)</strong></html>");

        textClavePrivadaDEv.setEnabled(false);

        jLabel21.setText("d:");

        botAceptarClavePrivadaEv.setText("Aceptar");
        botAceptarClavePrivadaEv.setEnabled(false);
        botAceptarClavePrivadaEv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botAceptarClavePrivadaEvMousePressed(evt);
            }
        });

        labelCorreccionD.setText("Corrección:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCorreccionD, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCorreccionD)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panClavePrivadaEvLayout = new javax.swing.GroupLayout(panClavePrivadaEv);
        panClavePrivadaEv.setLayout(panClavePrivadaEvLayout);
        panClavePrivadaEvLayout.setHorizontalGroup(
            panClavePrivadaEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panClavePrivadaEvLayout.createSequentialGroup()
                .addGroup(panClavePrivadaEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panClavePrivadaEvLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panClavePrivadaEvLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(panClavePrivadaEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panClavePrivadaEvLayout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textClavePrivadaDEv, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botAceptarClavePrivadaEv)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panClavePrivadaEvLayout.setVerticalGroup(
            panClavePrivadaEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panClavePrivadaEvLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panClavePrivadaEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textClavePrivadaDEv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botAceptarClavePrivadaEv)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 0, 51), null));

        botReintentarCI.setText("Reintentar");
        botReintentarCI.setEnabled(false);
        botReintentarCI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botReintentarCIMousePressed(evt);
            }
        });

        botCorregirCI.setText("Corregir");
        botCorregirCI.setEnabled(false);
        botCorregirCI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botCorregirCIMousePressed(evt);
            }
        });

        botResolverCI.setText("Resolver");
        botResolverCI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botResolverCIMousePressed(evt);
            }
        });

        botContinuarCI.setText("Continuar");
        botContinuarCI.setEnabled(false);
        botContinuarCI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botContinuarCIMousePressed(evt);
            }
        });

        botNuevoCI.setText("Nuevo");
        botNuevoCI.setEnabled(false);
        botNuevoCI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botNuevoCIMousePressed(evt);
            }
        });

        botGuardarCalculosIniciales.setText("Guardar");
        botGuardarCalculosIniciales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botGuardarCalculosInicialesMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(199, 199, 199)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botGuardarCalculosIniciales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botCorregirCI, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(botReintentarCI)
                        .addGap(18, 18, 18)
                        .addComponent(botResolverCI, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(botContinuarCI, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botNuevoCI, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botReintentarCI)
                    .addComponent(botResolverCI)
                    .addComponent(botCorregirCI))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botContinuarCI)
                    .addComponent(botNuevoCI)
                    .addComponent(botGuardarCalculosIniciales))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelCalculosInicialesEvLayout = new javax.swing.GroupLayout(panelCalculosInicialesEv);
        panelCalculosInicialesEv.setLayout(panelCalculosInicialesEvLayout);
        panelCalculosInicialesEvLayout.setHorizontalGroup(
            panelCalculosInicialesEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCalculosInicialesEvLayout.createSequentialGroup()
                .addGroup(panelCalculosInicialesEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCalculosInicialesEvLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCalculosInicialesEvLayout.createSequentialGroup()
                        .addContainerGap(136, Short.MAX_VALUE)
                        .addGroup(panelCalculosInicialesEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCalculosInicialesEvLayout.createSequentialGroup()
                                .addComponent(panClavePublicaEv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panClavePrivadaEv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        panelCalculosInicialesEvLayout.setVerticalGroup(
            panelCalculosInicialesEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCalculosInicialesEvLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCalculosInicialesEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panClavePublicaEv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panClavePrivadaEv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(184, 184, 184))
        );

        panClavePublicaEv.getAccessibleContext().setAccessibleName("Definir clave pública");

        RSAEvaluarPanel.addTab("Cálculos Iniciales", panelCalculosInicialesEv);

        jLabel22.setText("<html>De acuerdo a los parámetros <strong>(n, e)</strong> indicados, encripte un mensaje. Recuerde que cada bloque de 1 o 2 caracteres debe representarse con 6 dígitos.</html>");

        panEncriptarTodo.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 0, 51), null));
        panEncriptarTodo.setEnabled(false);

        textResultadoTodo.setEnabled(false);
        textResultadoTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textResultadoTodoActionPerformed(evt);
            }
        });

        jLabel27.setText("Resultado:");

        botAceptarTodo.setText("Aceptar");
        botAceptarTodo.setEnabled(false);
        botAceptarTodo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botAceptarTodoMousePressed(evt);
            }
        });

        labelCorreccionEncriptarTodo.setText("Corrección:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCorreccionEncriptarTodo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(labelCorreccionEncriptarTodo)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panEncriptarTodoLayout = new javax.swing.GroupLayout(panEncriptarTodo);
        panEncriptarTodo.setLayout(panEncriptarTodoLayout);
        panEncriptarTodoLayout.setHorizontalGroup(
            panEncriptarTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panEncriptarTodoLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panEncriptarTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textResultadoTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 803, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panEncriptarTodoLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(botAceptarTodo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panEncriptarTodoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panEncriptarTodoLayout.setVerticalGroup(
            panEncriptarTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panEncriptarTodoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panEncriptarTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textResultadoTodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botAceptarTodo)
                .addGap(35, 35, 35))
        );

        panEncriptarBloques.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 0, 51), null));
        panEncriptarBloques.setEnabled(false);

        textResultadoBloque.setEnabled(false);
        textResultadoBloque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textResultadoBloqueActionPerformed(evt);
            }
        });

        jLabel28.setText("Resultado:");

        textBloqueActual.setEditable(false);
        textBloqueActual.setEnabled(false);

        jLabel29.setText("Bloque:");

        botAceptarBloques.setText("Aceptar");
        botAceptarBloques.setEnabled(false);
        botAceptarBloques.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botAceptarBloquesMousePressed(evt);
            }
        });

        botSiguienteBloque.setText("Siguiente");
        botSiguienteBloque.setEnabled(false);
        botSiguienteBloque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botSiguienteBloqueMousePressed(evt);
            }
        });

        textResParcial.setEditable(false);
        textResParcial.setEnabled(false);
        textResParcial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textResParcialActionPerformed(evt);
            }
        });

        jLabel31.setText("Parcial:");

        labelCorreccionEncriptarBloques.setText("Corrección:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCorreccionEncriptarBloques)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(labelCorreccionEncriptarBloques)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        text_ascii_enc.setEditable(false);
        text_ascii_enc.setEnabled(false);

        jLabel41.setText("Rep. Numerica:");

        javax.swing.GroupLayout panEncriptarBloquesLayout = new javax.swing.GroupLayout(panEncriptarBloques);
        panEncriptarBloques.setLayout(panEncriptarBloquesLayout);
        panEncriptarBloquesLayout.setHorizontalGroup(
            panEncriptarBloquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panEncriptarBloquesLayout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(botAceptarBloques)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botSiguienteBloque)
                .addContainerGap(609, Short.MAX_VALUE))
            .addGroup(panEncriptarBloquesLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panEncriptarBloquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel28)
                    .addComponent(jLabel31)
                    .addComponent(jLabel41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panEncriptarBloquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textResultadoBloque)
                    .addComponent(textResParcial)
                    .addGroup(panEncriptarBloquesLayout.createSequentialGroup()
                        .addComponent(textBloqueActual, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_ascii_enc, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panEncriptarBloquesLayout.setVerticalGroup(
            panEncriptarBloquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panEncriptarBloquesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panEncriptarBloquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textResultadoBloque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panEncriptarBloquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textResParcial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panEncriptarBloquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textBloqueActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(text_ascii_enc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(panEncriptarBloquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botAceptarBloques)
                    .addComponent(botSiguienteBloque))
                .addContainerGap())
        );

        botEncriptarTodo.setText("Todo");
        botEncriptarTodo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botEncriptarTodoMousePressed(evt);
            }
        });

        botEncriptarBloques.setText("Bloques");
        botEncriptarBloques.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botEncriptarBloquesMousePressed(evt);
            }
        });

        text_repnum_encriptar.setEditable(false);

        jLabel26.setText("Rep. Numérica:");

        jLabel25.setText("Mensaje:");

        text_mens_encriptar.setText("Prueba");

        jLabel24.setText("e:");

        text_e_encriptar.setEditable(false);

        jLabel23.setText("n:");

        text_modulo_encriptar.setEditable(false);

        spinTamBloque.setModel(new javax.swing.SpinnerNumberModel(1, 1, 2, 1));

        jLabel30.setText("Tamaño Bloque:");

        botNuevoEncriptar.setText("Nuevo");
        botNuevoEncriptar.setEnabled(false);
        botNuevoEncriptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botNuevoEncriptarMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botEncriptarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botEncriptarBloques, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botNuevoEncriptar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(text_repnum_encriptar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(text_mens_encriptar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_e_encriptar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_modulo_encriptar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinTamBloque, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 289, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_mens_encriptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel24)
                    .addComponent(text_e_encriptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(text_modulo_encriptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(spinTamBloque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_repnum_encriptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botEncriptarTodo)
                    .addComponent(botEncriptarBloques)
                    .addComponent(botNuevoEncriptar))
                .addGap(55, 55, 55))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 51), null));

        botCorregirEncriptar.setText("Corregir");
        botCorregirEncriptar.setEnabled(false);
        botCorregirEncriptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botCorregirEncriptarMousePressed(evt);
            }
        });

        botReintenterEncriptar.setText("Reintentar");
        botReintenterEncriptar.setEnabled(false);
        botReintenterEncriptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botReintenterEncriptarMousePressed(evt);
            }
        });

        botResolverEncriptar.setText("Resolver");
        botResolverEncriptar.setEnabled(false);
        botResolverEncriptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botResolverEncriptarMousePressed(evt);
            }
        });

        botContinuarEncriptar.setText("Continuar");
        botContinuarEncriptar.setEnabled(false);
        botContinuarEncriptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botContinuarEncriptarMousePressed(evt);
            }
        });

        botGuardarEncriptar.setText("Guardar");
        botGuardarEncriptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botGuardarEncriptarMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(botCorregirEncriptar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botReintenterEncriptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botResolverEncriptar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(botGuardarEncriptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botContinuarEncriptar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botCorregirEncriptar)
                    .addComponent(botReintenterEncriptar)
                    .addComponent(botResolverEncriptar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botContinuarEncriptar)
                    .addComponent(botGuardarEncriptar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelEncriptarEvLayout = new javax.swing.GroupLayout(panelEncriptarEv);
        panelEncriptarEv.setLayout(panelEncriptarEvLayout);
        panelEncriptarEvLayout.setHorizontalGroup(
            panelEncriptarEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncriptarEvLayout.createSequentialGroup()
                .addGroup(panelEncriptarEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEncriptarEvLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelEncriptarEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panEncriptarBloques, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panEncriptarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 908, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelEncriptarEvLayout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelEncriptarEvLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel22)))
                .addContainerGap())
        );
        panelEncriptarEvLayout.setVerticalGroup(
            panelEncriptarEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncriptarEvLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panEncriptarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panEncriptarBloques, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
        );

        RSAEvaluarPanel.addTab("Encriptar", panelEncriptarEv);

        jLabel32.setText("<html>De acuerdo a los parámetros <strong>(n, d)</strong> desencripte el mensaje del paso anterior. Recuerde que los bloques son de 6 dígitos y que debe tener en cuenta si se encriptó<br> de a 1 o de a 2 caracteres y que cada caracter ASCII desencriptado se representa con 3 dígitos.</html> ");

        jLabel33.setText("Mensaje encriptado:");

        text_mens_encriptado.setEditable(false);

        text_d_desenc.setEditable(false);

        jLabel34.setText("d:");

        text_n_desenc.setEditable(false);

        jLabel35.setText("n:");

        botDesencTodo.setText("Todo");
        botDesencTodo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botDesencTodoMousePressed(evt);
            }
        });
        botDesencTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botDesencTodoActionPerformed(evt);
            }
        });

        botDesencBloques.setText("Bloques");
        botDesencBloques.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botDesencBloquesMousePressed(evt);
            }
        });

        spinTamBloqueDes.setModel(new javax.swing.SpinnerNumberModel(1, 1, 2, 1));
        spinTamBloqueDes.setEnabled(false);

        jLabel42.setText("Tamaño Bloque:");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel34)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(text_mens_encriptado)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(text_d_desenc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_n_desenc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel42)
                        .addGap(2, 2, 2)
                        .addComponent(spinTamBloqueDes, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 379, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botDesencTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botDesencBloques, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(text_mens_encriptado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_d_desenc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(text_n_desenc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(spinTamBloqueDes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botDesencBloques)
                    .addComponent(botDesencTodo))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        panDesencTodo.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 0, 51), null));
        panDesencTodo.setEnabled(false);

        jLabel36.setText("Resultado numérico:");

        text_resultado_des_todo.setEnabled(false);

        labelCorreccionDesTodo.setText("Corrección:");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCorreccionDesTodo, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelCorreccionDesTodo)
                .addContainerGap())
        );

        botAceptarDesTodo.setText("Aceptar");
        botAceptarDesTodo.setEnabled(false);
        botAceptarDesTodo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botAceptarDesTodoMousePressed(evt);
            }
        });

        text_res_decodificado_todo.setEditable(false);
        text_res_decodificado_todo.setEnabled(false);

        jLabel43.setText("Mensaje Original:");

        javax.swing.GroupLayout panDesencTodoLayout = new javax.swing.GroupLayout(panDesencTodo);
        panDesencTodo.setLayout(panDesencTodoLayout);
        panDesencTodoLayout.setHorizontalGroup(
            panDesencTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panDesencTodoLayout.createSequentialGroup()
                .addGroup(panDesencTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panDesencTodoLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(panDesencTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panDesencTodoLayout.createSequentialGroup()
                                .addGroup(panDesencTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel36)
                                    .addComponent(jLabel43))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panDesencTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(text_res_decodificado_todo)
                                    .addComponent(text_resultado_des_todo, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)))))
                    .addGroup(panDesencTodoLayout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(botAceptarDesTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panDesencTodoLayout.setVerticalGroup(
            panDesencTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panDesencTodoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panDesencTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(text_resultado_des_todo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panDesencTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_res_decodificado_todo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botAceptarDesTodo)
                .addContainerGap())
        );

        panDesenBloques.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 0, 51), null));
        panDesenBloques.setEnabled(false);

        jLabel37.setText("Resultado numérico:");

        text_resultado_des_bloque.setEnabled(false);

        text_parcial_des_bloque.setEditable(false);
        text_parcial_des_bloque.setEnabled(false);

        jLabel38.setText("Parcial:");

        text_bloque_des_bloque.setEditable(false);
        text_bloque_des_bloque.setEnabled(false);

        jLabel39.setText("Bloque:");

        botAceptarDesBloque.setText("Aceptar");
        botAceptarDesBloque.setEnabled(false);
        botAceptarDesBloque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botAceptarDesBloqueMousePressed(evt);
            }
        });

        botSiguienteDesBloque.setText("Siguiente");
        botSiguienteDesBloque.setEnabled(false);
        botSiguienteDesBloque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botSiguienteDesBloqueMousePressed(evt);
            }
        });

        labelCorreccionDesencBloque.setText("Corrección:");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCorreccionDesencBloque, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCorreccionDesencBloque)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        text_string_resultante_Des.setEditable(false);
        text_string_resultante_Des.setEnabled(false);

        jLabel40.setText("Mensaje Original:");

        javax.swing.GroupLayout panDesenBloquesLayout = new javax.swing.GroupLayout(panDesenBloques);
        panDesenBloques.setLayout(panDesenBloquesLayout);
        panDesenBloquesLayout.setHorizontalGroup(
            panDesenBloquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panDesenBloquesLayout.createSequentialGroup()
                .addGroup(panDesenBloquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panDesenBloquesLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(panDesenBloquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel37)
                            .addComponent(jLabel38)
                            .addComponent(jLabel39)
                            .addComponent(jLabel40))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panDesenBloquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(text_resultado_des_bloque, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(text_bloque_des_bloque, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(text_parcial_des_bloque, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                            .addComponent(text_string_resultante_Des)))
                    .addGroup(panDesenBloquesLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panDesenBloquesLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(botAceptarDesBloque, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botSiguienteDesBloque, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panDesenBloquesLayout.setVerticalGroup(
            panDesenBloquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panDesenBloquesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panDesenBloquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_resultado_des_bloque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panDesenBloquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_parcial_des_bloque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panDesenBloquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_string_resultante_Des, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panDesenBloquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_bloque_des_bloque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addGap(18, 18, 18)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panDesenBloquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botSiguienteDesBloque)
                    .addComponent(botAceptarDesBloque)))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 0, 51), null));

        botCorregirDesencriptar.setText("Corregir");
        botCorregirDesencriptar.setEnabled(false);
        botCorregirDesencriptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botCorregirDesencriptarMousePressed(evt);
            }
        });

        botReintentarDesencriptar.setText("Reintentar");
        botReintentarDesencriptar.setEnabled(false);
        botReintentarDesencriptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botReintentarDesencriptarMousePressed(evt);
            }
        });

        botResolverDesencriptar.setText("Resolver");
        botResolverDesencriptar.setEnabled(false);
        botResolverDesencriptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botResolverDesencriptarMousePressed(evt);
            }
        });

        botGuardarDesencriptar.setText("Guardar");
        botGuardarDesencriptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botGuardarDesencriptarMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botCorregirDesencriptar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botReintentarDesencriptar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botGuardarDesencriptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botResolverDesencriptar, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                .addContainerGap(157, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botResolverDesencriptar)
                    .addComponent(botCorregirDesencriptar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botGuardarDesencriptar)
                    .addComponent(botReintentarDesencriptar))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelDesencriptarEvLayout = new javax.swing.GroupLayout(panelDesencriptarEv);
        panelDesencriptarEv.setLayout(panelDesencriptarEvLayout);
        panelDesencriptarEvLayout.setHorizontalGroup(
            panelDesencriptarEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDesencriptarEvLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDesencriptarEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panDesencTodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panDesenBloques, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelDesencriptarEvLayout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 892, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 16, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelDesencriptarEvLayout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelDesencriptarEvLayout.setVerticalGroup(
            panelDesencriptarEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDesencriptarEvLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panDesencTodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panDesenBloques, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        RSAEvaluarPanel.addTab("Desencriptar", panelDesencriptarEv);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(327, 327, 327)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RSAEvaluarPanel)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RSAEvaluarPanel)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void botResolverPreguntasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botResolverPreguntasMousePressed
        this.rbotCorrecto1.setForeground(Color.green);
        this.rbotCorrecto2.setForeground(Color.green);
        this.rbotCorrecto3.setForeground(Color.green);
        this.rbotCorrecto4.setForeground(Color.green);
        this.rbotCorrecto5.setForeground(Color.green);
        this.rbotCorrecto6.setForeground(Color.green);
        this.rbotCorrecto7.setForeground(Color.green);
        this.rbotCorrecto8.setForeground(Color.green);
        this.rbotCorrecto9.setForeground(Color.green);
        this.rbotCorrecto10.setForeground(Color.green);
        this.botContinuarPreguntas.setEnabled(true);
        this.botCorregirPreguntas.setEnabled(false);
        this.botResolverPreguntas.setEnabled(false);
        this.botReintentarPreguntas.setEnabled(true);
    }//GEN-LAST:event_botResolverPreguntasMousePressed

    private void botCorregirPreguntasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botCorregirPreguntasMousePressed
        short correctas = 0;
        if(this.rbotCorrecto1.isSelected()){
            correctas++;
            this.rbotCorrecto1.setForeground(Color.green);
        }
        else this.rbotCorrecto1.setForeground(Color.red);
        if(this.rbotCorrecto2.isSelected()){
            correctas++;
            this.rbotCorrecto2.setForeground(Color.green);
        }
        else this.rbotCorrecto2.setForeground(Color.red);
        if(this.rbotCorrecto3.isSelected()){
            correctas++;
            this.rbotCorrecto3.setForeground(Color.green);
        }
        else this.rbotCorrecto3.setForeground(Color.red);
        if(this.rbotCorrecto4.isSelected()){
            correctas++;
            this.rbotCorrecto4.setForeground(Color.green);
        }
        else this.rbotCorrecto4.setForeground(Color.red);
        if(this.rbotCorrecto5.isSelected()){
            correctas++;
            this.rbotCorrecto5.setForeground(Color.green);
        }
        else this.rbotCorrecto5.setForeground(Color.red);
        if(this.rbotCorrecto6.isSelected()){
            correctas++;
            this.rbotCorrecto6.setForeground(Color.green);
        }
        else this.rbotCorrecto6.setForeground(Color.red);
        if(this.rbotCorrecto7.isSelected()){
            correctas++;
            this.rbotCorrecto7.setForeground(Color.green);
        }
        else this.rbotCorrecto7.setForeground(Color.red);
        if(this.rbotCorrecto8.isSelected()){
            correctas++;
            this.rbotCorrecto8.setForeground(Color.green);
        }
        else this.rbotCorrecto8.setForeground(Color.red);
        if(this.rbotCorrecto9.isSelected()){
            correctas++;
            this.rbotCorrecto9.setForeground(Color.green);
        }
        else this.rbotCorrecto9.setForeground(Color.red);
        if(this.rbotCorrecto10.isSelected()){
            correctas++;
            this.rbotCorrecto10.setForeground(Color.green);
        }
        else this.rbotCorrecto10.setForeground(Color.red);
        
        JOptionPane.showMessageDialog(null, "Correctas: " + correctas + "/10");
        
        this.botCorregirPreguntas.setEnabled(false);
        this.botReintentarPreguntas.setEnabled(true);
        this.botContinuarPreguntas.setEnabled(true);
    }//GEN-LAST:event_botCorregirPreguntasMousePressed

    private void botReintentarPreguntasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botReintentarPreguntasMousePressed
        this.botReintentarPreguntas.setEnabled(false);
        this.botCorregirPreguntas.setEnabled(true);
        this.ResetearPreguntas();
    }//GEN-LAST:event_botReintentarPreguntasMousePressed

    private void botContinuarPreguntasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botContinuarPreguntasMousePressed
        this.RSAEvaluarPanel.setEnabledAt(this.RSAEvaluarPanel.getSelectedIndex() + 1, true);
        this.RSAEvaluarPanel.setSelectedIndex(this.RSAEvaluarPanel.getSelectedIndex() + 1);
    }//GEN-LAST:event_botContinuarPreguntasMousePressed

    private void botCalcularModuloEvMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botCalcularModuloEvMousePressed

        String p = this.text_p_ev.getText();
        String q = this.text_q_ev.getText();
        if(p.isEmpty() || q.isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese números primos p y q para calcular el módulo.");
            this.LimpiarCalculosIniciales();
        }
        else{
            if(!this.esNumero(p) || !this.esNumero(q)){
                JOptionPane.showMessageDialog(null, "Debe ingresar valores numéricos.");          
                this.LimpiarCalculosIniciales();
            }
            else{
                if(this.CorregirModulo()){
                    long pe = Long.parseLong(p);
                    long qu = Long.parseLong(q);
                    long modulo = pe * qu;
                    this.text_modulo_ev.setText(Long.toString(modulo));                
                    this.panClavePublicaEv.setEnabled(true);
                    this.textClavePublicaEEv.setEnabled(true);
                    this.botAceptarClavePublicaEv.setEnabled(true);
                    this.botCalcularModuloEv.setEnabled(false);
                    this.botNuevoCI.setEnabled(true);
                    this.modulo_generado = true;
                }
                this.labelCorreccionN.setVisible(true);
            }
        }         
    }//GEN-LAST:event_botCalcularModuloEvMousePressed

    private void botContinuarCIMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botContinuarCIMousePressed
        this.RSAEvaluarPanel.setEnabledAt(this.RSAEvaluarPanel.getSelectedIndex() + 1, true);
        this.RSAEvaluarPanel.setSelectedIndex(this.RSAEvaluarPanel.getSelectedIndex() + 1);
        //this.rsa_evaluar.LimpiarValores();
        //this.NuevoRSA();
        long e = this.rsa_evaluar.GetE();
        long n = this.rsa_evaluar.GetModulo();
        this.text_e_encriptar.setText(Long.toString(e));
        this.text_modulo_encriptar.setText(Long.toString(n));
    }//GEN-LAST:event_botContinuarCIMousePressed

    private void botCorregirCIMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botCorregirCIMousePressed
        this.CorregirModulo();
        this.CorregirE();
        this.CorregirD();        
        this.labelCorreccionN.setVisible(true);
        this.labelCorreccionD.setVisible(true);
        this.labelCorreccionE.setVisible(true);       
        this.botReintentarCI.setEnabled(true);
        this.botCorregirCI.setEnabled(false);
        this.botResolverCI.setEnabled(false);
    }//GEN-LAST:event_botCorregirCIMousePressed

    private void botReintentarCIMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botReintentarCIMousePressed
        this.panClavePublicaEv.setEnabled(true);
        this.textClavePublicaEEv.setEnabled(true);
        this.textClavePublicaEEv.setText("");
        this.botAceptarClavePublicaEv.setEnabled(true);
        this.labelCorreccionE.setVisible(false);
        this.panClavePrivadaEv.setEnabled(false);
        this.textClavePrivadaDEv.setEnabled(false);
        this.textClavePrivadaDEv.setText("");
        this.botAceptarClavePrivadaEv.setEnabled(false);
        this.labelCorreccionD.setVisible(false);
        
        this.botCorregirCI.setEnabled(false);
        this.botReintentarCI.setEnabled(false);
        this.botResolverCI.setEnabled(true);
        this.botContinuarCI.setEnabled(false);
        this.botNuevoCI.setEnabled(true);
    }//GEN-LAST:event_botReintentarCIMousePressed

    private void botAceptarClavePublicaEvMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botAceptarClavePublicaEvMousePressed
        String texto = this.textClavePublicaEEv.getText();        
        if(texto.isEmpty())
            JOptionPane.showMessageDialog(null, "Ingrese el valor D.");
        else{
            if(!this.esNumero(texto)){                            
                JOptionPane.showMessageDialog(null, "Debe ingresar valores numéricos.");  
                this.textClavePublicaEEv.setText("");
            }
            else{
                this.textClavePublicaEEv.setEnabled(false);
                this.botAceptarClavePublicaEv.setEnabled(false);
                this.panClavePrivadaEv.setEnabled(true);
                this.textClavePrivadaDEv.setEnabled(true);
                this.botAceptarClavePrivadaEv.setEnabled(true);
            }
        }        
    }//GEN-LAST:event_botAceptarClavePublicaEvMousePressed

    private void botAceptarClavePrivadaEvMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botAceptarClavePrivadaEvMousePressed
        String texto = this.textClavePrivadaDEv.getText();
        if(texto.isEmpty())
            JOptionPane.showMessageDialog(null, "Ingrese el valor E.");
        else{
            if(!this.esNumero(texto)){                            
                JOptionPane.showMessageDialog(null, "Debe ingresar valores numéricos.");  
                this.textClavePrivadaDEv.setText("");
            }   
            else{
                this.botAceptarClavePrivadaEv.setEnabled(false);
                this.textClavePrivadaDEv.setEnabled(false);
                this.botCorregirCI.setEnabled(true);
                this.botResolverCI.setEnabled(false);
            }
        }
    }//GEN-LAST:event_botAceptarClavePrivadaEvMousePressed

    private void botResolverCIMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botResolverCIMousePressed
        if(!this.modulo_generado)
            this.rsa_evaluar.GenerarPrimos();
        this.rsa_evaluar.GenerarModulo();
        this.rsa_evaluar.GenerarExponentePublico();
        this.rsa_evaluar.GenerarExponentePrivado();        
        long p = this.rsa_evaluar.GetP();
        long q = this.rsa_evaluar.GetQ();
        long n = this.rsa_evaluar.GetModulo();
        long d = this.rsa_evaluar.GetD();
        long e = this.rsa_evaluar.GetE();
        this.text_p_ev.setText(Long.toString(p));
        this.text_q_ev.setText(Long.toString(q));
        this.text_modulo_ev.setText(Long.toString(n));
        this.textClavePublicaEEv.setText(Long.toString(e));
        this.textClavePrivadaDEv.setText(Long.toString(d));
        this.panClavePublicaEv.setEnabled(true);
        this.textClavePublicaEEv.setEnabled(true);
        this.panClavePrivadaEv.setEnabled(true);
        this.textClavePrivadaDEv.setEnabled(true);
        this.botResolverCI.setEnabled(false);
        this.botReintentarCI.setEnabled(true);
        this.botContinuarCI.setEnabled(true);        
    }//GEN-LAST:event_botResolverCIMousePressed

    private void textResultadoTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textResultadoTodoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textResultadoTodoActionPerformed

    private void textResultadoBloqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textResultadoBloqueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textResultadoBloqueActionPerformed

    private void botEncriptarTodoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botEncriptarTodoMousePressed
        String texto = this.text_mens_encriptar.getText();
        if(this.ValidarTextoEncriptar(texto)){
            this.panEncriptarTodo.setEnabled(true);
            this.textResultadoTodo.setEnabled(true);
            this.botAceptarTodo.setEnabled(true);
            this.botResolverEncriptar.setEnabled(true);
            this.botEncriptarBloques.setEnabled(false);
            this.botEncriptarTodo.setEnabled(false);
            this.spinTamBloque.setEnabled(false);
            this.text_mens_encriptar.setEnabled(false);
            this.text_repnum_encriptar.setText(this.rsa_evaluar.Codificar(texto));
            this.botNuevoEncriptar.setEnabled(true);
        }
        else{
            JOptionPane.showMessageDialog(null, "Por cuestiones didácticas el texto a encriptar debe ser como máximo de " 
                    + Long.toString(this.max_lenght_encriptar) + " caracteres.");
            this.text_mens_encriptar.setText("Prueba");
        }
    }//GEN-LAST:event_botEncriptarTodoMousePressed

    private void botEncriptarBloquesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botEncriptarBloquesMousePressed
        String texto = this.text_mens_encriptar.getText();
        if(this.ValidarTextoEncriptar(texto)){
            this.panEncriptarBloques.setEnabled(true);
            this.textResultadoBloque.setEnabled(true);
            this.textBloqueActual.setEnabled(true);
            this.text_ascii_enc.setEnabled(true);
            this.botAceptarBloques.setEnabled(true);
            this.spinTamBloque.setEnabled(false);
            this.botEncriptarTodo.setEnabled(false);
            this.botEncriptarBloques.setEnabled(false);
            this.botResolverEncriptar.setEnabled(true);
            this.text_mens_encriptar.setEnabled(false);
            this.text_repnum_encriptar.setText(this.rsa_evaluar.Codificar(texto));
            int tam_bloque;
            tam_bloque = (int)this.spinTamBloque.getValue();
            String bloque;
            String caracteres;
            String repnum = this.text_repnum_encriptar.getText();
            if(this.pos_encriptar + (3*tam_bloque) < repnum.length()){
                bloque = repnum.substring(this.pos_encriptar, this.pos_encriptar + (3*tam_bloque));
                caracteres = texto.substring(this.pos_encriptar, this.pos_encriptar + tam_bloque );
            }
            else{
                bloque = repnum.substring(this.pos_encriptar);
                caracteres = texto.substring(this.pos_encriptar);
            }
            this.textBloqueActual.setText(bloque);
            this.text_ascii_enc.setText(caracteres);
            this.pos_encriptar += 3*tam_bloque;
            this.botNuevoEncriptar.setEnabled(true);
       }
       else{
            JOptionPane.showMessageDialog(null, "Por cuestiones didácticas el texto a encriptar debe ser como máximo de " 
                                            + Long.toString(this.max_lenght_encriptar) + " caracteres.");
            this.text_mens_encriptar.setText("Prueba");
       }
    }//GEN-LAST:event_botEncriptarBloquesMousePressed

    private void botReintenterEncriptarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botReintenterEncriptarMousePressed
        if(this.panEncriptarTodo.isEnabled()){
            this.textResultadoTodo.setEnabled(true);
            this.textResultadoTodo.setText("");
            this.botAceptarTodo.setEnabled(true);
            this.botCorregirEncriptar.setEnabled(false);
            this.botReintenterEncriptar.setEnabled(false);
            this.botResolverEncriptar.setEnabled(true);
            this.botContinuarEncriptar.setEnabled(false);
            this.labelCorreccionEncriptarTodo.setVisible(false);
        }
        else{
            if(this.panEncriptarBloques.isEnabled()){
                this.textResultadoBloque.setEnabled(true);
                this.textResultadoBloque.setText("");
                this.botAceptarBloques.setEnabled(true);
                this.botSiguienteBloque.setEnabled(false);
                this.botReintenterEncriptar.setEnabled(false);
                this.botResolverEncriptar.setEnabled(true);
                this.labelCorreccionEncriptarBloques.setVisible(false);
                String parcial = this.textResParcial.getText();
                this.textResParcial.setText(parcial.substring(0,parcial.length() - this.rsa_evaluar.GetLongitudBloque()));
            }
        }
    }//GEN-LAST:event_botReintenterEncriptarMousePressed

    private void botAceptarTodoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botAceptarTodoMousePressed
        String texto = this.textResultadoTodo.getText();
        if(texto.isEmpty())
            JOptionPane.showMessageDialog(null, "Ingrese el mensaje encriptado.");
        else{
            if(!this.esNumero(texto)){
                JOptionPane.showMessageDialog(null, "Debe ingresar solo valores numéricos.");
                this.textResultadoTodo.setText("");
            }
            else{
                int tam_bloque = this.rsa_evaluar.GetLongitudBloque();
                int cant_caracteres = (int)this.spinTamBloque.getValue();
                String palabra = this.text_mens_encriptar.getText();
                int pal_car = (palabra.length()/cant_caracteres);
                if(palabra.length()%cant_caracteres != 0)
                    pal_car++;
                int tam_encript = pal_car*tam_bloque;
                if(texto.length() != tam_encript){
                    JOptionPane.showMessageDialog(null, "El mensaje encriptado debe tener " + 
                            Long.toString(tam_encript) + " dígitos.");
                }
                else{
                    this.textResultadoTodo.setEnabled(false);
                    this.botCorregirEncriptar.setEnabled(true);
                    this.botResolverEncriptar.setEnabled(false);
                    this.botAceptarTodo.setEnabled(false); 
                }                  
            }                 
        }
    }//GEN-LAST:event_botAceptarTodoMousePressed

    private void botAceptarBloquesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botAceptarBloquesMousePressed
        String texto = this.textResultadoBloque.getText();
        if(texto.isEmpty())
            JOptionPane.showMessageDialog(null, "Ingrese el mensaje encriptado.");
        else{
            if(!this.esNumero(texto)){
                JOptionPane.showMessageDialog(null, "Debe ingresar solo valores numéricos.");
                this.textResultadoBloque.setText("");
            }
            else{
                int tam_bloque = this.rsa_evaluar.GetLongitudBloque();
                if(texto.length() != tam_bloque)
                    JOptionPane.showMessageDialog(null, "El bloque debe tener " + Long.toString(tam_bloque) + " dígitos.");
                else{
                    this.botAceptarBloques.setEnabled(false);
                    this.botResolverEncriptar.setEnabled(false);
                    this.botCorregirEncriptar.setEnabled(true);
                    this.textResultadoBloque.setEnabled(false);
                    this.textResParcial.setText(this.textResParcial.getText() + this.textResultadoBloque.getText());
                }                
            }
        }
    }//GEN-LAST:event_botAceptarBloquesMousePressed

    private void botCorregirEncriptarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botCorregirEncriptarMousePressed
    if(this.panEncriptarTodo.isEnabled()){
        String encriptado = this.rsa_evaluar.Encrypt(this.text_mens_encriptar.getText(),
                                                (int) this.spinTamBloque.getValue());
        if(encriptado.compareTo(this.textResultadoTodo.getText()) == 0){
            this.labelCorreccionEncriptarTodo.setText("Correcto.");
            this.labelCorreccionEncriptarTodo.setForeground(Color.green);  
            this.botContinuarEncriptar.setEnabled(true);
        }
        else{
            this.labelCorreccionEncriptarTodo.setText("<html>Incorrecto.</html>");
            this.labelCorreccionEncriptarTodo.setForeground(Color.red);                
        }
        this.botResolverEncriptar.setEnabled(false);
        this.labelCorreccionEncriptarTodo.setVisible(true);        
    }        
    if(this.panEncriptarBloques.isEnabled()){
        String encriptado = this.rsa_evaluar.Encrypt(this.text_ascii_enc.getText(),
                                                    (int) this.spinTamBloque.getValue());
        if(encriptado.compareTo(this.textResParcial.getText()) == 0){
            this.labelCorreccionEncriptarBloques.setText("Correcto.");
            this.labelCorreccionEncriptarBloques.setForeground(Color.green);
            this.botSiguienteBloque.setEnabled(true);            
        }
        else{
            this.labelCorreccionEncriptarBloques.setText("<html>Incorrecto.</html>");
            this.labelCorreccionEncriptarBloques.setForeground(Color.red);            
        }
        this.botResolverEncriptar.setEnabled(false);
        this.labelCorreccionEncriptarBloques.setVisible(true);
    }           
    this.botCorregirEncriptar.setEnabled(false);    
    this.botReintenterEncriptar.setEnabled(true);        
    }//GEN-LAST:event_botCorregirEncriptarMousePressed

    private void textResParcialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textResParcialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textResParcialActionPerformed

    private void botSiguienteBloqueMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botSiguienteBloqueMousePressed
        String repnum = this.text_repnum_encriptar.getText();
        String texto = this.text_mens_encriptar.getText();
        if(this.pos_encriptar < repnum.length()){
            String bloque;
            String caracteres;
            int tam_bloque = (int)this.spinTamBloque.getValue();
            if(this.pos_encriptar + 3*tam_bloque < repnum.length()){
                bloque = repnum.substring(this.pos_encriptar, this.pos_encriptar + (3*tam_bloque));
                caracteres = texto.substring(this.pos_encriptar/3, (this.pos_encriptar/3) + tam_bloque);
            }
            else{
                bloque = repnum.substring(this.pos_encriptar);
                caracteres = texto.substring(this.pos_encriptar/3);
            }
            this.textBloqueActual.setText(bloque);
            this.text_ascii_enc.setText(caracteres);
            this.pos_encriptar += 3*tam_bloque;            
            this.botAceptarBloques.setEnabled(true);
            this.botSiguienteBloque.setEnabled(false);
            this.botReintenterEncriptar.setEnabled(false);
            this.botResolverEncriptar.setEnabled(true);
            this.textResultadoBloque.setEnabled(true);
            this.labelCorreccionEncriptarBloques.setVisible(false);
            this.textResultadoBloque.setText("");
        }
        else{ 
            JOptionPane.showMessageDialog(null, "El mensaje ha sido encriptado.");
            this.labelCorreccionEncriptarBloques.setText("Mensaje final encriptado: " + this.textResParcial.getText());
            this.labelCorreccionEncriptarBloques.setForeground(Color.blue);
            this.labelCorreccionEncriptarBloques.setVisible(true);
            this.botAceptarBloques.setEnabled(false);
            this.botSiguienteBloque.setEnabled(false);
            this.botResolverEncriptar.setEnabled(false);
            this.botCorregirEncriptar.setEnabled(false);
            this.botReintenterEncriptar.setEnabled(false);
            this.botContinuarEncriptar.setEnabled(true);
        }
        this.textResultadoBloque.setText("");
    }//GEN-LAST:event_botSiguienteBloqueMousePressed

    private void botResolverEncriptarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botResolverEncriptarMousePressed
        if(this.panEncriptarTodo.isEnabled()){ 
            String res = this.rsa_evaluar.Encrypt(this.text_mens_encriptar.getText(),
                                                    (int) this.spinTamBloque.getValue());            
            this.labelCorreccionEncriptarTodo.setText("<html>Mensaje encriptado: " + res + "</html>" );
            this.labelCorreccionEncriptarTodo.setForeground(Color.blue);
            this.labelCorreccionEncriptarTodo.setVisible(true);
            this.botAceptarTodo.setEnabled(false);
            this.textResultadoTodo.setText(res);
            this.textResultadoTodo.setEnabled(false);
            this.botContinuarEncriptar.setEnabled(true);
        }
        if(this.panEncriptarBloques.isEnabled()){
            String res = this.rsa_evaluar.Encrypt(this.text_ascii_enc.getText(),(int)this.spinTamBloque.getValue());
            this.labelCorreccionEncriptarBloques.setText("<html>Mensaje encriptado: " + res + "</html>");
            this.labelCorreccionEncriptarBloques.setForeground(Color.blue);            
            this.labelCorreccionEncriptarBloques.setVisible(true);
            this.textResParcial.setText(this.textResParcial.getText() + res);
            this.botAceptarBloques.setEnabled(false);
            this.botSiguienteBloque.setEnabled(true);
            this.textResultadoBloque.setEnabled(false);
        }
        this.botResolverEncriptar.setEnabled(false);        
        this.botReintenterEncriptar.setEnabled(true);
        this.botCorregirEncriptar.setEnabled(false);
    }//GEN-LAST:event_botResolverEncriptarMousePressed

    private void botContinuarEncriptarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botContinuarEncriptarMousePressed
        this.RSAEvaluarPanel.setEnabledAt(this.RSAEvaluarPanel.getSelectedIndex() + 1, true);
        this.RSAEvaluarPanel.setSelectedIndex(this.RSAEvaluarPanel.getSelectedIndex() + 1);
        this.LimpiarDesencriptar();        
    }//GEN-LAST:event_botContinuarEncriptarMousePressed

    private void botDesencTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botDesencTodoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botDesencTodoActionPerformed

    private void botDesencTodoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botDesencTodoMousePressed
        this.panDesencTodo.setEnabled(true);
        this.text_resultado_des_todo.setEnabled(true);
        this.botAceptarDesTodo.setEnabled(true);
        this.botDesencTodo.setEnabled(false);
        this.botDesencBloques.setEnabled(false);
        this.botResolverDesencriptar.setEnabled(true);
    }//GEN-LAST:event_botDesencTodoMousePressed

    private void botAceptarDesTodoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botAceptarDesTodoMousePressed
        String texto = this.text_resultado_des_todo.getText();
        if(texto.isEmpty())
            JOptionPane.showMessageDialog(null, "Ingrese el mensaje desencriptado.");
        else{
            if(!this.esNumero(texto)){
                JOptionPane.showMessageDialog(null, "Debe ingresar solo valores numéricos.");
                this.text_resultado_des_todo.setText("");
            }
            else{
                int cant_bl_caract = (int)this.spinTamBloqueDes.getValue();
                String encriptado = this.text_mens_encriptado.getText();
                int cant_digitos = 3*cant_bl_caract*(encriptado.length()/this.rsa_evaluar.GetLongitudBloque());
                if(texto.length() > cant_digitos)
                    JOptionPane.showMessageDialog(null, "El resultado debe tener hasta " + Long.toString(cant_digitos) + " dígitos.");
                else{  
                    this.text_resultado_des_todo.setEnabled(false);
                    this.botAceptarDesTodo.setEnabled(false);
                    this.botCorregirDesencriptar.setEnabled(true);
                    this.botResolverDesencriptar.setEnabled(false);
                    this.botReintentarDesencriptar.setEnabled(false);
                }                
            }
        }        
    }//GEN-LAST:event_botAceptarDesTodoMousePressed

    private void botResolverDesencriptarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botResolverDesencriptarMousePressed
        if(this.panDesencTodo.isEnabled()){
            String res = this.rsa_evaluar.DesencriptarTodo(this.text_mens_encriptado.getText(),
                    (int)this.spinTamBloqueDes.getValue());
            String decod = this.rsa_evaluar.DecodificarAscii(res);
            this.labelCorreccionDesTodo.setText("Mensaje desencriptado: " + res + 
                                        " -- Mensaje decodificado: " + decod);
            this.text_resultado_des_todo.setText(res);
            this.text_res_decodificado_todo.setText(decod);
            this.labelCorreccionDesTodo.setForeground(Color.blue);
            this.labelCorreccionDesTodo.setVisible(true);
            this.text_resultado_des_todo.setEnabled(false);
            this.botAceptarDesTodo.setEnabled(false);

        }
        else if(this.panDesenBloques.isEnabled()){
            String res = this.rsa_evaluar.DesencriptarTodo(this.text_bloque_des_bloque.getText(),
                    (int)this.spinTamBloqueDes.getValue());
            String decod = this.rsa_evaluar.DecodificarAscii(res);
            this.labelCorreccionDesencBloque.setText("Mensaja desencriptado: " + res + " -- Mensaje decodificado: " +
                    decod);
            this.labelCorreccionDesencBloque.setForeground(Color.blue);
            this.labelCorreccionDesencBloque.setVisible(true);
            this.parcial_desenc_numerico = res;
            this.parcial_desenc_ascii = decod;
            this.text_resultado_des_bloque.setEnabled(false);
            this.botAceptarDesBloque.setEnabled(false);
            this.botSiguienteDesBloque.setEnabled(true);
        }
        this.botResolverDesencriptar.setEnabled(false);
        this.botReintentarDesencriptar.setEnabled(true);
        this.botCorregirDesencriptar.setEnabled(false);
    }//GEN-LAST:event_botResolverDesencriptarMousePressed

    private void botCorregirDesencriptarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botCorregirDesencriptarMousePressed
        if(this.panDesencTodo.isEnabled()){
            String desenc_ok = this.rsa_evaluar.DesencriptarTodo(this.text_mens_encriptado.getText(), 
                    (int)this.spinTamBloqueDes.getValue());
            if(desenc_ok.compareTo(this.text_resultado_des_todo.getText()) == 0){
                this.labelCorreccionDesTodo.setText("Correcto.");
                this.labelCorreccionDesTodo.setForeground(Color.green);
                this.botReintentarDesencriptar.setEnabled(false);
                this.text_res_decodificado_todo.setText(this.rsa_evaluar.DecodificarAscii(desenc_ok));
            }
            else{
                this.labelCorreccionDesTodo.setText("Incorrecto.");
                this.labelCorreccionDesTodo.setForeground(Color.red);
                this.botReintentarDesencriptar.setEnabled(true);
            }
            this.labelCorreccionDesTodo.setVisible(true);
        }
        else if(this.panDesenBloques.isEnabled()){
            String desenc_ok = this.rsa_evaluar.DesencriptarTodo(this.text_bloque_des_bloque.getText(), 
                                                                (int)this.spinTamBloqueDes.getValue());
            if(desenc_ok.compareTo(this.text_resultado_des_bloque.getText()) == 0){
                this.labelCorreccionDesencBloque.setText("Correcto.");
                this.labelCorreccionDesencBloque.setForeground(Color.green);
                this.parcial_desenc_numerico = desenc_ok;
                this.parcial_desenc_ascii = this.rsa_evaluar.DecodificarAscii(desenc_ok);
                this.botReintentarDesencriptar.setEnabled(false);
                this.botSiguienteDesBloque.setEnabled(true);
            }
            else{
                this.labelCorreccionDesencBloque.setText("Incorrecto.");
                this.labelCorreccionDesencBloque.setForeground(Color.red);
                this.botReintentarDesencriptar.setEnabled(true);
            }
            this.labelCorreccionDesencBloque.setVisible(true);
        }
        this.botCorregirDesencriptar.setEnabled(false);        
        this.botResolverDesencriptar.setEnabled(false);                
    }//GEN-LAST:event_botCorregirDesencriptarMousePressed

    private void botReintentarDesencriptarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botReintentarDesencriptarMousePressed
        this.LimpiarDesencriptar();
    }//GEN-LAST:event_botReintentarDesencriptarMousePressed

    private void botDesencBloquesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botDesencBloquesMousePressed
        String texto = this.text_mens_encriptado.getText();
        this.text_bloque_des_bloque.setText(texto.substring(this.pos_desencriptar, 
                                            this.pos_desencriptar + this.rsa_evaluar.GetLongitudBloque()));
        this.pos_desencriptar += this.rsa_evaluar.GetLongitudBloque();
        this.panDesenBloques.setEnabled(true);
        this.text_resultado_des_bloque.setEnabled(true);
        this.text_bloque_des_bloque.setEnabled(true);
        this.botAceptarDesBloque.setEnabled(true);
        this.botDesencTodo.setEnabled(false);
        this.botDesencBloques.setEnabled(false);
        this.botCorregirDesencriptar.setEnabled(false);
        this.botResolverDesencriptar.setEnabled(true);
        this.botReintentarDesencriptar.setEnabled(false);
    }//GEN-LAST:event_botDesencBloquesMousePressed

    private void botAceptarDesBloqueMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botAceptarDesBloqueMousePressed
        String texto = this.text_resultado_des_bloque.getText();
        if(texto.isEmpty())
            JOptionPane.showMessageDialog(null, "Ingrese el mensaje desencriptado.");
        else{
            if(!this.esNumero(texto)){
                JOptionPane.showMessageDialog(null, "Debe ingresar solo valores numéricos.");
                this.text_resultado_des_bloque.setText("");
            }
            else{
                String bloque = this.text_bloque_des_bloque.getText();
                int cant_bl_caract = (int)this.spinTamBloqueDes.getValue();
                int cant_digitos = 3*cant_bl_caract*(bloque.length()/this.rsa_evaluar.GetLongitudBloque());
                if(texto.length() > cant_digitos)
                    JOptionPane.showMessageDialog(null, "El resultado debe tener hasta " + Long.toString(cant_digitos) + " dígitos.");
                else{
                    this.text_resultado_des_bloque.setEnabled(false);
                    this.text_bloque_des_bloque.setEnabled(false);
                    this.botAceptarDesBloque.setEnabled(false);
                    this.botCorregirDesencriptar.setEnabled(true);
                    this.botResolverDesencriptar.setEnabled(false);
                    this.botReintentarDesencriptar.setEnabled(false);
                }                
            }
        }
    }//GEN-LAST:event_botAceptarDesBloqueMousePressed

    private void botSiguienteDesBloqueMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botSiguienteDesBloqueMousePressed
        String msg = this.text_mens_encriptado.getText();
        this.text_parcial_des_bloque.setText(this.text_parcial_des_bloque.getText() + this.parcial_desenc_numerico);
        this.text_string_resultante_Des.setText(this.text_string_resultante_Des.getText() + this.parcial_desenc_ascii);
        if(this.pos_desencriptar < msg.length()){
            this.text_bloque_des_bloque.setText(msg.substring(this.pos_desencriptar, this.pos_desencriptar + 
                    this.rsa_evaluar.GetLongitudBloque()));
            this.pos_desencriptar += this.rsa_evaluar.GetLongitudBloque();            
            this.botAceptarDesBloque.setEnabled(true);
            this.text_resultado_des_bloque.setText("");
            this.text_resultado_des_bloque.setEnabled(true);            
            this.botResolverDesencriptar.setEnabled(true);   
            this.labelCorreccionDesencBloque.setVisible(false);
        }
        else{
            JOptionPane.showMessageDialog(null, "El mensaje ha sido desencriptado.");
            this.botResolverDesencriptar.setEnabled(false);
            this.labelCorreccionDesencBloque.setText("Mensaje final desencriptado: " + this.text_parcial_des_bloque.getText()
                     + " -- Mensaje final decodificado: " + this.text_string_resultante_Des.getText());
            this.labelCorreccionDesencBloque.setForeground(Color.blue);
            this.labelCorreccionDesencBloque.setVisible(true);
        }               
        this.botReintentarDesencriptar.setEnabled(false);
        this.botCorregirDesencriptar.setEnabled(false);
        this.botSiguienteDesBloque.setEnabled(false);
    }//GEN-LAST:event_botSiguienteDesBloqueMousePressed

    private void botNuevoCIMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botNuevoCIMousePressed
        this.LimpiarCalculosIniciales();
        this.rsa_evaluar.LimpiarValores();
    }//GEN-LAST:event_botNuevoCIMousePressed

    private void botNuevoEncriptarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botNuevoEncriptarMousePressed
        this.LimpiarEncriptar();
    }//GEN-LAST:event_botNuevoEncriptarMousePressed

    private void botGuardarCalculosInicialesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botGuardarCalculosInicialesMousePressed
        try {
            JFileChooser saveFile = new JFileChooser();
            saveFile.showSaveDialog(null);
            String path=saveFile.getSelectedFile().getAbsolutePath();
            String filename=saveFile.getSelectedFile().getName();
            AlgorithmStateStorage.saveRSA(filename, path, this.generarJsonCalculosIniciales());
            JOptionPane.showMessageDialog(null, "El archivo se salvo correctamente");
        } catch (IOException ex) {
            Logger.getLogger(MD5PanelEvaluar.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Hubo un error al guardar el archivo");
        }
    }//GEN-LAST:event_botGuardarCalculosInicialesMousePressed

    private void botGuardarEncriptarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botGuardarEncriptarMousePressed
        try {
            JFileChooser saveFile = new JFileChooser();
            saveFile.showSaveDialog(null);
            String path=saveFile.getSelectedFile().getAbsolutePath();
            String filename=saveFile.getSelectedFile().getName();
            AlgorithmStateStorage.saveRSA(filename, path, this.generarJsonPasoEncriptar());
            JOptionPane.showMessageDialog(null, "El archivo se salvo correctamente");
        } catch (IOException ex) {
            Logger.getLogger(MD5PanelEvaluar.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Hubo un error al guardar el archivo");
        }
    }//GEN-LAST:event_botGuardarEncriptarMousePressed

    private void botGuardarDesencriptarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botGuardarDesencriptarMousePressed
        try {
            JFileChooser saveFile = new JFileChooser();
            saveFile.showSaveDialog(null);
            String path=saveFile.getSelectedFile().getAbsolutePath();
            String filename=saveFile.getSelectedFile().getName();
            AlgorithmStateStorage.saveRSA(filename, path, this.generarJsonPasoDesencriptar());
            JOptionPane.showMessageDialog(null, "El archivo se salvo correctamente");
        } catch (IOException ex) {
            Logger.getLogger(MD5PanelEvaluar.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Hubo un error al guardar el archivo");
        }
    }//GEN-LAST:event_botGuardarDesencriptarMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane RSAEvaluarPanel;
    private javax.swing.JButton botAceptarBloques;
    private javax.swing.JButton botAceptarClavePrivadaEv;
    private javax.swing.JButton botAceptarClavePublicaEv;
    private javax.swing.JButton botAceptarDesBloque;
    private javax.swing.JButton botAceptarDesTodo;
    private javax.swing.JButton botAceptarTodo;
    private javax.swing.JButton botCalcularModuloEv;
    private javax.swing.JButton botContinuarCI;
    private javax.swing.JButton botContinuarEncriptar;
    private javax.swing.JButton botContinuarPreguntas;
    private javax.swing.JButton botCorregirCI;
    private javax.swing.JButton botCorregirDesencriptar;
    private javax.swing.JButton botCorregirEncriptar;
    private javax.swing.JButton botCorregirPreguntas;
    private javax.swing.JButton botDesencBloques;
    private javax.swing.JButton botDesencTodo;
    private javax.swing.JButton botEncriptarBloques;
    private javax.swing.JButton botEncriptarTodo;
    private javax.swing.JButton botGuardarCalculosIniciales;
    private javax.swing.JButton botGuardarDesencriptar;
    private javax.swing.JButton botGuardarEncriptar;
    private javax.swing.JButton botNuevoCI;
    private javax.swing.JButton botNuevoEncriptar;
    private javax.swing.JButton botReintentarCI;
    private javax.swing.JButton botReintentarDesencriptar;
    private javax.swing.JButton botReintentarPreguntas;
    private javax.swing.JButton botReintenterEncriptar;
    private javax.swing.JButton botResolverCI;
    private javax.swing.JButton botResolverDesencriptar;
    private javax.swing.JButton botResolverEncriptar;
    private javax.swing.JButton botResolverPreguntas;
    private javax.swing.JButton botSiguienteBloque;
    private javax.swing.JButton botSiguienteDesBloque;
    private javax.swing.ButtonGroup grupoPreg1;
    private javax.swing.ButtonGroup grupoPreg10;
    private javax.swing.ButtonGroup grupoPreg2;
    private javax.swing.ButtonGroup grupoPreg3;
    private javax.swing.ButtonGroup grupoPreg4;
    private javax.swing.ButtonGroup grupoPreg5;
    private javax.swing.ButtonGroup grupoPreg6;
    private javax.swing.ButtonGroup grupoPreg7;
    private javax.swing.ButtonGroup grupoPreg8;
    private javax.swing.ButtonGroup grupoPreg9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton11;
    private javax.swing.JRadioButton jRadioButton12;
    private javax.swing.JRadioButton jRadioButton13;
    private javax.swing.JRadioButton jRadioButton14;
    private javax.swing.JRadioButton jRadioButton15;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JLabel labelCorreccionD;
    private javax.swing.JLabel labelCorreccionDesTodo;
    private javax.swing.JLabel labelCorreccionDesencBloque;
    private javax.swing.JLabel labelCorreccionE;
    private javax.swing.JLabel labelCorreccionEncriptarBloques;
    private javax.swing.JLabel labelCorreccionEncriptarTodo;
    private javax.swing.JLabel labelCorreccionN;
    private javax.swing.JPanel panClavePrivadaEv;
    private javax.swing.JPanel panClavePublicaEv;
    private javax.swing.JPanel panDesenBloques;
    private javax.swing.JPanel panDesencTodo;
    private javax.swing.JPanel panEncriptarBloques;
    private javax.swing.JPanel panEncriptarTodo;
    private javax.swing.JPanel panelBotonesPreguntas;
    private javax.swing.JPanel panelCalculosInicialesEv;
    private javax.swing.JPanel panelDesencriptarEv;
    private javax.swing.JPanel panelEncriptarEv;
    private javax.swing.JPanel panelPreguntas;
    private javax.swing.JRadioButton rbotCorrecto1;
    private javax.swing.JRadioButton rbotCorrecto10;
    private javax.swing.JRadioButton rbotCorrecto2;
    private javax.swing.JRadioButton rbotCorrecto3;
    private javax.swing.JRadioButton rbotCorrecto4;
    private javax.swing.JRadioButton rbotCorrecto5;
    private javax.swing.JRadioButton rbotCorrecto6;
    private javax.swing.JRadioButton rbotCorrecto7;
    private javax.swing.JRadioButton rbotCorrecto8;
    private javax.swing.JRadioButton rbotCorrecto9;
    private javax.swing.JSpinner spinTamBloque;
    private javax.swing.JSpinner spinTamBloqueDes;
    private javax.swing.JTextField textBloqueActual;
    private javax.swing.JTextField textClavePrivadaDEv;
    private javax.swing.JTextField textClavePublicaEEv;
    private javax.swing.JTextField textResParcial;
    private javax.swing.JTextField textResultadoBloque;
    private javax.swing.JTextField textResultadoTodo;
    private javax.swing.JTextField text_ascii_enc;
    private javax.swing.JTextField text_bloque_des_bloque;
    private javax.swing.JTextField text_d_desenc;
    private javax.swing.JTextField text_e_encriptar;
    private javax.swing.JTextField text_mens_encriptado;
    private javax.swing.JTextField text_mens_encriptar;
    private javax.swing.JTextField text_modulo_encriptar;
    private javax.swing.JTextField text_modulo_ev;
    private javax.swing.JTextField text_n_desenc;
    private javax.swing.JTextField text_p_ev;
    private javax.swing.JTextField text_parcial_des_bloque;
    private javax.swing.JTextField text_q_ev;
    private javax.swing.JTextField text_repnum_encriptar;
    private javax.swing.JTextField text_res_decodificado_todo;
    private javax.swing.JTextField text_resultado_des_bloque;
    private javax.swing.JTextField text_resultado_des_todo;
    private javax.swing.JTextField text_string_resultante_Des;
    // End of variables declaration//GEN-END:variables
}
