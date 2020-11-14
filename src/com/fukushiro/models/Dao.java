/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukushiro.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jpflc
 */
public class Dao {
    
    private static Dao instance;
    private static Connection con;
    
    private String nome ;
    private String senha ;
    private String url ;

    public Dao(){
        this.nome = "root";
        this.senha = "senha";
        this.url =  "jdbc:mysql://localhost/trabalho_final_poo";
    }
    
    public Dao(String nome, String senha, String url) {
        this.nome = nome;
        this.senha = senha;
        this.url = url;
    }
    
    public static Dao getInstance(){
        if(instance == null){
            instance = new Dao();
        }
        
        return instance;
    }
    
    public Connection getConnection(){
        if(this.con == null){
            try {
                this.con = DriverManager.getConnection(this.url, this.nome, this.senha);
                
            } catch (SQLException ex) {
                 
            }
        }
        
        return this.con;
    }
    
    public boolean closeConnection(){
        if(con != null){
            try {
                con.close();
                con = null;
                return true;
            } catch (SQLException ex) {
                return false;
            }
            
        }
        return true;
    }
    
    
    
    
    
    
    
    
}
