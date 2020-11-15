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
public class DepositarNegativoException extends RuntimeException{

    public DepositarNegativoException() {
        super("O valor que tentou depositar é negativo");
    }

    public DepositarNegativoException(String string) {
        super(string);
    }
    
}
