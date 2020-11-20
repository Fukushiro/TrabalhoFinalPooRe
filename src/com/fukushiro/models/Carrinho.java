/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukushiro.models;

import com.fukushiro.interfaces.ICompravel;
import java.util.ArrayList;

/**
 *
 * @author jpflc
 */
public class Carrinho {

    private ArrayList<ICompravel> produtos;
    private Usuario owner;

    public Carrinho(Usuario owner) {
        this.produtos = new ArrayList<ICompravel>();
        this.owner = owner;
    }

    public boolean comprar() {
        for (ICompravel p : produtos) {
            if (!p.checkComprar(owner)) {
                System.out.println("Carrinho-comprar : Falhou");
                return false;
            }

        }

        for (ICompravel p : produtos) {
            p.comprar(owner);
        }
        esvaziarCarrinho();
        return true;
    }

    public void addItem(ICompravel produto) {
        this.produtos.add(produto);
    }

    public void esvaziarCarrinho() {
        this.produtos = new ArrayList<ICompravel>();
    }

    public ArrayList<ICompravel> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<ICompravel> produtos) {
        this.produtos = produtos;
    }

    public Usuario getOwner() {
        return owner;
    }

    public void setOwner(Usuario owner) {
        this.owner = owner;
    }

}
