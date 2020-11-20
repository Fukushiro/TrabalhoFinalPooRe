/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukushiro.controller;

import com.fukushiro.interfaces.ICompravel;
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
    private ClienteCarrinhoView view;

    public ClienteCarrinhoController(ClienteCarrinhoView view) {
        this.view = view;
    }
    
    public void start(){
        fillTable();
        setSaldo();
    }
    
    public void fillTable(){
        ArrayList<ICompravel> compraveis = Singleton.getInstance().getUsuarioLogado().getCarrinho().getProdutos();
        DefaultTableModel dtm = new DefaultTableModel(new Object[]{"id", "nome", "quantidade", "preço", "preço total"}, 0){
            @Override
            public boolean isCellEditable(int i, int j){
                return false;
            }
        };
        
        for(ICompravel c : compraveis){
            Produto p = (Produto)c;
            dtm.addRow(new Object[]{p.getId(), p.getNome(), p.getQuantidade(), p.getPreco(), (p.getQuantidade() * p.getPreco())});
        }
        
        
        
        this.view.getTable().setModel(dtm);
        
        TableColumnModel tcm =  this.view.getTable().getColumnModel();
        
        tcm.removeColumn(tcm.getColumn(0));
    }
    
    public void comprar(){
        if(Singleton.getInstance().getUsuarioLogado().getCarrinho().comprar()){
            JOptionPane.showMessageDialog(view, "Compra realizada com sucesso");
            setSaldo();
        }
        
    }
    public void setSaldo(){
        double saldo = Singleton.getInstance().getUsuarioLogado().getSaldo();
        
        this.view.getLblSaldo().setText("R$"+saldo);
    }
}
