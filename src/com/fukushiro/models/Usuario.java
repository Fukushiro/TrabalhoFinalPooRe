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
    private Carrinho carrinho;
    private int criptografado;
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
        this.carrinho = new Carrinho(this);
    }

    public Usuario(int id, String nome, String senha, int tipo, double saldo, int criptografado) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.tipo = tipo;
        this.saldo = saldo;
        this.carrinho = new Carrinho(this);
        this.criptografado = criptografado;
    }

    public Usuario() {
        this.carrinho = new Carrinho(this);
    }

    //funções dao
    public boolean save() {
        Connection con = Dao.getInstance().getConnection();
        String sql = "insert into usuarios(nome, senha, tipo, saldo) values (?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, this.nome);
            ps.setString(2, new Criptografia().criptografar(this.senha));
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

    public Usuario getUsuario(int outroId, boolean close) {
        Usuario u = null;
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
                u = new Usuario(id, nome, senha, tipo, saldo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (close) {
            Dao.getInstance().closeConnection();
            con = null;
            rs = null;
        }

        return u;

    }

    public Usuario authenticate(String nome, String password, boolean criptografado) {
        Connection con = Dao.getInstance().getConnection();
        String sql = "select * from usuarios where nome=? AND senha=?";

        Usuario u = null;
        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, nome);
            if (criptografado) {
                ps.setString(2, new Criptografia().criptografar(password));
            } else {
                ps.setString(2, password);
            }
            ResultSet r = ps.executeQuery();
            while (r.next()) {
                int id = r.getInt("id");
                String n = r.getString("nome");
                String senha = "";
                if (!criptografado) {
                    senha = new Criptografia().criptografar(r.getString("senha"));
                    Usuario uN = new Usuario().getUsuario(id, false);
                    uN.setSenha(senha);
                    uN.setCriptografado(1);
                    uN.update(false);
                } else {
                    senha = r.getString("senha");
                }

                double saldo = r.getDouble("saldo");
                int tipo = r.getInt("tipo");
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

    public boolean update(boolean close) {
        Connection con = Dao.getInstance().getConnection();
        String sql = "update usuarios "
                + "set saldo = ?, nome = ?, criptografado = ?, senha= ?"
                + "where id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setDouble(1, this.saldo);
            ps.setString(2, this.nome);
            ps.setInt(3, this.criptografado);
            ps.setString(4, this.getSenha());
            ps.setInt(5, this.id);
            ps.executeUpdate();
            if (close) {
                Dao.getInstance().closeConnection();
            }
            return true;
        } catch (SQLException ex) {
            if (close) {
                Dao.getInstance().closeConnection();
            }
            return false;
        }
    }

    //função
    public void depositar(double saldo) {
        this.saldo += saldo;
    }

    public boolean sacar(double saldo) {
        if (this.saldo < saldo) {
            System.out.println("Usuario-sacar:falhou");
            return false;
        }
        this.saldo -= saldo;
        System.out.println("Usuario-sacar:" + saldo);
        return true;
    }

    //get e set
    public int getCriptografado() {
        return criptografado;
    }

    public void setCriptografado(int criptografado) {
        this.criptografado = criptografado;
    }

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

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public PreparedStatement getPs() {
        return ps;
    }

    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

}
