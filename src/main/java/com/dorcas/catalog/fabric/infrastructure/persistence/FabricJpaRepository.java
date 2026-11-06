package com.dorcas.catalog.fabric.infrastructure.persistence;

import com.dorcas.catalog.fabric.domain.Category;
import com.dorcas.catalog.fabric.domain.Fabric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface FabricJpaRepository extends JpaRepository<FabricEntity, Long> {

    boolean existsByReferenceIgnoreCase(String reference);

    boolean existsByReferenceIgnoreCaseAndIdNot(String reference, Long id);

    @Query("""
            select f from FabricEntity f
            where (:category is null or f.category = :category)
              and (:beginnerFriendly is null or f.beginnerFriendly = :beginnerFriendly)
            """)
    List<FabricEntity> search(Category category, Boolean beginnerFriendly);

    List<FabricEntity> findByStockMetersLessThanOrderByStockMeters(double minimum);
}
