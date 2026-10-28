package com.dorcas.catalogo.tela;

public record VentaOutputDto(
        Long id, Long telaId, double metros, double importe, double stockRestante) {

    public static VentaOutputDto desde(Venta venta){
        return new VentaOutputDto(
                venta.getId(),
                venta.getTela().getId(),
                venta.getMetros(),
                venta.getImporte(),
                venta.getStockRestante()
        );
    }
}
