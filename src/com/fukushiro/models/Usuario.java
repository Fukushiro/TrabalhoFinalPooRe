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
public class Usuario {

    private int id;
    private String nome;
    private String senha;
    //0->cliente
    //1->admin
    private int tipo;
    private double saldo;
    //dao
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    //controller
    public Usuario(int id, String nome, String senha, int tipo, double saldo) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.tipo = tipo;
        this.saldo = saldo;
    }

    //funções dao
    public boolean save() {
        Connection con = Dao.getInstance().getConnection();
        String sql = "insert into usuarios(nome, senha, tipo, saldo) values (?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, this.nome);
            ps.setString(2, this.senha);
            ps.setInt(3, this.tipo);
            ps.setDouble(4, this.saldo);
            ps.execute();
        } catch (SQLException ex) {
            return false;
        }

        ps = null;
        Dao.getInstance().closeConnection();
        con = null;
        return true;
    }

    public ArrayList<Usuario> getAll(boolean close) {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        Connection con = Dao.getInstance().getConnection();
        String sql = "select * from usuarios";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String senha = rs.getString("senha");
                int tipo = rs.getInt("tipo");
                double saldo = rs.getDouble("saldo");
                lista.add(new Usuario(id, nome, senha, tipo, saldo));
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

    public Usuario getUsuario(int outroId) {
        Connection con = Dao.getInstance().getConnection();
        String sql = "select * from usuarios where id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, outroId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String senha = rs.getString("senha");
                int tipo = rs.getInt("tipo");
                double saldo = rs.getDouble("saldo");
                return new Usuario(id, nome, senha, tipo, saldo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        Dao.getInstance().closeConnection();
        rs = null;
        return null;

    }

    public Usuario authenticate(String nome, String password) {
        Connection con = Dao.getInstance().getConnection();
        String sql = "select * from usuarios where nome=? AND senha=?";

        Usuario u = null;
        try {
            ps = con.prepareStatement(sql);
            
            ps.setString(1, nome);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String n = rs.getString("nome");
                String senha = rs.getString("senha");
                int tipo = rs.getInt("tipo");
                double saldo = rs.getDouble("saldo");
                u = new Usuario(id, n, senha, tipo, saldo);
                return u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        Dao.getInstance().closeConnection();
        return u;
    }

    public boolean updateSaldo() {
        Connection con = Dao.getInstance().getConnection();
        String sql = "update usuarios "
                + "set saldo = ?"
                + "where id = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setDouble(1, this.saldo);
            ps.setInt(2, this.id);
            ps.executeUpdate();
            Dao.getInstance().closeConnection();
            return true;
        } catch (SQLException ex) {
            Dao.getInstance().closeConnection();
            return false;
        }
    }

    //função
    public void depositar(double saldo) {
        this.saldo += saldo;
    }

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

}
