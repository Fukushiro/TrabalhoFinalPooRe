/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukushiro.helper;

import com.fukushiro.models.Empresa;
import com.fukushiro.view.AdminEmpresaView;

/**
 *
 * @author jpflc
 */
public class AdminEmpresaHelper extends Helper{
    private AdminEmpresaView view;

    public AdminEmpresaHelper(AdminEmpresaView view) {
        this.view = view;
    }
    
    
    @Override
    public void clear() {
        this.view.getTxtNome().setText("");
    }

    @Override
    public Object getObject() {
         try {
            String nome = this.view.getTxtNome().getText();
            Empresa e = new Empresa(0, nome);
            return e;
        } catch (NumberFormatException e) {

        }
        return null;
    }
    
}
