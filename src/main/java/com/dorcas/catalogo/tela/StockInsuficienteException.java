package com.dorcas.catalogo.tela;

public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(String tela, double pedidos, double disponibles){
        super("No hay suficiente "+ tela + ": piden " + pedidos + " m y me quedan " + disponibles + " m");
    }
}
