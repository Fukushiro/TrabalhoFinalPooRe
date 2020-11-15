/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukushiro.controller;

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
}
