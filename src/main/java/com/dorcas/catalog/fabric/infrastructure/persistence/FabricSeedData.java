package com.dorcas.catalog.fabric.infrastructure.persistence;

import com.dorcas.catalog.fabric.domain.Category;
import com.dorcas.catalog.fabric.domain.Fabric;
import com.dorcas.catalog.fabric.domain.FabricRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
class FabricSeedData {

    @Bean
    CommandLineRunner loadFabrics(FabricRepository fabrics) {
        return args -> {
            if (!fabrics.search(null, null).isEmpty()) {
                return;
            }
            List.of(
                    new Fabric("ALG-01", "Algodón", Category.KIDS, 8.50, 15.0, true),
                    new Fabric("LON-01", "Loneta lisa", Category.HOME, 11.0, 6, true),
                    new Fabric("LON-02", "Loneta estampada", Category.HOME, 9.30, 5, true),
                    new Fabric("LY-01", "Lycra", Category.FASHION, 8.50, 15.5, false),
                    new Fabric("STR-01", "Stretch", Category.FASHION, 4.5, 9, true),
                    new Fabric("ANT-01", "Antelina", Category.HOME, 6.0, 12.5, false),
                    new Fabric("LAM-01", "Lamé", Category.PARTY, 5.0, 6.5, false),
                    new Fabric("COR-01", "Coralina", Category.OUTERWEAR, 11.50, 8, false)
            ).forEach(fabrics::save);
        };
    }
}
