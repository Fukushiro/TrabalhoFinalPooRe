/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukushiro.models;

/**
 *
 * @author jpflc
 */
public class Singleton {
    private static Singleton instance;
    
    private Usuario usuarioLogado = null;
    
    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        
        return instance;
    }
    
    

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
    
    
    
}
