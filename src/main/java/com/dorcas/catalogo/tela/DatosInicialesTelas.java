package com.dorcas.catalogo.tela;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DatosInicialesTelas {

    @Bean
    CommandLineRunner cargarTelas(TelaRepository telaRepository) {

        return args -> {
            if (telaRepository.count() > 0) {
                return;
            }
            telaRepository.saveAll(List.of(
                new Tela("ALG-01", "Algodón", Categoria.INFANTIL, 8.50, 15.0, true),
                new Tela("LON-01", "Loneta Lisa", Categoria.HOGAR, 11.0, 6, true),
                new Tela("LON-02", "Loneta estampada", Categoria.HOGAR, 9.30, 5, true),
                new Tela("LY-01", "Lycra", Categoria.MODA, 8.50, 15.5, false),
                new Tela("STR-01", "Stretch", Categoria.MODA, 4.5, 9, true),
                new Tela("ANT-01", "Antelina", Categoria.HOGAR, 6.0, 12.5, false),
                new Tela("LAM-01", "Lamé", Categoria.FIESTA, 5.0, 6.5, false),
                new Tela("COR-01", "Coralina", Categoria.ABRIGO, 11.50, 8, false)
            ));
        };

    }


}
