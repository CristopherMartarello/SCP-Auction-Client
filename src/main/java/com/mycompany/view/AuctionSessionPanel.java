package com.mycompany.view;

import com.mycompany.auctionclient.AuctionClient;
import com.mycompany.auctionclient.User;
import com.mycompany.utils.AuctionClientHelpers;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.swing.JOptionPane;

/**
 *
 * @author Cristopher
 */
public class AuctionSessionPanel extends javax.swing.JFrame {

    private static User user;
    private static SecretKey secretKey;
    private static IvParameterSpec iv;
    
    public enum ClientAction {
        ENTER, // Quando o usuário entra no leilão
        EXIT, // Quando o usuário sai do leilão
        BID    // Quando o usuário dá um lance
    }

    public AuctionSessionPanel(SecretKey secretKey, User user) {
        this.user = user;
        this.secretKey = secretKey;
        this.iv = AuctionClientHelpers.generateIV(secretKey);
        // Conectar ao grupo multicast e iniciar a escuta de mensagens
        AuctionClient.multicastSocket = connectsToMulticastGroup();

        // Iniciar thread para receber mensagens do multicast
        new Thread(() -> {
            try {
                receiveMulticastMessages(AuctionClient.multicastSocket);
            } catch (Exception ex) {
                Logger.getLogger(AuctionSessionPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();

        // Inicializa os componentes da interface
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        itemTitleTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        itemDescription = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        itemCurrentPrice = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        currentBidTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        itemRemainingTime = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        makeBid = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        myCurrentBid = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        auctionHistory = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LEILÃO VIRTUAL");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 170, 20));

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 250, 250));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, 400, 10));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 320, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Título");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, -1, -1));
        getContentPane().add(itemTitleTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, 400, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Descrição");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, -1, -1));

        itemDescription.setColumns(20);
        itemDescription.setRows(5);
        jScrollPane1.setViewportView(itemDescription);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 400, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Preço Atual");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 240, -1, -1));
        getContentPane().add(itemCurrentPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 260, 400, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Lance Atual");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 290, -1, -1));
        getContentPane().add(currentBidTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 310, 400, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tempo Restante");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 340, -1, -1));
        getContentPane().add(itemRemainingTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 360, 400, -1));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 390, 400, -1));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 320, -1));

        makeBid.setBackground(new java.awt.Color(51, 153, 0));
        makeBid.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        makeBid.setForeground(new java.awt.Color(255, 255, 255));
        makeBid.setText("Dar Lance!");
        makeBid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makeBidActionPerformed(evt);
            }
        });
        getContentPane().add(makeBid, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 450, 110, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Meu Lance");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 440, -1, -1));
        getContentPane().add(myCurrentBid, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 460, 280, -1));

        auctionHistory.setColumns(20);
        auctionHistory.setRows(5);
        auctionHistory.setText("Ainda não foi realizado nenhum lance para este item.");
        jScrollPane2.setViewportView(auctionHistory);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 320, 180));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Histórico do Leilão");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void makeBidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makeBidActionPerformed
        try {
            //        sendMulticastMessage("TESTE: Esse é um teste de mensagem para o multicast");
            sendMulticastMessage(ClientAction.ENTER);
        } catch (Exception ex) {
            Logger.getLogger(AuctionSessionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_makeBidActionPerformed

    // Função para enviar um lance
    private void sendBid() {
        try {
            String bidValue = myCurrentBid.getText();
            String itemTitleText = itemTitleTextField.getText();

            if (bidValue.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, determine um valor antes de enviar o lance.");
                return;
            }

            String message = String.format("Lance: %s | Item: %s", bidValue, itemTitleText);

            // Envio de mensagens ao grupo multicast
            InetAddress group = InetAddress.getByName(AuctionClient.multicastAddress);
            DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), group, AuctionClient.multicastPort);
            AuctionClient.multicastSocket.send(packet);

            System.out.println("Mensagem enviada: " + message);

            // Atualiza o histórico localmente
            String currentText = auctionHistory.getText();
            auctionHistory.setText(currentText + "\nVocê: " + message);
        } catch (IOException ex) {
            Logger.getLogger(AuctionSessionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Função para se conectar ao grupo multicast
    public static MulticastSocket connectsToMulticastGroup() {
        try {
            InetAddress multicastGroup = InetAddress.getByName(AuctionClient.multicastAddress);
            MulticastSocket multicastSocket = new MulticastSocket(AuctionClient.multicastPort);
            multicastSocket.joinGroup(multicastGroup);
            System.out.println("Conectado ao grupo multicast: " + AuctionClient.multicastAddress + AuctionClient.multicastPort);
            return multicastSocket;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    // Função para receber mensagens do grupo multicast no cliente
    public void receiveMulticastMessages(MulticastSocket multicastSocket) throws Exception {
        try {
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                multicastSocket.receive(packet);  // Recebe a mensagem

                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Mensagem recebida [CLIENT]: " + message);

                // Chama a função para tratar a mensagem recebida
                handleReceivedMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Função para enviar uma mensagem ao grupo
    public void sendMulticastMessage(ClientAction action) throws Exception {
        try {
            String message = "";

            // Determina a mensagem com base na ação
            switch (action) {
                case ENTER:
                    message = "ENTROU: " + user.getName();
                    handleEnterNotification();
                    break;
                case EXIT:
                    message = "SAIU: " + user.getName();
                    handleExitNotification();
                    break;
                case BID:
                    String bidValue = currentBidTextField.getText();
                    String itemTitle = myCurrentBid.getText();
                    message = "LANCE de " + user.getName() + ": " + bidValue + "; " + itemTitle;
                    handleBidReceived(message);
                    break;
            }

            String encryptedMessage = AuctionClientHelpers.encrypt(message, secretKey, AuctionSessionPanel.iv);

            // Enviar a mensagem criptografada para o grupo multicast
            InetAddress group = InetAddress.getByName(AuctionClient.multicastAddress);
            DatagramPacket packet = new DatagramPacket(encryptedMessage.getBytes(), encryptedMessage.length(), group, AuctionClient.multicastPort);
            AuctionClient.multicastSocket.send(packet);
            System.out.println("Mensagem enviada [CLIENT]: " + message);

        } catch (IOException ex) {
            Logger.getLogger(AuctionSessionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Função para tratar as ações de mensagem recebida no cliente
    public void handleReceivedMessage(String message) throws Exception {
        if (message.contains("ENTROU")) {
            handleEnterNotification();
        } else if (message.contains("LANCE")) {
            handleBidReceived(message);
        } else if (message.contains("SAIU")) {
            handleExitNotification();
        } else { // se não possui nenhum, siginifica que tem que descriptografar
            System.out.println("Mensagem: " + AuctionClientHelpers.decrypt(message, secretKey, AuctionClient.iv));
        }
    }

    // Ação para processar o lance recebido
    public void handleBidReceived(String message) {
        String msg = user.getName() + " deu um lance de " + message;
        auctionHistory.setText(msg + "\n");
        // Aqui o cliente pode atualizar seu histórico de lances
    }

    // Ação para processar a saída de um participante
    public void handleExitNotification() {
        String message = "SAIU: " + user.getName() + " saiu do leilão.";
        auctionHistory.setText(message + "\n");
    }

    // Ação para processar entrada de um participante
    public void handleEnterNotification() {
        String message = "ENTROU: " + user.getName() + " entrou no leilão.";
        auctionHistory.setText(message + "\n");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea auctionHistory;
    private javax.swing.JTextField currentBidTextField;
    private javax.swing.JTextField itemCurrentPrice;
    private javax.swing.JTextArea itemDescription;
    private javax.swing.JTextField itemRemainingTime;
    private javax.swing.JTextField itemTitleTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JButton makeBid;
    private javax.swing.JTextField myCurrentBid;
    // End of variables declaration//GEN-END:variables
}
