
package com.mycompany.view;

import com.mycompany.auctionclient.AuctionClient;
import com.mycompany.auctionclient.User;
import com.mycompany.utils.AuctionClientHelpers;
import java.awt.Color;
import java.util.Base64;
import javax.crypto.SecretKey;
import javax.swing.JTextField;
import org.json.JSONObject;

/**
 *
 * @author Cristopher
 */
public class ServerInformations extends javax.swing.JFrame {

    private final User user;
    private SecretKey secretKey = null;
    
    public ServerInformations(User user, String response) throws Exception {
        this.user = user;
        
        // Processa a resposta JSON
        JSONObject jsonResponse = new JSONObject(response);
        
        // Extrai a assinatura do servidor (Base64)
        String signatureBase64 = jsonResponse.getString("signature");
        byte[] signatureBytes = Base64.getDecoder().decode(signatureBase64);  // Decodificando a assinatura Base64
        
        // Extrai a chave simétrica gera o IV a partir dela
        String secretKeyBase64 = AuctionClientHelpers.decryptWithPrivateKey(jsonResponse.getString("secretKey"), AuctionClient.privateKey);
        secretKey = AuctionClientHelpers.decodeSymmetricKey(secretKeyBase64);
        AuctionClient.iv = AuctionClientHelpers.generateIV(secretKey);

        // Decriptando os dados recebidos do JSON criptografado
        AuctionClient.multicastAddress = AuctionClientHelpers.decryptWithPrivateKey(jsonResponse.getString("address"), AuctionClient.privateKey);
        AuctionClient.multicastPort = Integer.parseInt(AuctionClientHelpers.decryptWithPrivateKey(jsonResponse.getString("port"), AuctionClient.privateKey));
        String text = jsonResponse.getString("text");
        
        // Verifica se a assinatura é válida (se a mensagem corresponde à assinatura)
        Boolean canWeTrustSignature = AuctionClientHelpers.verifySignature(text, signatureBytes, AuctionClient.ServerPublicKey);
        
        // Inicializa os componentes da interface gráfica
        initComponents();
        
        // Preenche os campos com os dados do JSON
        addressTextField.setText(AuctionClient.multicastAddress);
        portTextField.setText(Integer.toString(AuctionClient.multicastPort));
        signatureTextField.setText(canWeTrustSignature ? "Assinatura confiável" : "Assinatura NÃO confiável");
        responseTextField.setText(text);
        
        // Exibe se a assinatura é confiável ou não
        if (canWeTrustSignature) {
            setFieldColor(addressTextField, Color.GREEN); 
            setFieldColor(portTextField, Color.GREEN);
            setFieldColor(signatureTextField, Color.GREEN);
            setFieldColor(responseTextField, Color.GREEN);
        } else {
            setFieldColor(addressTextField, Color.RED);
            setFieldColor(portTextField, Color.RED);
            setFieldColor(signatureTextField, Color.RED);
            setFieldColor(responseTextField, Color.RED);
            enterAuctionButton.setEnabled(false);
        }
    }
    
    private void setFieldColor(JTextField field, Color color) {
        field.setBackground(color);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        addressTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        portTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        signatureTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        responseTextField = new javax.swing.JTextField();
        enterAuctionButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Informações do servidor");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, -1, -1));

        jLabel2.setText("Endereço Multicast");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        addressTextField.setEditable(false);
        addressTextField.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        getContentPane().add(addressTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 720, -1));

        jLabel3.setText("Porta Multicast");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        portTextField.setEditable(false);
        portTextField.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        getContentPane().add(portTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 720, -1));

        jLabel4.setText("Assinatura Recebida do Servidor");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));

        signatureTextField.setEditable(false);
        signatureTextField.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        getContentPane().add(signatureTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 720, -1));

        jLabel5.setText("Texto Recebido do Servidor");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));

        responseTextField.setEditable(false);
        responseTextField.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        getContentPane().add(responseTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 720, -1));

        enterAuctionButton.setText("Entrar no Leilão");
        enterAuctionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterAuctionButtonActionPerformed(evt);
            }
        });
        getContentPane().add(enterAuctionButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 490, 150, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enterAuctionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterAuctionButtonActionPerformed
        this.dispose();
        AuctionSessionPanel auctionPanel = new AuctionSessionPanel(secretKey, user);
        auctionPanel.setVisible(true);
        auctionPanel.setLocationRelativeTo(null);
    }//GEN-LAST:event_enterAuctionButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressTextField;
    private javax.swing.JButton enterAuctionButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField portTextField;
    private javax.swing.JTextField responseTextField;
    private javax.swing.JTextField signatureTextField;
    // End of variables declaration//GEN-END:variables
}
