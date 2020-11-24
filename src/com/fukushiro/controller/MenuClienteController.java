/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukushiro.controller;

import com.fukushiro.view.ClienteCarrinhoView;
import com.fukushiro.view.ClienteCompraView;
import com.fukushiro.view.ClienteComprarProdutoView;
import com.fukushiro.view.ClienteDepositarView;
import com.fukushiro.view.MenuClienteView;

/**
 *
 * @author jpflc
 */
public class MenuClienteController {
    private MenuClienteView view;

    public MenuClienteController(MenuClienteView view) {
        this.view = view;
    }
    
    
    public void abrirDepositar(){
        ClienteDepositarView view = new ClienteDepositarView();
        view.setVisible(true);
        this.view.getDesktop().add(view);
    }
    
    public void abrirLojaComprar(){
        ClienteComprarProdutoView view = new ClienteComprarProdutoView();
        view.setVisible(true);
        this.view.getDesktop().add(view);
    }
    
    public void abrirGerenciarCarrinho(){
        ClienteCarrinhoView view = new ClienteCarrinhoView();
        view.setVisible(true);
        this.view.getDesktop().add(view);
    }
    
    public void abrirGerenciarCompras(){
        ClienteCompraView view = new ClienteCompraView();
        view.setVisible(true);
        this.view.getDesktop().add(view);
    }
}
