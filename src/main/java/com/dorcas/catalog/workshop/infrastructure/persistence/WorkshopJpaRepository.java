package com.dorcas.catalog.workshop.infrastructure.persistence;

import com.dorcas.catalog.workshop.domain.Level;
import com.dorcas.catalog.workshop.domain.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface WorkshopJpaRepository extends JpaRepository<Workshop, Long> {

    List<Workshop> findByLevel(Level level);
}
