/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukushiro.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author jpflc
 */
public class Empresa {

    private int id;
    private String nome;
    //dao
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public Empresa() {
    }
    
    public Empresa(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    //dao
    public boolean save() {
        Connection con = Dao.getInstance().getConnection();
        String sql = "insert into empresas(nome) values (?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, this.nome);

            ps.execute();
        } catch (SQLException ex) {
            return false;
        }

        ps = null;
        Dao.getInstance().closeConnection();
        con = null;
        return true;
    }
    
    public ArrayList<Empresa> getAll(boolean close) {
        ArrayList<Empresa> lista = new ArrayList<Empresa>();
        Connection con = Dao.getInstance().getConnection();
        String sql = "select * from empresas";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                
                lista.add(new Empresa(id, nome));
            }
        } catch (SQLException ex) {

        }
        rs = null;
        if (close) {
            Dao.getInstance().closeConnection();
            con = null;
        }
        return lista;
    }
    
    
    public TableModel getWhereLike(String l){
        TableModel tb = null;
        
        Connection con = Dao.getInstance().getConnection();
        String sql = "select * from empresas where nome like ?";
        
        try {
            this.ps = con.prepareStatement(sql);
            this.ps.setString(1, l+"%");
            this.rs = this.ps.executeQuery();
            DefaultTableModel dtm = new DefaultTableModel(new Object[]{"id", "nome"}, 0);
            
            while(rs.next()){
                
                dtm.addRow(new Object[]{rs.getInt("id"), rs.getString("nome")});
            }
            tb = dtm;
        } catch (SQLException ex) {
            Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Dao.getInstance().closeConnection();
        return tb;
    }
    
    public Empresa where(int id, boolean close){
        Empresa e = null;
        Connection con = Dao.getInstance().getConnection();
        String sql = "select * from empresas where id=?";
        
        try {
            this.ps = con.prepareStatement(sql);
            this.ps.setInt(1, id);
            this.rs = this.ps.executeQuery();
            while(rs.next()){
                int idE = rs.getInt("id");
                String nome = rs.getString("nome");
                e =  new Empresa(id, nome);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(close){
            Dao.getInstance().closeConnection();
        }
        con = null;
        return e;
    }
    
    public boolean update(){
        boolean retorno = false;
        Connection con = Dao.getInstance().getConnection();
        String sql = "update empresas set nome=? where id=?";
        
        try {
            this.ps = con.prepareStatement(sql);
            ps.setString(1, this.nome);
            ps.setInt(2, this.id);
            this.ps.executeUpdate();
            
            retorno=  true;
        } catch (SQLException ex) {
            Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        Dao.getInstance().closeConnection();
        return retorno;
    }
    
    public boolean delete(){
        boolean retorno = false;
        Connection con = Dao.getInstance().getConnection();
        String sql = "delete from empresas where id = ?";
        
        try {
            this.ps = con.prepareStatement(sql);
            this.ps.setInt(1, this.id);
            this.ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return retorno;
    }
    

    //funcoes
    public String toString(){
        return this.nome;
    }
    
    
    //
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}