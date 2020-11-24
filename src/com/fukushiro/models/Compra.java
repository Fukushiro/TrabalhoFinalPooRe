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
public class Compra {
    private int id;
    private Usuario usuario;
    private String tipo;
    private String produto;
    private double valor;
    
    //dao
    private PreparedStatement ps = null;
    private ResultSet rs = null;


    public Compra(int id, Usuario usuario, String tipo, String produto, double valor) {
        this.id = id;
        this.usuario = usuario;
        this.tipo = tipo;
        this.produto = produto;
        this.valor = valor;
    }
    
    public Compra(){
        
    }
    //funções dao
    public boolean save() {
        Connection con = Dao.getInstance().getConnection();
        String sql = "insert into compras(usuario, tipo, produto, valor) values (?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, this.usuario.getId());
            ps.setString(2, this.tipo);
            ps.setString(3, this.produto);
            ps.setDouble(4, this.valor);
            ps.execute();
        } catch (SQLException ex) {
            return false;
        }

        ps = null;
        Dao.getInstance().closeConnection();
        con = null;
        return true;
    }
    
    public ArrayList<Compra> getAll(boolean close) {
        ArrayList<Compra> lista = new ArrayList<Compra>();
        Connection con = Dao.getInstance().getConnection();
        String sql = "select * from compras";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Usuario u = new Usuario().getUsuario(rs.getInt("usuario"), false);
                String tipo = rs.getString("tipo");
                String produto = rs.getString("produto");
                double valor = rs.getDouble("valor");
                lista.add(new Compra(id, u, tipo, produto, valor));
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
    
    public ResultSet getRsLike(String like){
        Connection con = Dao.getInstance().getConnection();
        String sql = "select c.id, u.nome, c.produto, c.valor from compras as c  left join usuarios as u on c.usuario = u.id where c.produto like ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, like+"%");
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Compra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    
    
    //get e set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
    
    
}
