/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.view;

import com.mycompany.auctionclient.Item;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cristopher
 */
public class ResultsPanel extends javax.swing.JFrame {

    private static List<Item> auctionItems = AuctionSessionPanel.auctionItems;

    public ResultsPanel() {
        initComponents();
        fillTable();
    }

    public void fillTable() {
        // Define os nomes das colunas
        String[] columnNames = {"Nome", "Preço", "Maior Lance", "Vencedor"};

        // Cria o modelo da tabela
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Verifica se auctionItems está vazio
        System.out.println("Tamanho de auctionItems: " + auctionItems.size());

        // Itera sobre os itens do leilão e adiciona ao modelo
        for (Item item : auctionItems) {
            System.out.println("Item: " + item.getName() + ", Preço: " + item.getPrice() + ", Maior Lance: " + item.getCurrentBid() + ", Vencedor: " + item.getCurrentWinner());

            Object[] rowData = {
                item.getName(), // Nome
                String.format("%.2f", item.getPrice()), // Preço formatado
                String.format("%.2f", item.getCurrentBid()), // Maior Lance formatado
                item.getCurrentWinner() // Vencedor
            };

            tableModel.addRow(rowData);
        }

        // Define o modelo na tabela
        auctionItemsTable.setModel(tableModel);

        // Ajusta o tamanho automático das colunas
        auctionItemsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        auctionItemsTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("RESULTADOS DO LEILÃO");

        auctionItemsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nome", "Preço", "Maior Lance", "Vencedor"
            }
        ));
        jScrollPane1.setViewportView(auctionItemsTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable auctionItemsTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
