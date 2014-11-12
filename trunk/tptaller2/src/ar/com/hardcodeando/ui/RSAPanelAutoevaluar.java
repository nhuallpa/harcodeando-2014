/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.hardcodeando.ui;

import ar.com.hardcodeando.algorithm.RSA;
import java.awt.Color;
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
    
    public RSAPanelAutoevaluar() {
        initComponents();
        this.rsa_evaluar = new RSA();
        this.RSAEvaluarPanel.setEnabledAt(1, false);
        this.RSAEvaluarPanel.setEnabledAt(2, false);
        this.RSAEvaluarPanel.setEnabledAt(3, false);
        this.labelCorreccionN.setVisible(false);
        this.labelCorreccionD.setVisible(false);
        this.labelCorreccionE.setVisible(false);
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
        try {
            Integer.parseInt(cadena);
            return true;
        }catch (NumberFormatException nfe){
            return false;
        }
    }
    
    private void CorregirModulo(){
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
            }                         
        }        
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
    
    private void LimpiarCalculosIniciales(){
        this.text_p_ev.setText("");
        this.text_q_ev.setText("");
        this.text_modulo_ev.setText("");
        this.textClavePublicaEEv.setText("");
        this.textClavePrivadaDEv.setText("");
        this.panClavePrivadaEv.setEnabled(false);
        this.textClavePrivadaDEv.setText("");
        this.textClavePrivadaDEv.setEnabled(false);
        this.botAceptarClavePrivEv.setEnabled(false);
        this.panClavePublicaEv.setEnabled(false);
        this.textClavePublicaEEv.setText("");
        this.textClavePublicaEEv.setEnabled(false);
        this.botAceptarClavePublEv.setEnabled(false);
        this.botCalcularModuloEv.setEnabled(true);
        this.botContinuarCI.setEnabled(false);
        this.botReintentarCI.setEnabled(false);
        this.botResolverCI.setEnabled(true);
        this.botCorregirCI.setEnabled(false);        
        this.labelCorreccionN.setVisible(false);
        this.labelCorreccionD.setVisible(false);
        this.labelCorreccionE.setVisible(false);
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
        labelCorreccionN = new javax.swing.JLabel();
        panClavePrivadaEv = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        textClavePrivadaDEv = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        labelCorreccionD = new javax.swing.JLabel();
        botAceptarClavePrivEv = new javax.swing.JButton();
        panClavePublicaEv = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        textClavePublicaEEv = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        labelCorreccionE = new javax.swing.JLabel();
        botAceptarClavePublEv = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        botReintentarCI = new javax.swing.JButton();
        botCorregirCI = new javax.swing.JButton();
        botResolverCI = new javax.swing.JButton();
        botContinuarCI = new javax.swing.JButton();
        panelEncriptarEv = new javax.swing.JPanel();
        panelDesencriptarEv = new javax.swing.JPanel();

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
                .addContainerGap(73, Short.MAX_VALUE))
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

        botCalcularModuloEv.setText("Calcular");
        botCalcularModuloEv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botCalcularModuloEvMousePressed(evt);
            }
        });

        labelCorreccionN.setText("Corrección:");

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
                .addGap(109, 109, 109)
                .addComponent(labelCorreccionN, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(text_p_ev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(text_q_ev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(text_modulo_ev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17))
                    .addComponent(botCalcularModuloEv))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelCorreccionN)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        panClavePrivadaEv.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Definir clave privada", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(51, 0, 51)));
        panClavePrivadaEv.setEnabled(false);

        jLabel18.setText("<html>En base al <strong>módulo</strong> calculado, calcule el valor de la <br>componente de la clave privada d.<br>Recuerde que debe pertenecer al intervalo<br><strong>[max(p,q)+1,n-1]</strong> y que <strong>MCD(d, (p-1)&times;(q-1)) = 1</strong></html>");

        textClavePrivadaDEv.setEnabled(false);

        jLabel19.setText("d:");

        labelCorreccionD.setText("Corrección:");

        botAceptarClavePrivEv.setText("Aceptar");
        botAceptarClavePrivEv.setEnabled(false);
        botAceptarClavePrivEv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botAceptarClavePrivEvMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panClavePrivadaEvLayout = new javax.swing.GroupLayout(panClavePrivadaEv);
        panClavePrivadaEv.setLayout(panClavePrivadaEvLayout);
        panClavePrivadaEvLayout.setHorizontalGroup(
            panClavePrivadaEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panClavePrivadaEvLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panClavePrivadaEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCorreccionD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panClavePrivadaEvLayout.createSequentialGroup()
                        .addGroup(panClavePrivadaEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panClavePrivadaEvLayout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textClavePrivadaDEv, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botAceptarClavePrivEv)))
                        .addGap(0, 34, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panClavePrivadaEvLayout.setVerticalGroup(
            panClavePrivadaEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panClavePrivadaEvLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(panClavePrivadaEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textClavePrivadaDEv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(botAceptarClavePrivEv))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelCorreccionD)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        panClavePublicaEv.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Definir clave pública", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(51, 0, 51)));
        panClavePublicaEv.setEnabled(false);

        jLabel20.setText("<html>Teniendo en cuenta el valor <strong>d</strong> calculado, calcule el valor de la <br>componente de la clave pública e.<br>Recuerde que <strong>e</strong> es el multiplicador modular inverso de <br><strong>d mod (p-1)&times;(q-1)</strong></html>");

        textClavePublicaEEv.setEnabled(false);

        jLabel21.setText("e:");

        labelCorreccionE.setText("Corrección:");

        botAceptarClavePublEv.setText("Aceptar");
        botAceptarClavePublEv.setEnabled(false);
        botAceptarClavePublEv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botAceptarClavePublEvMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panClavePublicaEvLayout = new javax.swing.GroupLayout(panClavePublicaEv);
        panClavePublicaEv.setLayout(panClavePublicaEvLayout);
        panClavePublicaEvLayout.setHorizontalGroup(
            panClavePublicaEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panClavePublicaEvLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panClavePublicaEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panClavePublicaEvLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textClavePublicaEEv, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botAceptarClavePublEv))
                    .addGroup(panClavePublicaEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(labelCorreccionE, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panClavePublicaEvLayout.setVerticalGroup(
            panClavePublicaEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panClavePublicaEvLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panClavePublicaEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textClavePublicaEEv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(botAceptarClavePublEv))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelCorreccionE)
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

        botContinuarCI.setText("Continuar");
        botContinuarCI.setEnabled(false);
        botContinuarCI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botContinuarCIMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(199, 199, 199)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botContinuarCI, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(botCorregirCI, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botReintentarCI)))
                .addGap(18, 18, 18)
                .addComponent(botResolverCI, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(botContinuarCI)
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
                    .addGroup(panelCalculosInicialesEvLayout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addGroup(panelCalculosInicialesEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelCalculosInicialesEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(panelCalculosInicialesEvLayout.createSequentialGroup()
                                    .addComponent(panClavePrivadaEv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(panClavePublicaEv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(137, Short.MAX_VALUE))
        );
        panelCalculosInicialesEvLayout.setVerticalGroup(
            panelCalculosInicialesEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCalculosInicialesEvLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelCalculosInicialesEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panClavePublicaEv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panClavePrivadaEv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(97, 97, 97))
        );

        RSAEvaluarPanel.addTab("Cálculos Iniciales", panelCalculosInicialesEv);

        javax.swing.GroupLayout panelEncriptarEvLayout = new javax.swing.GroupLayout(panelEncriptarEv);
        panelEncriptarEv.setLayout(panelEncriptarEvLayout);
        panelEncriptarEvLayout.setHorizontalGroup(
            panelEncriptarEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 928, Short.MAX_VALUE)
        );
        panelEncriptarEvLayout.setVerticalGroup(
            panelEncriptarEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 602, Short.MAX_VALUE)
        );

        RSAEvaluarPanel.addTab("Encriptar", panelEncriptarEv);

        javax.swing.GroupLayout panelDesencriptarEvLayout = new javax.swing.GroupLayout(panelDesencriptarEv);
        panelDesencriptarEv.setLayout(panelDesencriptarEvLayout);
        panelDesencriptarEvLayout.setHorizontalGroup(
            panelDesencriptarEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 928, Short.MAX_VALUE)
        );
        panelDesencriptarEvLayout.setVerticalGroup(
            panelDesencriptarEvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 602, Short.MAX_VALUE)
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
                long pe = Long.parseLong(p);
                long qu = Long.parseLong(q);
                long modulo = pe * qu;
                this.text_modulo_ev.setText(Long.toString(modulo));                
                this.panClavePrivadaEv.setEnabled(true);
                this.textClavePrivadaDEv.setEnabled(true);
                this.botAceptarClavePrivEv.setEnabled(true);
                this.botCalcularModuloEv.setEnabled(false);
            }
        }         
    }//GEN-LAST:event_botCalcularModuloEvMousePressed

    private void botContinuarCIMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botContinuarCIMousePressed
        this.RSAEvaluarPanel.setEnabledAt(this.RSAEvaluarPanel.getSelectedIndex() + 1, true);
        this.RSAEvaluarPanel.setSelectedIndex(this.RSAEvaluarPanel.getSelectedIndex() + 1);
    }//GEN-LAST:event_botContinuarCIMousePressed

    private void botCorregirCIMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botCorregirCIMousePressed
        this.CorregirModulo();
        this.CorregirD();
        this.CorregirE();
        this.labelCorreccionN.setVisible(true);
        this.labelCorreccionD.setVisible(true);
        this.labelCorreccionE.setVisible(true);
        this.botReintentarCI.setEnabled(true);
        this.botCorregirCI.setEnabled(false);
        this.botResolverCI.setEnabled(false);
    }//GEN-LAST:event_botCorregirCIMousePressed

    private void botReintentarCIMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botReintentarCIMousePressed
        this.LimpiarCalculosIniciales();
        this.rsa_evaluar.LimpiarValores();
    }//GEN-LAST:event_botReintentarCIMousePressed

    private void botAceptarClavePrivEvMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botAceptarClavePrivEvMousePressed
        String texto = this.textClavePrivadaDEv.getText();        
        if(texto.isEmpty())
            JOptionPane.showMessageDialog(null, "Ingrese el valor D.");
        else{
            if(!this.esNumero(texto)){                            
                JOptionPane.showMessageDialog(null, "Debe ingresar valores numéricos.");  
                this.textClavePrivadaDEv.setText("");
            }
            else{
                this.textClavePrivadaDEv.setEnabled(false);
                this.botAceptarClavePrivEv.setEnabled(false);
                this.panClavePublicaEv.setEnabled(true);
                this.textClavePublicaEEv.setEnabled(true);
                this.botAceptarClavePublEv.setEnabled(true);
            }
        }        
    }//GEN-LAST:event_botAceptarClavePrivEvMousePressed

    private void botAceptarClavePublEvMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botAceptarClavePublEvMousePressed
        String texto = this.textClavePublicaEEv.getText();
        if(texto.isEmpty())
            JOptionPane.showMessageDialog(null, "Ingrese el valor E.");
        else{
            if(!this.esNumero(texto)){                            
                JOptionPane.showMessageDialog(null, "Debe ingresar valores numéricos.");  
                this.textClavePublicaEEv.setText("");
            }   
            else{
                this.botAceptarClavePublEv.setEnabled(false);
                this.textClavePublicaEEv.setEnabled(false);
                this.botCorregirCI.setEnabled(true);
            }
        }
    }//GEN-LAST:event_botAceptarClavePublEvMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane RSAEvaluarPanel;
    private javax.swing.JButton botAceptarClavePrivEv;
    private javax.swing.JButton botAceptarClavePublEv;
    private javax.swing.JButton botCalcularModuloEv;
    private javax.swing.JButton botContinuarCI;
    private javax.swing.JButton botContinuarPreguntas;
    private javax.swing.JButton botCorregirCI;
    private javax.swing.JButton botCorregirPreguntas;
    private javax.swing.JButton botReintentarCI;
    private javax.swing.JButton botReintentarPreguntas;
    private javax.swing.JButton botResolverCI;
    private javax.swing.JButton botResolverPreguntas;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
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
    private javax.swing.JLabel labelCorreccionE;
    private javax.swing.JLabel labelCorreccionN;
    private javax.swing.JPanel panClavePrivadaEv;
    private javax.swing.JPanel panClavePublicaEv;
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
    private javax.swing.JTextField textClavePrivadaDEv;
    private javax.swing.JTextField textClavePublicaEEv;
    private javax.swing.JTextField text_modulo_ev;
    private javax.swing.JTextField text_p_ev;
    private javax.swing.JTextField text_q_ev;
    // End of variables declaration//GEN-END:variables
}
