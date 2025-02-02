package com.mycompany.view;

import com.mycompany.auctionclient.AuctionClient;
import com.mycompany.auctionclient.Item;
import com.mycompany.auctionclient.User;
import com.mycompany.utils.AuctionClientHelpers;
import java.awt.Image;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Cristopher
 */
public class AuctionSessionPanel extends javax.swing.JFrame {

    private static User user;
    private static SecretKey secretKey;
    private static IvParameterSpec iv;
    public static List<Item> auctionItems = new ArrayList<>();

    public enum ClientAction {
        ENTER,
        EXIT,
        BID
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
        itemImage = new javax.swing.JLabel();
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
        jLabel9 = new javax.swing.JLabel();
        itemCurrentWinner = new javax.swing.JTextField();
        openFinalResult = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));
        setMinimumSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LEILÃO VIRTUAL");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 320, 20));

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));

        itemImage.setPreferredSize(new java.awt.Dimension(250, 250));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(itemImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(itemImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 250, 250));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 480, 10));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 320, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Título");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, -1, -1));

        itemTitleTextField.setEditable(false);
        getContentPane().add(itemTitleTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 480, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Descrição");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, -1, -1));

        itemDescription.setEditable(false);
        itemDescription.setColumns(20);
        itemDescription.setRows(5);
        jScrollPane1.setViewportView(itemDescription);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 130, 480, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Preço Atual");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 230, -1, -1));

        itemCurrentPrice.setEditable(false);
        getContentPane().add(itemCurrentPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 250, 480, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Lance Atual");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 280, -1, -1));

        currentBidTextField.setEditable(false);
        getContentPane().add(currentBidTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 300, 480, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Tempo Restante");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 330, -1, -1));

        itemRemainingTime.setEditable(false);
        getContentPane().add(itemRemainingTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 350, 480, -1));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 440, 480, -1));
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
        getContentPane().add(makeBid, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 510, 230, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Meu Lance");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 450, -1, -1));
        getContentPane().add(myCurrentBid, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 470, 480, -1));

        auctionHistory.setEditable(false);
        auctionHistory.setColumns(20);
        auctionHistory.setRows(5);
        jScrollPane2.setViewportView(auctionHistory);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 320, 180));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Histórico do Leilão");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Vencedor Atual");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 380, -1, -1));

        itemCurrentWinner.setEditable(false);
        getContentPane().add(itemCurrentWinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 400, 480, -1));

        openFinalResult.setBackground(new java.awt.Color(51, 153, 255));
        openFinalResult.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        openFinalResult.setForeground(new java.awt.Color(255, 255, 255));
        openFinalResult.setText("Ver Resultado Final");
        openFinalResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFinalResultActionPerformed(evt);
            }
        });
        getContentPane().add(openFinalResult, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 510, 230, 40));
        openFinalResult.setVisible(false);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void makeBidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makeBidActionPerformed
        try {
            sendMulticastMessage(ClientAction.BID);
        } catch (Exception ex) {
            Logger.getLogger(AuctionSessionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_makeBidActionPerformed

    private void openFinalResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFinalResultActionPerformed
        try {
            openResultsPanel();
        } catch (Exception ex) {
            Logger.getLogger(AuctionSessionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_openFinalResultActionPerformed

    public void openResultsPanel() throws Exception {
        this.dispose();
        ResultsPanel resultsPanel = new ResultsPanel();
        resultsPanel.setVisible(true);
        resultsPanel.setLocationRelativeTo(null);
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
                System.out.println("[CLIENT] - Mensagem recebida do grupo: " + message);

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
            // Criação do JSON para a mensagem
            JSONObject messageJson = new JSONObject();
            messageJson.put("user", user.getName());

            // Determina o conteúdo do JSON com base na ação
            switch (action) {
                case ENTER:
                    messageJson.put("@type", "enter");
                    handleEnterNotification();
                    break;
                case EXIT:
                    messageJson.put("@type", "exit");
                    handleExitNotification();
                    break;
                case BID:
                    messageJson.put("@type", "bid");
                    String userBid = myCurrentBid.getText();
                    messageJson.put("userBid", Double.parseDouble(userBid));
                    String itemTitle = itemTitleTextField.getText();
                    messageJson.put("item", itemTitle);
                    String currentBid = currentBidTextField.getText();
                    messageJson.put("currentBid", currentBid);
                    handleBidReceived(messageJson.toString());
                    break;
            }

            // Criptografa a mensagem JSON
            String encryptedMessage = AuctionClientHelpers.encrypt(messageJson.toString(), secretKey, AuctionSessionPanel.iv);

            // Enviar a mensagem criptografada para o grupo multicast
            InetAddress group = InetAddress.getByName(AuctionClient.multicastAddress);
            DatagramPacket packet = new DatagramPacket(encryptedMessage.getBytes(), encryptedMessage.length(), group, AuctionClient.multicastPort);
            AuctionClient.multicastSocket.send(packet);

            System.out.println("[CLIENT] - Mensagem do cliente ao grupo multicast: " + encryptedMessage);

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
            System.out.println("Mensagem descriptografada: " + AuctionClientHelpers.decrypt(message, secretKey, AuctionClient.iv));
            String decryptedMessage = AuctionClientHelpers.decrypt(message, secretKey, AuctionClient.iv);
            JSONObject decryptedMessageJson = new JSONObject(decryptedMessage);

            if (decryptedMessageJson.has("@type")) {
                String type = decryptedMessageJson.getString("@type");
                if ("timer".equals(type)) {
                    itemRemainingTime.setText(decryptedMessageJson.getInt("timeLeft") + "s");
                } else if ("item".equals(type)) {
                    clearFields();
                    itemTitleTextField.setText(decryptedMessageJson.getString("name"));
                    itemDescription.setText(decryptedMessageJson.getString("description"));
                    itemCurrentPrice.setText(Double.toString(decryptedMessageJson.getDouble("price")));
                    currentBidTextField.setText(Double.toString(decryptedMessageJson.getDouble("currentBid")));

                    String imagePath = decryptedMessageJson.getString("imagePath");

                    try {
                        // Carrega o recurso corretamente
                        URL imageUrl = getClass().getResource(imagePath);
                        if (imageUrl == null) {
                            throw new IllegalArgumentException("Recurso não encontrado: " + imagePath);
                        }

                        // Cria um ImageIcon diretamente do URL
                        ImageIcon itemIcon = new ImageIcon(imageUrl);
                        itemImage.setIcon(itemIcon);
                    } catch (Exception e) {
                        System.err.println("Erro ao carregar a imagem: " + e.getMessage());
                        itemImage.setText("Imagem não encontrada");
                    }

                } else if ("success".equals(type)) {
                    String currentWinner = decryptedMessageJson.getString("currentWinner");
                    String currentItemBid = Double.toString(decryptedMessageJson.getDouble("bidValue"));
                    currentBidTextField.setText(currentItemBid);
                    itemCurrentWinner.setText(currentWinner);
                    String prevMsg = auctionHistory.getText();
                    auctionHistory.setText(prevMsg + "\n" + currentWinner + " está vencendo o item com um lance de $" + currentItemBid + "\n");
                } else if ("error".equals(type)) {
                    String prevMsg = auctionHistory.getText();
                    auctionHistory.setText(prevMsg + "\n[REJECTED] - O lance foi rejeitado.");
                } else if ("end".equals(type)) {
                    String prevMsg = auctionHistory.getText();
                    auctionHistory.setText(prevMsg + "\n" + "[END] - Leilão foi encerrado!");
                    
                    JSONArray itemsArray = decryptedMessageJson.getJSONArray("auctionItems");
                    auctionItems = new ArrayList<>();
                    for (int i = 0; i < itemsArray.length(); i++) {
                        JSONObject itemJson = itemsArray.getJSONObject(i);
                        Item item = new Item();
                        item.setName(itemJson.getString("name"));
                        item.setPrice(itemJson.getDouble("price"));
                        item.setDescription(itemJson.getString("description"));
                        item.setCurrentWinner(itemJson.getString("currentWinner"));
                        item.setCurrentBid(itemJson.getDouble("currentBid"));
                        auctionItems.add(item);
                    }
                    openFinalResult.setVisible(true);
                } else if ("winner".equals(type)) {
                    String currentItem = decryptedMessageJson.getString("item");
                    String currentWinner = decryptedMessageJson.getString("currentWinner");
                    String currentItemBid = Double.toString(decryptedMessageJson.getDouble("bidValue"));
                    String prevMsg = auctionHistory.getText();
                    auctionHistory.setText(prevMsg + "\n" + "[WINNER] O vencedor do item " + currentItem + " foi " + currentWinner + ", com um lance de $" + currentItemBid + "!");
                } else if ("noWinner".equals(type)) {
                    String currentWinner = decryptedMessageJson.getString("currentWinner");
                    String prevMsg = auctionHistory.getText();
                    auctionHistory.setText(prevMsg + "\n" + "[NO WINNER] " + currentWinner);
                }
            } else {
                System.out.println("JSON recebido não possui o campo '@type'.");
            }
        }
    }

    // Ação para processar o lance recebido
    public void handleBidReceived(String message) {
        JSONObject jsonMessage = new JSONObject(message);
        String msg = user.getName() + " deu um lance de " + "$ " + Double.toString(jsonMessage.getDouble("userBid")) + " em " + jsonMessage.getString("item");
        String prevMsg = auctionHistory.getText();
        auctionHistory.setText(prevMsg + "\n" + msg + "\n");
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

    public void clearFields() {
        itemTitleTextField.setText("");
        itemDescription.setText("");
        itemCurrentPrice.setText("");
        currentBidTextField.setText("");
        itemRemainingTime.setText("");
        itemCurrentWinner.setText("");
        myCurrentBid.setText("");
        itemImage.setIcon(null);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea auctionHistory;
    private javax.swing.JTextField currentBidTextField;
    private javax.swing.JTextField itemCurrentPrice;
    private javax.swing.JTextField itemCurrentWinner;
    private javax.swing.JTextArea itemDescription;
    private javax.swing.JLabel itemImage;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JButton makeBid;
    private javax.swing.JTextField myCurrentBid;
    private javax.swing.JButton openFinalResult;
    // End of variables declaration//GEN-END:variables
}
