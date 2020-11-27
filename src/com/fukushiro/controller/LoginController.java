/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukushiro.controller;

import com.fukushiro.helper.LoginHelper;
import com.fukushiro.models.Singleton;
import com.fukushiro.models.Usuario;
import com.fukushiro.view.LoginView;
import com.fukushiro.view.MenuAdminView;
import com.fukushiro.view.MenuClienteView;
import com.fukushiro.view.RegisterView;
import javax.swing.JOptionPane;

/**
 *
 * @author jpflc
 */
public class LoginController {

    private LoginView view;
    private LoginHelper helper;

    public LoginController(LoginView view) {
        this.view = view;
        this.helper = new LoginHelper(view);
    }

    //metodos
    public void logar() {
        Usuario u = (Usuario) this.helper.getObject();
        if (u != null) {
            Usuario uLogado = u.authenticate(u.getNome(), u.getSenha(), true);
            if(uLogado == null){
                uLogado = u.authenticate(u.getNome(), u.getSenha(), true);
            }
            if (uLogado != null) {
                Singleton.getInstance().setUsuarioLogado(uLogado);
                if (uLogado.getTipo() == 0) {//cliente
                   
                    MenuClienteView v = new MenuClienteView();
                    v.setVisible(true);
                    this.view.dispose();
                } else if (uLogado.getTipo() == 1) {//admin
                    MenuAdminView v = new MenuAdminView();
                    v.setVisible(true);
                    this.view.dispose();
                }

            } else {
                JOptionPane.showMessageDialog(view, "Usuario nã existe");
                this.helper.clear();
            }
        }
    }

    public void registrar() {
        RegisterView view2 = new RegisterView();
        view2.setVisible(true);
        this.view.dispose();
    }

    public LoginView getView() {
        return view;
    }

    public void setView(LoginView view) {
        this.view = view;
    }
}
