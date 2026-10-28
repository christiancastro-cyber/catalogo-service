package com.dorcas.catalogo.taller;

import com.dorcas.catalogo.tela.Categoria;
import com.dorcas.catalogo.tela.Tela;
import com.dorcas.catalogo.tela.TelaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
class DatosInicialesTalleres {
    @Bean
    CommandLineRunner cargarTelas(TelaRepository telaRepository, TallerRepository tallerRepository) {

        return args -> {
            if (tallerRepository.count() > 0) {
                return;
            }
            tallerRepository.saveAll(List.of(
                new Taller("Taller de Canastilla",Nivel.INICIACION,8,45.0),
                new Taller("Patronaje Básico", Nivel.INICIACION, 5, 50.0),
                new Taller("Costura Básica", Nivel.INICIACION, 5, 55.0)
            ));
        };

    }
}
