/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Sibre.Telas;

import br.com.Sibre.DAL.ModuloConexao;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author T69779848134
 */
public class TelaPedidosFeitos extends javax.swing.JInternalFrame {

    ModuloConexao conexao = new ModuloConexao();
    PreparedStatement pst;
    ResultSet rs;

    private void PesquisaMostraPeditos() {
           conexao.conector();
        try {
            conexao.executaSQL("select descricao from pedidos where nome like '%" + PesqPed.getText() + "%'");  // string para habilitar os get's
            conexao.rs.first();
//            IdPed.setText(String.valueOf(conexao.rs.getInt("idPedi")));
//            LblNome.setText(conexao.rs.getString("nomeP"));
            TxtPed.setText(conexao.rs.getString("descricao"));          // metodo para "getar" pegar o que vao para o banco
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void PesquisaPegaPedido() {
        conexao.conector();
        String sql = "select * from pedidos where nome like ?"; // string para habilitar os set's
        try {
            pst = conexao.conn.prepareStatement(sql);
            pst.setString(2, PesqPed.getText());    //metodo para setar mostrar o que vem do banco
            int mostra = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TelaPedidosFeitos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     public void pesquisaPedido() {
        conexao.conector();
        String sql = "select id_ped as Codigo, nome as Nome, descricao as Descrição, datap as Data from pedidos where nome like ?";
        try {
            pst = conexao.conn.prepareStatement(sql);
            pst.setString(1, PesqPed.getText() + "%");
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar                          
            PedTable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
     
     private void setarProduto() {
        int setar = PedTable.getSelectedRow();
        IdPed.setText(PedTable.getModel().getValueAt(setar, 0).toString());
        PesqPed.setText(PedTable.getModel().getValueAt(setar, 1).toString());
        LblNome.setText(PedTable.getModel().getValueAt(setar, 1).toString());
        TxtPed.setText(PedTable.getModel().getValueAt(setar, 2).toString());
    }
     private void Alterar(){
         String sql = "update pedidos set descricao=? where id_ped=?";
       conexao.conector();
        try {
            pst = conexao.conn.prepareStatement(sql);
            pst.setString(1, TxtPed.getText());
             pst.setString(2, IdPed.getText());

            if ((TxtPed.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preenchimento obrgatórios");
            } else {
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do usuário alterados com sucesso");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        System.out.println(conexao);
    
     }
     private void excluir(){
          conexao.conector();
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from pedidos where id_ped=?";
            try {
                pst = conexao.conn.prepareStatement(sql);
                pst.setString(1, IdPed.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso");
                    
                    //ModuloC.fecharConexao();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "erro");

            }
        }

     }
     
     private void limparCampos(){
        // IdPed.setText("");
         PesqPed.setText("");
       //  LblNome.setText("");
         TxtPed.setText("");
         PedTable.setVisible(false);
     }

    /**
     * Creates new form TelaPedidosFeitos
     */
    public TelaPedidosFeitos() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TxtPed = new javax.swing.JTextArea();
        LblNome = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        PedTable = new javax.swing.JTable();
        PesqPed = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        IdPed = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        BtnPedAlter = new javax.swing.JButton();
        BtnPFDel = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Pedidos Feitos");
        setMaximumSize(new java.awt.Dimension(1130, 614));
        setPreferredSize(new java.awt.Dimension(1130, 614));

        jPanel1.setBackground(new java.awt.Color(172, 132, 132));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        TxtPed.setColumns(20);
        TxtPed.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        TxtPed.setRows(5);
        jScrollPane2.setViewportView(TxtPed);

        LblNome.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LblNome.setText("Nome");

        PedTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        PedTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PedTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(PedTable);

        PesqPed.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PesqPed.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PesqPedKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Busca por Nome:");

        IdPed.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        IdPed.setText("Id");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Fazer pedidos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        BtnPedAlter.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BtnPedAlter.setText("Alterar");
        BtnPedAlter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPedAlterActionPerformed(evt);
            }
        });

        BtnPFDel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BtnPFDel.setText("Excluir");
        BtnPFDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPFDelActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Id");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(IdPed)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(PesqPed))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(BtnPFDel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(96, 96, 96)
                            .addComponent(BtnPedAlter, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(137, 137, 137)
                            .addComponent(jButton1))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(LblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(298, 298, 298)))
                    .addComponent(jScrollPane2))
                .addContainerGap(172, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PesqPed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(IdPed)
                    .addComponent(jLabel2))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(LblNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(BtnPedAlter)
                    .addComponent(BtnPFDel))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setBounds(0, 0, 1130, 614);
    }// </editor-fold>//GEN-END:initComponents

    private void PesqPedKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PesqPedKeyReleased
      pesquisaPedido();
      //PesqPed.setEnabled(false);
      PedTable.setVisible(true);
    }//GEN-LAST:event_PesqPedKeyReleased

    private void PedTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PedTableMouseClicked
        setarProduto();
    }//GEN-LAST:event_PedTableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PedidosdeOraçoes oracoes = new PedidosdeOraçoes();
        oracoes.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BtnPFDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPFDelActionPerformed
        excluir();
        limparCampos();
    }//GEN-LAST:event_BtnPFDelActionPerformed

    private void BtnPedAlterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPedAlterActionPerformed
       Alterar();
       limparCampos();
    }//GEN-LAST:event_BtnPedAlterActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnPFDel;
    private javax.swing.JButton BtnPedAlter;
    private javax.swing.JLabel IdPed;
    private javax.swing.JLabel LblNome;
    private javax.swing.JTable PedTable;
    private javax.swing.JTextField PesqPed;
    private javax.swing.JTextArea TxtPed;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
