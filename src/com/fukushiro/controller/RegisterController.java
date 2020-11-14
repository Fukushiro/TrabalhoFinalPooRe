/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukushiro.controller;

import com.fukushiro.helper.RegisterHelper;
import com.fukushiro.models.Usuario;
import com.fukushiro.view.LoginView;
import com.fukushiro.view.RegisterView;
import javax.swing.JOptionPane;

/**
 *
 * @author jpflc
 */
public class RegisterController {

    private RegisterView view;
    private RegisterHelper helper;

    public RegisterController(RegisterView view) {
        this.view = view;
        this.helper = new RegisterHelper(view);
    }

    public void registrar() {
        Usuario u = (Usuario) this.helper.getObject();
        if (u.save()) {
            this.helper.clear();
            JOptionPane.showMessageDialog(view, "Cadastro realizado com sucesso");
        } else {
            JOptionPane.showMessageDialog(view, "Erro no cadastro");

        }
    }

    public void irParaLogin() {
        LoginView view2 = new LoginView();
        view2.setVisible(true);
        this.view.dispose();
    }

}
