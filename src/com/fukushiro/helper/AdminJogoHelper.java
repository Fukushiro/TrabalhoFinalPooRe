/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukushiro.helper;

import com.fukushiro.models.Genero;
import com.fukushiro.models.Jogo;
import com.fukushiro.view.AdminJogoView;

/**
 *
 * @author jpflc
 */
public class AdminJogoHelper extends Helper {

    private AdminJogoView view;

    public AdminJogoHelper(AdminJogoView view) {
        this.view = view;
    }

    @Override
    public void clear() {
        this.view.getTxtNome().setText("");
        this.view.getTxtPreco().setText("");
        this.view.getTxtQuantidade().setText("");
    }

    @Override
    public Object getObject() {
        String nome = this.view.getTxtNome().getText();
        Double preco = Double.valueOf(this.view.getTxtPreco().getText());
        int quantidade = Integer.valueOf(this.view.getTxtQuantidade().getText());
        Genero genero = (Genero) this.view.getComboGenero().getSelectedItem();
        
        return new Jogo(genero, 0, nome, preco, quantidade);
    }

}
