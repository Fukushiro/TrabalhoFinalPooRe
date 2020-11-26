/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukushiro.controller;

import com.fukushiro.models.Compra;
import com.fukushiro.models.Singleton;
import com.fukushiro.models.Usuario;
import com.fukushiro.view.ClienteCompraView;
import java.sql.ResultSet;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author jpflc
 */
public class ClienteCompraController {
    private ClienteCompraView view;

    public ClienteCompraController(ClienteCompraView view) {
        this.view = view;
    }
    
    
    public void start(){
        startTable();
    }
    
    public void startTable(){
        Usuario u = Singleton.getInstance().getUsuarioLogado();
        String like = "";
        ResultSet rs = new Compra().getRsLike(like, u.getId());
        TableModel model = DbUtils.resultSetToTableModel(rs);
        this.view.getTable().setModel(model);
        TableColumnModel tcm = this.view.getTable().getColumnModel();
        tcm.removeColumn(tcm.getColumn(0));
    }
}
