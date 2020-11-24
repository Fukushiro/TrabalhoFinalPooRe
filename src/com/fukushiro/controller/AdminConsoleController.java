/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukushiro.controller;

import com.fukushiro.exceptions.NegativeQuantityException;
import com.fukushiro.models.Console;
import com.fukushiro.models.Empresa;
import com.fukushiro.helper.AdminConsoleHelper;
import com.fukushiro.view.AdminConsoleView;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author jpflc
 */
public class AdminConsoleController {

    private Console console;
    private AdminConsoleView view;
    private AdminConsoleHelper helper;

    public AdminConsoleController(AdminConsoleView view) {
        this.view = view;
        this.helper = new AdminConsoleHelper(view);
    }

    public void emptyTxtField() {
        this.console = null;
        fillTxtField();
    }

    public void fillTxtField() {
        if (this.console != null) {
            this.view.getTxtNome().setText(console.getNome());
            this.view.getTxtPreco().setText(String.valueOf(this.console.getPreco()));
            this.view.getTxtQuantidade().setText(String.valueOf(this.console.getQuantidade()));
            int pos = 0;
            for (int i = 0; i < this.view.getComboEmpresa().getItemCount(); i++) {
                Empresa c = this.view.getComboEmpresa().getItemAt(i);

                if (c.getId() == console.getEmpresa().getId()) {
                    pos = i;
                    break;
                }
            }
            this.view.getComboEmpresa().setSelectedIndex(pos);

        } else {
            this.view.getTxtNome().setText("");
            this.view.getTxtPreco().setText("");
            this.view.getTxtQuantidade().setText("");
        }
    }

    public void startConsole(int id) {
        this.console = new Console().where(id, true);
    }

    public void update() {
        if (this.console != null) {
            Console c = (Console) this.helper.getObject();
            if (c != null) {
                this.console.setNome(c.getNome());
                this.console.setPreco(c.getPreco());
                this.console.setQuantidade(c.getQuantidade());
                this.console.setEmpresa(c.getEmpresa());
                this.console.update();
                JOptionPane.showMessageDialog(view, "Update realizado");
            } else {
                JOptionPane.showMessageDialog(view, "Erro ao atualizar");
            }
        }
        populate();
        emptyTxtField();

    }

    public void delete() {
        if (this.console.delete()) {
            JOptionPane.showMessageDialog(view, "Apagado");
        }
        populate();
        emptyTxtField();
    }

    public void startComboEmpresa() {
        Empresa e = new Empresa();
        ArrayList<Empresa> lista = e.getAll(true);

        //  this.view.getComboEmpresa().setModel(new DefaultComboBoxModel<String>(lista.toArray(new String[0])));
        for (Empresa ee : lista) {
            this.view.getComboEmpresa().addItem(ee);
        }
    }

    public void cadastrar() throws NegativeQuantityException{
        Console console = (Console) this.helper.getObject();
        if(console.getQuantidade() < 0){
            throw new NegativeQuantityException();
        }
        if (console != null) {
            console.save();
            JOptionPane.showMessageDialog(view, "Console cadastrado com sucesso");
            this.helper.clear();
        } else {
            JOptionPane.showMessageDialog(view, "Erro ao cadastrar");

            this.helper.clear();
        }
        populate();
        emptyTxtField();
    }

    public void populate() {
        String val = this.view.getTxtProcurar().getText();
        TableModel tm = new Console().getWhereLike(val);
        this.view.getTable().setModel(tm);

        TableColumnModel tcm = this.view.getTable().getColumnModel();
        tcm.removeColumn(tcm.getColumn(0));
    }

    public void popularTabela() {

        populate();

        this.view.getTable().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                int row = view.getTable().getSelectedRow();
                int col = 0;
                int id = Integer.valueOf(view.getTable().getModel().getValueAt(row, col).toString());
                startConsole(id);
                fillTxtField();
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
    }
}
