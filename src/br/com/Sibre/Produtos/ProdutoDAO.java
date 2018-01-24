/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Sibre.Produtos;

import java.sql.*;
import br.com.Sibre.DAL.ModuloConexao;
import javax.swing.JOptionPane;
import br.com.Sibre.Produtos.ProdutoDTO;
import br.com.Sibre.Cantina.vendaDTO;

/**
 *
 * @author ernil
 */
public class ProdutoDAO {

    ModuloConexao conexao = new ModuloConexao();

    public void salvarProduto(ProdutoDTO dto) {

        String sql = "insert into produtos (descricao_p, qtd, preco )value (?,?,?)";
        conexao.conector();
        try {
            PreparedStatement pst = conexao.conn.prepareStatement(sql);
            pst.setString(1, dto.getDescricao());
            pst.setString(2, dto.getQtd());
            pst.setString(3, dto.getPreco());

            if (dto.getDescricao().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrgatórios");
            } else {
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do usuário alterados com sucesso");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void ExcluirProduto(ProdutoDTO dto) {
        conexao.conector();
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from produtos where id_pro = ?";
            try {
                PreparedStatement pst = conexao.conn.prepareStatement(sql);
                pst.setInt(1, dto.getId_pro());

                int apagado = pst.executeUpdate();

                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "erro");

            }
        }
    }

}
