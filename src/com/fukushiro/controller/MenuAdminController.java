/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukushiro.controller;

import com.fukushiro.view.AdminCadastrarUsuarioView;
import com.fukushiro.view.AdminConsoleView;
import com.fukushiro.view.AdminEmpresaView;
import com.fukushiro.view.AdminGeneroView;
import com.fukushiro.view.AdminJogoView;
import com.fukushiro.view.MenuAdminView;

/**
 *
 * @author jpflc
 */
public class MenuAdminController {
    MenuAdminView view;

    public MenuAdminController(MenuAdminView view) {
        this.view = view;
    }
    
    public void abrirGerenciarConsole(){
        AdminConsoleView view = new AdminConsoleView();
        view.setVisible(true);
        this.view.getDesktop().add(view);
    }
    
    public void abrirGerenciarEmpresa(){
        AdminEmpresaView view = new AdminEmpresaView();
        view.setVisible(true);
        this.view.getDesktop().add(view);
    }
    
    public void abrirGerenciarGenero(){
        AdminGeneroView view = new AdminGeneroView();
        view.setVisible(true);
        this.view.getDesktop().add(view);
    }
    
    public void abrirGerenciarJogo(){
        AdminJogoView view = new AdminJogoView();
        view.setVisible(true);
        this.view.getDesktop().add(view);
    }
    
    public void abrirCadastrarUsuario(){
        AdminCadastrarUsuarioView view = new AdminCadastrarUsuarioView();
        view.setVisible(true);
        this.view.getDesktop().add(view);
    }
    
}
