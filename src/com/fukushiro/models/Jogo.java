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

/**
 *
 * @author jpflc
 */
public class Jogo extends Produto implements ICompravel{
    private Genero genero;
    //dao
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    

    public Jogo(Genero genero, int id, String nome, double preco, int quantidade) {
        super(id, nome, preco, quantidade);
        this.genero = genero;
    }
    
    public Jogo(){
        
    }
    
    @Override
    public boolean comprar(Usuario u) {
        if (checkComprar(u)) {
            Jogo c = new Jogo().where(this.getId(), true);
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
       Jogo c = new Jogo().where(this.getId(), true);
        if (c.getQuantidade() < this.getQuantidade()) {
            return false;
        }
        if (u.getSaldo() < (c.getPreco() * this.getQuantidade())) {
            return false;
        }
        return true;
    }
    
    //funções dao
    
     public Jogo where(int id, boolean close) {
        Jogo e = null;
        Connection con = Dao.getInstance().getConnection();
        String sql = "select * from jogos where id=?";

        try {
            this.ps = con.prepareStatement(sql);
            this.ps.setInt(1, id);
            this.rs = this.ps.executeQuery();
            while (rs.next()) {
                int idE = rs.getInt("id");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                int quantidade = rs.getInt("quantidade");
                Genero genero = new Genero().where(rs.getInt("genero"), false);
                e = new Jogo(genero, id, nome, preco, quantidade);
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
        String sql = "update jogos set nome=?, preco=?, quantidade=?, genero=? where id=?";

        try {
            this.ps = con.prepareStatement(sql);
            ps.setString(1, this.getNome());
            ps.setDouble(2, this.getPreco());
            ps.setInt(3, this.getQuantidade());
            ps.setInt(4, this.genero.getId());
            ps.setInt(5, this.getId());
            this.ps.executeUpdate();

            retorno = true;
        } catch (SQLException ex) {
            Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        Dao.getInstance().closeConnection();
        return retorno;
    }
    
    public boolean save() {
        Connection con = Dao.getInstance().getConnection();
        String sql = "insert into jogos(nome, preco, quantidade, genero) values (?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getNome());
            ps.setDouble(2, this.getPreco());
            ps.setInt(3, this.getQuantidade());
            ps.setInt(4, this.genero.getId());
            ps.execute();
        } catch (SQLException ex) {
            return false;
        }

        ps = null;
        Dao.getInstance().closeConnection();
        con = null;
        return true;
    }
    
    public ResultSet getRsLike(String like){
        
        Connection con = Dao.getInstance().getConnection();
        String sql = "select j.id, j.nome, j.preco, j.quantidade, g.nome from jogos as j\n" +
"left join generos as g on g.id = j.genero;";
        
        try {
            ps = con.prepareStatement(sql);
            this.rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Jogo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return this.rs;
        
    }
    public ArrayList<Jogo> getLike(String l) {
        ArrayList<Jogo> consoles = new ArrayList<Jogo>();
        Connection con = Dao.getInstance().getConnection();
        String sql = "select * from jogos where nome like ?";

        try {
            this.ps = con.prepareStatement(sql);
            this.ps.setString(1, l + "%");
            this.rs = this.ps.executeQuery();

            while (rs.next()) {
                Genero g = new Genero().where(rs.getInt("genero"), false);
                
                consoles.add(new Jogo(g, rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco"), rs.getInt("quantidade")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
        }
        return consoles;
    }
    public boolean delete() {
        boolean retorno = false;
        Connection con = Dao.getInstance().getConnection();
        String sql = "delete from jogos where id = ?";

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
    
    
    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    
    
    
    
    
}
