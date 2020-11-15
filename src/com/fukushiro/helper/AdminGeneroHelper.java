/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukushiro.helper;

import com.fukushiro.models.Genero;
import com.fukushiro.view.AdminGeneroView;

/**
 *
 * @author jpflc
 */
public class AdminGeneroHelper extends Helper {

    private AdminGeneroView view;

    public AdminGeneroHelper(AdminGeneroView view) {
        this.view = view;
    }

    @Override
    public void clear() {
        this.view.getTxtNome().setText("");
    }

    @Override
    public Object getObject() {
        String nome = this.view.getTxtNome().getText();
        return new Genero(0, nome);
    }

}
