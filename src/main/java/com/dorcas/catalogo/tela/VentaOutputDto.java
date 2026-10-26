package com.dorcas.catalogo.tela;

public record VentaOutputDto(
        Long id, Long telaId, double metros, double importe, double stockRestante) {
}
