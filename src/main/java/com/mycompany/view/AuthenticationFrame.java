package com.mycompany.view;

import com.mycompany.auctionclient.AuctionClient;
import com.mycompany.auctionclient.User;
import com.mycompany.utils.AuctionClientHelpers;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author Cristopher
 */
public class AuthenticationFrame extends javax.swing.JFrame {

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private User user;

    public AuthenticationFrame() {
        initComponents();
    }

    private String sendEnterRequest(String plainText, String signatureBase64) {
        String cpf = cpf_field.getText().trim();
        String name = name_field.getText().trim();

        if (cpf.isEmpty() || name.isEmpty()) {
            statusLabel.setText("Por favor, preencha todos os campos...");
            statusLabel.setVisible(true);
        } else {
            try {
                // Conectar ao servidor TCP
                socket = new Socket("127.0.0.1", 12345); // IP e porta do servidor TCP
                out = new DataOutputStream(socket.getOutputStream()); // Saída de dados
                in = new DataInputStream(socket.getInputStream()); // Entrada de dados

                // Criando um objeto JSON
                JSONObject jsonRequest = new JSONObject();
                jsonRequest.put("CPF", cpf);
                jsonRequest.put("name", name);
                jsonRequest.put("text", plainText);
                jsonRequest.put("signature", signatureBase64);
                
                // criar o objeto usuário que irá participar do leilão
                user = new User(name, cpf, signatureBase64, plainText);
                
                // Enviar o JSON contendo as informações de autenticação para o servidor
                out.writeUTF(jsonRequest.toString());
                statusLabel.setText("CPF enviado para autenticação...");

                // Receber a resposta do servidor
                String serverResponse = in.readUTF(); // Alinha com writeUTF do servidor
                System.out.println("Resposta do servidor: " + serverResponse);

                return serverResponse;
            } catch (IOException ex) {
                System.out.println("Erro ao conectar ao servidor: " + ex.getMessage());
                statusLabel.setText("Erro ao conectar ao servidor: " + ex.getMessage());
            } catch (Exception ex) {
                System.out.println("Erro ao processar autenticação: " + ex.getMessage());
                statusLabel.setText("Erro ao processar autenticação: " + ex.getMessage());
            }
        }
        return "";
    }

    public boolean joinRequest() {
        try {
            String plaintext = "Quero entrar no leilão";
            byte[] signatureBytes = AuctionClientHelpers.signMessage(plaintext, AuctionClient.privateKey);
            String signatureBase64 = Base64.getEncoder().encodeToString(signatureBytes);
            String responseStr = sendEnterRequest(plaintext, signatureBase64);
            if (!responseStr.equals("")) {
                System.out.println(responseStr);
                openServerInformations(user, responseStr);
                // posso fazer uma tela que já puxa os dados descriptografados e com um botão para entrar no leilão
            }
//            String responseJsonSTR = ClientCommunicationUtils.sendRequestToServer(CPF, plaintext, signatureBase64, serverIP, ServerPort);
//            System.out.println(responseJsonSTR);
//            //data extraction
//            JSONObject responseJson = new JSONObject(responseJsonSTR);
//            
//            multicastAddress = responseJson.getString("adress");
//            multicastPort = responseJson.getInt("port");
//            String encodedSecretKey = responseJson.getString("secretKey");////String in base64, encoded with client public key.
//            String text = responseJson.getString("text");
//            String serverSignature = responseJson.getString("signature");
//            
//            System.out.println("multicastAdress : "+ multicastAddress);
//            System.out.println("multicastPort : "+ multicastPort);
//            System.out.println("encodedSecretKey : "+ encodedSecretKey);
//            System.out.println("text : "+ text);
//            System.out.println("serverSignature : "+ serverSignature);
//            //authenticate server
//            signatureBytes = Base64.getDecoder().decode(serverSignature);
//            System.out.println(ClientCryptoUtils.verifySignature(text, signatureBytes, SERVER_PUBLIC_KEY_BYTES));
//            //decodes the secret key
//            String secretKeyBase64 = ClientCryptoUtils.decryptWithRSA(encodedSecretKey, privateKey);
//            secretKey = ClientCryptoUtils.convertBase64ToSecretKey(secretKeyBase64);
//            iv = ClientCryptoUtils.generateIvFromAESKey(secretKey);

        } catch (Exception ex) {
            Logger.getLogger(AuthenticationFrame.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public void openServerInformations(User user, String response) throws Exception {
        this.dispose();
        ServerInformations serverInformations = new ServerInformations(user, response);
        serverInformations.setVisible(true);
        serverInformations.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sendCPF = new javax.swing.JButton();
        cpf_field = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        name_field = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sendCPF.setText("Enviar");
        sendCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendCPFActionPerformed(evt);
            }
        });
        getContentPane().add(sendCPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 90, -1));
        getContentPane().add(cpf_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 210, -1));

        jLabel1.setText("CPF");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, -1, -1));

        statusLabel.setVisible(false);
        getContentPane().add(statusLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 270, 150, 20));

        jLabel2.setText("Nome");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, -1, -1));
        getContentPane().add(name_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 210, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendCPFActionPerformed
        System.out.println("Clicou botão enviar cpf");
        joinRequest();
    }//GEN-LAST:event_sendCPFActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cpf_field;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField name_field;
    private javax.swing.JButton sendCPF;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables
}
