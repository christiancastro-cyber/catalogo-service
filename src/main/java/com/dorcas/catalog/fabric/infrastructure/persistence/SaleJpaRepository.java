package com.dorcas.catalog.fabric.infrastructure.persistence;

import com.dorcas.catalog.fabric.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface SaleJpaRepository extends JpaRepository<Sale, Long> {

    List<Sale> findByFabricIdOrderByIdAsc(Long fabricId);

    boolean existsByFabricId(Long fabricId);

    @Query("select coalesce(sum(s.meters), 0.0) from Sale s where s.fabric.id = :fabricId")
    double sumMetersSold(Long fabricId);
}
