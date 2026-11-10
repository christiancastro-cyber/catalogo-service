package com.dorcas.catalog.workshop.infrastructure.persistence;

import com.dorcas.catalog.workshop.domain.Level;
import com.dorcas.catalog.workshop.domain.Workshop;
import com.dorcas.catalog.workshop.domain.WorkshopRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
class WorkshopRepositoryJpa implements WorkshopRepository {

    private final WorkshopJpaRepository workshops;

    WorkshopRepositoryJpa(WorkshopJpaRepository workshops) {
        this.workshops = workshops;
    }

    @Override
    public List<Workshop> findAll() {
        return workshops.findAll().stream()
                .map(WorkshopMapper::toDomain)
                .toList();
    }

    @Override
    public List<Workshop> findByLevel(Level level) {
        return workshops.findByLevel(level).stream()
                .map(WorkshopMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Workshop> findById(Long id) {
        return workshops.findById(id)
                .map(WorkshopMapper::toDomain);
    }

    @Override
    public Workshop save(Workshop workshop) {
        WorkshopEntity entity;
        if (workshop.getId() == null) {
            entity = WorkshopMapper.toNewEntity(workshop);
        } else {
            entity = workshops.findById(workshop.getId()).orElseThrow();
            WorkshopMapper.copyBookings(workshop, entity);
        }
        WorkshopEntity saved = workshops.saveAndFlush(entity);
        return WorkshopMapper.toDomain(saved);
    }
}
