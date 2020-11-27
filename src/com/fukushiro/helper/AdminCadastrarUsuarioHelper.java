/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukushiro.helper;

import com.fukushiro.models.Usuario;
import com.fukushiro.view.AdminCadastrarUsuarioView;

/**
 *
 * @author jpflc
 */
public class AdminCadastrarUsuarioHelper extends Helper {

    private AdminCadastrarUsuarioView view;

    public AdminCadastrarUsuarioHelper(AdminCadastrarUsuarioView view) {
        this.view = view;
    }

    @Override
    public void clear() {
        this.view.getTxtNome().setText("");
        this.view.getTxtSenha().setText("");

    }

    @Override
    public Object getObject() {
        String nome = this.view.getTxtNome().getText();
        String pass = new String(this.view.getTxtSenha().getPassword());
        int tipo = this.view.getComboTipo().getSelectedIndex();
        Usuario u = new Usuario(0, nome, pass, tipo, 0);

        return u;
    }

}
