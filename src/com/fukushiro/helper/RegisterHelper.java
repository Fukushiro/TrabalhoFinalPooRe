/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukushiro.helper;

import com.fukushiro.models.Usuario;
import com.fukushiro.view.RegisterView;

/**
 *
 * @author jpflc
 */
public class RegisterHelper extends Helper{
    private RegisterView view;

    public RegisterHelper(RegisterView view) {
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
