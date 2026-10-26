package com.dorcas.catalogo.tela;

import com.dorcas.catalogo.compartido.ConflictoException;

public class StockInsuficienteException extends ConflictoException {
    public StockInsuficienteException(String tela, double pedidos, double disponibles){
        super("STOCK_INSUFICIENTE","No hay suficiente "+ tela + ": piden " + pedidos + " m y me quedan " + disponibles + " m");
    }
}
