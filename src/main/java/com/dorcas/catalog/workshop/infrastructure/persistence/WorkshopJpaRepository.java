package com.dorcas.catalog.workshop.infrastructure.persistence;

import com.dorcas.catalog.workshop.domain.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface WorkshopJpaRepository extends JpaRepository<WorkshopEntity, Long> {

    List<WorkshopEntity> findByLevel(Level level);
}
