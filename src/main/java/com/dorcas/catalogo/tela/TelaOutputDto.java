package com.dorcas.catalogo.tela;

public record TelaOutputDto(Long id,
                            String referencia,
                            String nombre,
                            Categoria categoria,
                            double precioMetro,
                            double stockMetros,
                            boolean aptaPrincipiantes
) {

    public static TelaOutputDto desde(Tela tela){
        return new TelaOutputDto(
                tela.getId(),
                tela.getReferencia(),
                tela.getNombre(),
                tela.getCategoria(),
                tela.getPrecioMetro(),
                tela.getStockMetros(),
                tela.esAptaPrincipiantes()
        );
    }

}
