package com.dorcas.catalog.fabric.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface SaleJpaRepository extends JpaRepository<SaleEntity, Long> {

    List<SaleEntity> findByFabricIdOrderByIdAsc(Long fabricId);

    boolean existsByFabricId(Long fabricId);

    @Query("select coalesce(sum(s.meters), 0.0) from SaleEntity s where s.fabricId = :fabricId")
    double sumMetersSold(Long fabricId);
}
