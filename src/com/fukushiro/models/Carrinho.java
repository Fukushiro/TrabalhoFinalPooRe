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
                return false;
            }

        }

        for (ICompravel p : produtos) {
            p.comprar(owner);
            String tipo = "produto";
            Compra c = new Compra(0, owner, tipo, ((Produto) p).getNome(), ((Produto) p).getQuantidade() * ((Produto) p).getPreco());
            c.save();
        }
        esvaziarCarrinho();
        return true;
    }

    public void addItem(ICompravel produto) {
        this.produtos.add(produto);
    }

    public boolean remover(ICompravel i2) {
        Produto p2 = (Produto) i2;
        for (ICompravel i : produtos) {
            Produto p = (Produto) i;
            if((p.getId() == p2.getId()) || (p.getQuantidade() == p2.getQuantidade())){
                produtos.remove(i);
                return true;
            }
        }

        return false;
    }

    public void remover(int pos){
        for(int i = 0; i < this.produtos.size(); i++){
            if(i == pos){
                this.produtos.remove(i);
            }
        }
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
