/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukushiro.helper;


import com.fukushiro.models.Console;
import com.fukushiro.models.Empresa;
import com.fukushiro.view.AdminConsoleView;
import javax.swing.JOptionPane;

/**
 *
 * @author jpflc
 */
public class AdminConsoleHelper extends Helper{
    private AdminConsoleView view;

    public AdminConsoleHelper(AdminConsoleView view) {
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
        try {
            String nome = this.view.getTxtNome().getText();
            double preco = Double.valueOf(this.view.getTxtPreco().getText());
            int quantidade = Integer.valueOf(this.view.getTxtQuantidade().getText());
            Empresa empresa = (Empresa) this.view.getComboEmpresa().getSelectedItem();
            Console console = new Console(empresa, quantidade, nome, preco, quantidade);
            return console;
        } catch (NumberFormatException e) {
            clear();
            JOptionPane.showMessageDialog(view, "Não foi possivel cadastrar");
        }
        return null;
    }
    
}
