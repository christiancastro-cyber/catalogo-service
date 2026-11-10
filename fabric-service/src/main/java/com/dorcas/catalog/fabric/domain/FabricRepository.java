package com.dorcas.catalog.fabric.domain;

import java.util.List;
import java.util.Optional;

public interface FabricRepository {

    List<Fabric> search(Category category, Boolean beginnerFriendly);

    Optional<Fabric> findById(Long id);

    boolean existsByReference(String reference);

    boolean existsByReferenceInAnotherFabric(String reference, Long id);

    Fabric save(Fabric fabric);

    void delete(Fabric fabric);

    List<Fabric> findWithStockBelow(double minimum);

    Sale saveSale(Sale sale);

    boolean hasSales(Long fabricId);

    List<Sale> findSales(Long fabricId);

    double sumMetersSold(Long fabricId);
}
