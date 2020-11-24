/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukushiro.controller;

import com.fukushiro.exceptions.NegativeQuantityException;
import com.fukushiro.helper.AdminJogoHelper;
import com.fukushiro.models.Genero;
import com.fukushiro.models.Jogo;
import com.fukushiro.view.AdminJogoView;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author jpflc
 */
public class AdminJogoController {

    private Jogo jogo = null;
    private AdminJogoView view;
    private AdminJogoHelper helper;

    public AdminJogoController(AdminJogoView view) {
        this.view = view;
        this.helper = new AdminJogoHelper(view);

    }

    public void startJogo(int id) {
        this.jogo = new Jogo().where(id, true);
        if (this.jogo != null) {
            this.view.getTxtNome().setText(this.jogo.getNome());
            this.view.getTxtPreco().setText(String.valueOf(this.jogo.getPreco()));
            this.view.getTxtQuantidade().setText(String.valueOf(this.jogo.getQuantidade()));
            int pos = 0;
            for (int i = 0; i < this.view.getComboGenero().getItemCount(); i++) {
                Genero g = this.view.getComboGenero().getItemAt(i);
                if (g.getId() == this.jogo.getGenero().getId()) {
                    pos = i;
                }
            }
            this.view.getComboGenero().setSelectedIndex(pos);

        }
    }

    public void emptyJogo() {
        this.jogo = null;
        this.helper.clear();
    }

    public void start() {
        startTableLike();
        startCombo();
    }

    public void startCombo() {
        ArrayList<Genero> generos = new Genero().getAll(true);
        for (Genero genero : generos) {
            this.view.getComboGenero().addItem(genero);
        }
    }

    public void startTableLike() {
        String like = this.view.getTxtPesquisa().getText();
        TableModel tm = DbUtils.resultSetToTableModel(new Jogo().getRsLike(like));
        this.view.getTable().setModel(tm);
        TableColumnModel tcm = this.view.getTable().getColumnModel();
        tcm.removeColumn(tcm.getColumn(0));
    }

    public void onTableClick() {
        int row = this.view.getTable().getSelectedRow();
        int col = 0;
        int id = Integer.valueOf(this.view.getTable().getModel().getValueAt(row, col).toString());
        startJogo(id);

    }

    public void cadastrar() throws NegativeQuantityException {
        try {

            Jogo j = (Jogo) this.helper.getObject();
            if (j != null) {
                if (j.getQuantidade() < 0) {
                    throw new NegativeQuantityException();
                }
                new Jogo(j.getGenero(), 0, j.getNome(), j.getPreco(), j.getQuantidade()).save();
                emptyJogo();
                startTableLike();
                JOptionPane.showMessageDialog(view, "Jogo cadastrado");
            } else {
                JOptionPane.showMessageDialog(view, "Falha ao cadastrar");

            }
        } catch (NumberFormatException e) {

        }

    }

    public void update() {
        if (this.jogo != null) {
            Jogo j = (Jogo) this.helper.getObject();

            this.jogo.setNome(j.getNome());
            this.jogo.setPreco(j.getPreco());
            this.jogo.setQuantidade(j.getQuantidade());
            this.jogo.setGenero(j.getGenero());
            this.jogo.update();
            this.emptyJogo();
            this.startTableLike();
        }
    }

    public void delete() {
        if (this.jogo != null) {
            if (this.jogo.delete()) {
                JOptionPane.showMessageDialog(view, "Jogo deletado");
                emptyJogo();
                this.startTableLike();
            }
        }
    }

}
