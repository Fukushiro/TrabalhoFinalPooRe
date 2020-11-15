/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukushiro.controller;

import com.fukushiro.helper.AdminGeneroHelper;
import com.fukushiro.models.Console;
import com.fukushiro.models.Empresa;
import com.fukushiro.models.Genero;
import com.fukushiro.view.AdminGeneroView;
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
public class AdminGeneroController {

    private Genero genero;
    private AdminGeneroView view;
    private AdminGeneroHelper helper;

    public AdminGeneroController(AdminGeneroView view) {
        this.view = view;
        this.helper = new AdminGeneroHelper(view);
    }

    public void startGenero(int id) {
        this.genero = new Genero().where(id, true);
    }

    public void fillFields() {
        if (this.genero != null) {
            String nome = this.genero.getNome();
            this.view.getTxtNome().setText(nome);
        } else {
            this.view.getTxtNome().setText("");
        }
    }
    
    public void update() {
        Genero e = (Genero) this.helper.getObject();
        if (e != null) {
            this.genero.setNome(e.getNome());
            this.genero.update();
            JOptionPane.showMessageDialog(view, "Empresa atualizada");
        } else {
            JOptionPane.showMessageDialog(view, "Falha no update");
        }
        emptyFields();
        this.popularTabela();
    }

    public void delete() {
        this.genero.delete();
        emptyFields();
        this.popularTabela();
    }

    public void emptyFields() {
        this.genero = null;
        fillFields();
    }

    public void cadastrar() {
        Genero g = (Genero) this.helper.getObject();
        if (g != null) {
            if (g.save()) {
                JOptionPane.showMessageDialog(view, "Genero cadastrado com sucesso");
            } else {
                JOptionPane.showMessageDialog(view, "Erro ao cadastrar genero");

            }
        } else {
            JOptionPane.showMessageDialog(view, "Erro ao cadastrar genero");

        }
        emptyFields();
        this.popularTabela();
    }

    public void popularTabela() {

//        ArrayList<Console> consoles = new Console().getAll(true);;
//        
//        DefaultTableModel dtm = (DefaultTableModel) this.view.getTable().getModel();;
//        for(Console c: consoles){
//            dtm.addRow(new Object[] {c.getId(), c.getNome(), c.getPreco(), c.getQuantidade(), c.getEmpresa().getNome()});
//            
//        }
        TableModel tm = new Genero().getWhereLike(this.view.getTxtPesquisa().getText());

        this.view.getTable().setModel(tm);

        TableColumnModel tcm = this.view.getTable().getColumnModel();
        tcm.removeColumn(tcm.getColumn(0));

        this.view.getTable().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                int row = view.getTable().getSelectedRow();
                int col = 0;
                int id = Integer.valueOf(view.getTable().getModel().getValueAt(row, col).toString());
                startGenero(id);
                fillFields();
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
