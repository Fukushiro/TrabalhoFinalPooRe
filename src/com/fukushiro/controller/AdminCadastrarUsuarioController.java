/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukushiro.controller;

import com.fukushiro.helper.AdminCadastrarUsuarioHelper;
import com.fukushiro.models.Usuario;
import com.fukushiro.view.AdminCadastrarUsuarioView;
import javax.swing.JOptionPane;

/**
 *
 * @author jpflc
 */
public class AdminCadastrarUsuarioController {
    private AdminCadastrarUsuarioView view;
    private AdminCadastrarUsuarioHelper helper;

    public AdminCadastrarUsuarioController(AdminCadastrarUsuarioView view) {
        this.view = view;
        this.helper = new AdminCadastrarUsuarioHelper(view);
    }
    
    public void registrar(){
        Usuario u = (Usuario) this.helper.getObject();
        if (u.save()) {
            this.helper.clear();
            JOptionPane.showMessageDialog(view, "Cadastro realizado com sucesso");
        } else {
            JOptionPane.showMessageDialog(view, "Erro no cadastro");

        }
    }
    
}
