/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukushiro.controller;

import com.fukushiro.interfaces.ICompravel;
import com.fukushiro.models.Console;
import com.fukushiro.models.Jogo;
import com.fukushiro.models.Produto;
import com.fukushiro.models.Singleton;
import com.fukushiro.view.ClienteComprarProdutoView;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author jpflc
 */
public class ClienteComprarProdutoController {

    private ICompravel produto;
    private ClienteComprarProdutoView view;

    public ClienteComprarProdutoController(ClienteComprarProdutoView view) {
        this.view = view;
    }

    public void setProduto() {
        if (this.produto != null) {
            int quantidade = (int) this.view.getSpinnerQuantidade().getValue();
            ((Produto) this.produto).setQuantidade(quantidade);
        }
    }

    public void startTable() {
        this.view.getTable().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                int col = 1;
                int row = view.getTable().getSelectedRow();
                String tipo = view.getTable().getModel().getValueAt(row, 0).toString();
                int id = Integer.valueOf(view.getTable().getModel().getValueAt(row, col).toString());
                if (tipo == "console") {
                    produto = new Console().where(id, true);

                } else if (tipo == "jogo") {
                    produto = new Jogo().where(id, true);
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {

            }

            @Override
            public void mouseReleased(MouseEvent me) {

            }

            @Override
            public void mouseEntered(MouseEvent me) {

            }

            @Override
            public void mouseExited(MouseEvent me) {

            }
        });
        fillTableLike();

    }

    public void fillTableLike() {
        String likeString = this.view.getTxtPesquisar().getText();
        ArrayList<Console> consoles = new Console().getLike(likeString);
        ArrayList<Jogo> jogos = new Jogo().getLike(likeString);
        DefaultTableModel dtm = new DefaultTableModel(new Object[]{"tipo", "id", "nome", "quantidade", "preco"}, 0) {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }
        };

        for (Console c : consoles) {
            dtm.addRow(new Object[]{"console", c.getId(), c.getNome(), c.getQuantidade(), c.getPreco()});
        }
        for(Jogo j: jogos){
            dtm.addRow(new Object[]{"jogo", j.getId(), j.getNome(), j.getQuantidade(), j.getPreco()});
        }
        
        this.view.getTable().setModel(dtm);

        TableColumnModel tcm = this.view.getTable().getColumnModel();

        tcm.removeColumn(tcm.getColumn(0));
        tcm.removeColumn(tcm.getColumn(0));

    }

    public void colocarNoCarrinho() {
        setProduto();
        System.out.println(produto);
        if (this.produto != null && ((Produto) this.produto).getQuantidade() > 0) {

            Singleton.getInstance().getUsuarioLogado().getCarrinho().addItem(produto);
            Produto p = ((Produto) produto);
            String mensagem = p.getQuantidade() + " do produto " + p.getNome() + " forão adicionados no carrinho";
            JOptionPane.showMessageDialog(view, mensagem);
        }
    }
}
