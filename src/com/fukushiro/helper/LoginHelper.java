/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukushiro.helper;

import com.fukushiro.models.Usuario;
import com.fukushiro.view.LoginView;

/**
 *
 * @author jpflc
 */
public class LoginHelper extends Helper{
    private LoginView view;

    public LoginHelper(LoginView view) {
        this.view = view;
    }
    

    @Override
    public void clear() {
        this.view.getTxtNome().setText("");
        this.view.getTxtPass().setText("");
    }

    @Override
    public Object getObject() {
        String nome = this.view.getTxtNome().getText();
        String pass = new String(this.view.getTxtPass().getPassword());
        Usuario u = new Usuario(0, nome, pass, 0, 0);
        
        return u;
    }
    
}
