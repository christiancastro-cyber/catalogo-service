package com.dorcas.catalog.workshop.infrastructure.persistence;

import com.dorcas.catalog.workshop.domain.Level;
import com.dorcas.catalog.workshop.domain.Workshop;
import com.dorcas.catalog.workshop.domain.WorkshopRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
class WorkshopSeedData {

    @Bean
    CommandLineRunner loadWorkshops(WorkshopRepository workshops) {
        return args -> {
            if (!workshops.findAll().isEmpty()) {
                return;
            }
            List.of(
                    new Workshop("Taller de Canastilla", Level.BEGINNER, 8, 45.0),
                    new Workshop("Patronaje Básico", Level.BEGINNER, 5, 50.0),
                    new Workshop("Costura Básica", Level.BEGINNER, 5, 55.0),
                    new Workshop("Corsetería", Level.INTERMEDIATE, 2, 60.0)
            ).forEach(workshops::save);
        };
    }
}
