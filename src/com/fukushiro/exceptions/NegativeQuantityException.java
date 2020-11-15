/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukushiro.exceptions;

/**
 *
 * @author jpflc
 */
public class NegativeQuantityException extends RuntimeException{

    public NegativeQuantityException() {
        super("Quantidade negativa não suportada");
    }

    
    public NegativeQuantityException(String string) {
        super(string);
    }
    
}
