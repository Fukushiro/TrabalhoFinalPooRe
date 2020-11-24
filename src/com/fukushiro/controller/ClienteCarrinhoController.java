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
import com.fukushiro.view.ClienteCarrinhoView;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author jpflc
 */
public class ClienteCarrinhoController {

    private Produto produto = null;
    private int pos = -1;
    private ClienteCarrinhoView view;

    public ClienteCarrinhoController(ClienteCarrinhoView view) {
        this.view = view;
    }

    public void start() {
        fillTable();
        setSaldo();
    }

    public void onTableClick() {
        int row = this.view.getTable().getSelectedRow();
        int col = 0;
        int pos =  Integer.valueOf(this.view.getTable().getModel().getValueAt(row, col).toString());
        col++;
        String tipo = this.view.getTable().getModel().getValueAt(row, col).toString();
        col++;
        int id = Integer.valueOf(this.view.getTable().getModel().getValueAt(row, col).toString());
        if (tipo == "console") {
            this.produto = new Console().where(id, true);
        } else if (tipo == "jogo") {
            this.produto = new Jogo().where(id, true);
        }
        this.pos = pos;
    }

    public void fillTable() {
        ArrayList<ICompravel> compraveis = Singleton.getInstance().getUsuarioLogado().getCarrinho().getProdutos();
        DefaultTableModel dtm = new DefaultTableModel(new Object[]{"pos", "tipo", "id", "nome", "quantidade", "preço", "preço total"}, 0) {
            @Override
            public boolean isCellEditable(int i, int j) {
                return false;
            }
        };
        int pos = 0;
        for (ICompravel c : compraveis) {
            Produto p = (Produto) c;
            String t = "";
            if (p instanceof Console) {
                t = "console";
            } else if (p instanceof Jogo) {
                t = "jogo";
            }
            dtm.addRow(new Object[]{pos, t, p.getId(), p.getNome(), p.getQuantidade(), p.getPreco(), (p.getQuantidade() * p.getPreco())});
            pos++;
        }

        this.view.getTable().setModel(dtm);

        TableColumnModel tcm = this.view.getTable().getColumnModel();

        //tcm.removeColumn(tcm.getColumn(0));
        //tcm.removeColumn(tcm.getColumn(0));       
        //tcm.removeColumn(tcm.getColumn(0));
    }

    public void comprar() {
        if (Singleton.getInstance().getUsuarioLogado().getCarrinho().comprar()) {
            JOptionPane.showMessageDialog(view, "Compra realizada com sucesso");
            setSaldo();
            fillTable();
        }

    }

    public void remover() {
        if (this.produto != null) {
            Singleton.getInstance().getUsuarioLogado().getCarrinho().remover(this.pos);
            this.fillTable();
        }
    }

    public void setSaldo() {
        double saldo = Singleton.getInstance().getUsuarioLogado().getSaldo();

        this.view.getLblSaldo().setText("R$" + saldo);
    }
}
