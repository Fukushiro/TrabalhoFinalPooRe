/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukushiro.models;

import com.fukushiro.interfaces.ICompravel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author jpflc
 */
public class Console extends Produto implements ICompravel {

    private Empresa empresa;
    //dao
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public Console(Empresa empresa, int id, String nome, double preco, int quantidade) {
        super(id, nome, preco, quantidade);
        this.empresa = empresa;
    }

    public Console() {
    }

    //funções
    @Override
    public boolean comprar(Usuario u) {
        if (checkComprar(u)) {
            Console c = new Console().where(this.getId(), true);
            if (c.remover(this.getQuantidade()) && u.sacar(this.getPreco() * this.getQuantidade())) {
                c.update();
                u.updateSaldo();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkComprar(Usuario u) {
        Console c = new Console().where(this.getId(), true);
        if (c.getQuantidade() < this.getQuantidade()) {
            return false;
        }
        if (u.getSaldo() < (c.getPreco() * this.getQuantidade())) {
            return false;
        }
        return true;
    }

    //funções dao
    public boolean save() {
        Connection con = Dao.getInstance().getConnection();
        String sql = "insert into consoles(nome, preco, quantidade, empresa) values (?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getNome());
            ps.setDouble(2, this.getPreco());
            ps.setInt(3, this.getQuantidade());
            ps.setInt(4, this.empresa.getId());
            ps.execute();
        } catch (SQLException ex) {
            return false;
        }

        ps = null;
        Dao.getInstance().closeConnection();
        con = null;
        return true;
    }

    public ArrayList<Console> getAll(boolean close) {
        ArrayList<Console> lista = new ArrayList<Console>();
        Connection con = Dao.getInstance().getConnection();
        String sql = "select * from consoles";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                int quantidade = rs.getInt("quantidade");
                Empresa empresa = new Empresa().where(rs.getInt("empresa"), false);
                lista.add(new Console(empresa, id, nome, preco, quantidade));
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

    public ArrayList<Console> getLike(String l) {
        ArrayList<Console> consoles = new ArrayList<Console>();
        Connection con = Dao.getInstance().getConnection();
        String sql = "select * from consoles where nome like ?";

        try {
            this.ps = con.prepareStatement(sql);
            this.ps.setString(1, l + "%");
            this.rs = this.ps.executeQuery();

            while (rs.next()) {
                Empresa e = new Empresa().where(rs.getInt("empresa"), false);
                consoles.add(new Console(e, rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco"), rs.getInt("quantidade")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
        }
        return consoles;
    }

    public TableModel getWhereLike(String l) {
        TableModel tb = null;

        Connection con = Dao.getInstance().getConnection();
        String sql = "select * from consoles where nome like ?";

        try {
            this.ps = con.prepareStatement(sql);
            this.ps.setString(1, l + "%");
            this.rs = this.ps.executeQuery();
            DefaultTableModel dtm = new DefaultTableModel(new Object[]{"id", "nome", "preco", "quantidade", "empresa"}, 0) {
                @Override
                public boolean isCellEditable(int i, int i1) {
                    return false; //To change body of generated methods, choose Tools | Templates.
                }
            };

            while (rs.next()) {
                Empresa e = new Empresa().where(rs.getInt("empresa"), false);
                dtm.addRow(new Object[]{rs.getInt("id"), rs.getString("nome"), "R$" + rs.getDouble("preco"), rs.getInt("quantidade"), e.getNome()});
            }
            tb = dtm;
        } catch (SQLException ex) {
            Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
        }

        Dao.getInstance().closeConnection();
        return tb;
    }

    public Console where(int id, boolean close) {
        Console e = null;
        Connection con = Dao.getInstance().getConnection();
        String sql = "select * from consoles where id=?";

        try {
            this.ps = con.prepareStatement(sql);
            this.ps.setInt(1, id);
            this.rs = this.ps.executeQuery();
            while (rs.next()) {
                int idE = rs.getInt("id");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                int quantidade = rs.getInt("quantidade");
                Empresa empresa = new Empresa().where(rs.getInt("empresa"), false);
                e = new Console(empresa, id, nome, preco, quantidade);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (close) {
            Dao.getInstance().closeConnection();
        }
        con = null;
        return e;
    }

    public boolean update() {
        boolean retorno = false;
        Connection con = Dao.getInstance().getConnection();
        String sql = "update consoles set nome=?, preco=?, quantidade=?, empresa=? where id=?";

        try {
            this.ps = con.prepareStatement(sql);
            ps.setString(1, this.getNome());
            ps.setDouble(2, this.getPreco());
            ps.setInt(3, this.getQuantidade());
            ps.setInt(4, this.getEmpresa().getId());
            ps.setInt(5, this.getId());
            this.ps.executeUpdate();
           

            retorno = true;
        } catch (SQLException ex) {
            Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        Dao.getInstance().closeConnection();
        return retorno;
    }

    public boolean delete() {
        boolean retorno = false;
        Connection con = Dao.getInstance().getConnection();
        String sql = "delete from consoles where id = ?";

        try {
            this.ps = con.prepareStatement(sql);
            this.ps.setInt(1, this.getId());
            this.ps.execute();
            retorno = true;
        } catch (SQLException ex) {
            Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        Dao.getInstance().closeConnection();
        return retorno;
    }

    //get e set
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}
