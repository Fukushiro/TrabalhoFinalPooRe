/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukushiro.controller;

import com.fukushiro.exceptions.DepositarNegativoException;
import com.fukushiro.models.Singleton;
import com.fukushiro.view.ClienteDepositarView;

/**
 *
 * @author jpflc
 */
public class ClienteDepositarController {
    private ClienteDepositarView view;

    public ClienteDepositarController(ClienteDepositarView view) {
        this.view = view;
    }
    
    public void clear(){
        this.view.getTxtValor().setText("");
    }
    
    public void setSaldoLabel(){
        double saldo = Singleton.getInstance().getUsuarioLogado().getSaldo();
        this.view.getLblValorAtual().setText("R$"+String.valueOf(saldo));
    }
    
    public void depositar(){
        double val = Double.valueOf(this.view.getTxtValor().getText());
        if(val<0){
            throw new DepositarNegativoException();
        }
        Singleton.getInstance().getUsuarioLogado().depositar(val);
        clear();
        Singleton.getInstance().getUsuarioLogado().updateSaldo();
    }
    
}
