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

/**
 *
 * @author jpflc
 */
public class Genero {

    private int id;
    private String nome;
    //dao
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public Genero(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    public Genero(){
        
    }
    //dao
    public boolean save() {
        Connection con = Dao.getInstance().getConnection();
        String sql = "insert into generos(nome) values (?)";
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
    public ArrayList<Genero> getAll(boolean close) {
        ArrayList<Genero> lista = new ArrayList<Genero>();
        Connection con = Dao.getInstance().getConnection();
        String sql = "select * from generos";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                
                lista.add(new Genero(id, nome));
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
    
    public boolean update(){
        boolean retorno = false;
        Connection con = Dao.getInstance().getConnection();
        String sql = "update gerentes set nome=? where id=?";
        
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
        String sql = "delete from gerentes where id = ?";
        
        try {
            this.ps = con.prepareStatement(sql);
            this.ps.setInt(1, this.id);
            this.ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return retorno;
    }
    //função

    //get e set
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
