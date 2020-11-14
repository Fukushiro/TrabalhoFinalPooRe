/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukushiro.controller;

import com.fukushiro.helper.AdminEmpresaHelper;
import com.fukushiro.models.Empresa;
import com.fukushiro.view.AdminEmpresaView;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author jpflc
 */
public class AdminEmpresaController {

    private Empresa empresa;
    private AdminEmpresaView view;
    private AdminEmpresaHelper helper;

    public AdminEmpresaController(AdminEmpresaView view) {
        this.view = view;
        this.helper = new AdminEmpresaHelper(view);
    }

    public void emptyTextField() {
        this.empresa = null;
        fillTextField();
    }

    public void fillTextField() {
        if (this.empresa != null) {
            this.view.getTxtNome().setText(this.empresa.getNome());
        } else {
            this.view.getTxtNome().setText("");

        }
    }

    public void startEmpresa(int id) {
        this.empresa = new Empresa().where(id, true);
    }

    public void populate() {
        String val = this.view.getTxtPesquisar().getText();
        TableModel tm = new Empresa().getWhereLike(val);
        this.view.getTable().setModel(tm);
        TableColumnModel tc = this.view.getTable().getColumnModel();
        tc.removeColumn(tc.getColumn(0));
    }

    public void start() {
        populate();
        this.view.getTable().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                int row = view.getTable().getSelectedRow();
                int col = 0;
                int id = Integer.valueOf(view.getTable().getModel().getValueAt(row, col).toString());
                startEmpresa(id);
                fillTextField();
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

    public void cadastrar() {
        Empresa e = (Empresa) this.helper.getObject();
        if (e != null) {
            e.save();
            JOptionPane.showMessageDialog(view, "Empresa cadastrada");
            this.helper.clear();
        } else {
            JOptionPane.showMessageDialog(view, "Erro ao cadastrar a empresa");
            this.helper.clear();

        }
        populate();
        emptyTextField();
    }

    public void update() {
        Empresa e = (Empresa) this.helper.getObject();
        if (e != null) {
            this.empresa.setNome(e.getNome());
            this.empresa.update();
            JOptionPane.showMessageDialog(view, "Empresa atualizada");
        } else {
            JOptionPane.showMessageDialog(view, "Falha no update");
        }
        populate();
        emptyTextField();
    }

    public void delete() {
        this.empresa.delete();
        populate();
        emptyTextField();
    }

}
