package com.dorcas.catalogo.tela;

public record VentaOutputDto(
        Long id, Long telaId, double metros, double importe, double stockRestante) {


    public static VentaOutputDto desde(Tela tela, Venta venta){
        return new VentaOutputDto(
                venta.getId(),
                tela.getId(),
                venta.metros(),
                tela.getStockMetros(),
                tela.getPrecioMetro()
        );
    }
}
