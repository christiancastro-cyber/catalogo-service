package com.dorcas.catalog.fabric.infrastructure.persistence;

import com.dorcas.catalog.fabric.domain.Category;
import com.dorcas.catalog.fabric.domain.Fabric;
import com.dorcas.catalog.fabric.domain.FabricRepository;
import com.dorcas.catalog.fabric.domain.Sale;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class FabricRepositoryJpa implements FabricRepository {

    private final FabricJpaRepository fabrics;
    private final SaleJpaRepository sales;

    FabricRepositoryJpa(FabricJpaRepository fabrics, SaleJpaRepository sales) {
        this.fabrics = fabrics;
        this.sales = sales;
    }

    @Override
    public List<Fabric> search(Category category, Boolean beginnerFriendly) {
        return fabrics.search(category, beginnerFriendly);
    }

    @Override
    public Optional<Fabric> findById(Long id) {
        return fabrics.findById(id);
    }

    @Override
    public boolean existsByReference(String reference) {
        return fabrics.existsByReferenceIgnoreCase(reference);
    }

    @Override
    public boolean existsByReferenceInAnotherFabric(String reference, Long id) {
        return fabrics.existsByReferenceIgnoreCaseAndIdNot(reference, id);
    }

    @Override
    public Fabric save(Fabric fabric) {
        return fabrics.save(fabric);
    }

    @Override
    public void delete(Fabric fabric) {
        fabrics.delete(fabric);
    }

    @Override
    public List<Fabric> findWithStockBelow(double minimum) {
        return fabrics.findByStockMetersLessThanOrderByStockMeters(minimum);
    }

    @Override
    public Sale saveSale(Sale sale) {
        return sales.save(sale);
    }

    @Override
    public boolean hasSales(Long fabricId) {
        return sales.existsByFabricId(fabricId);
    }

    @Override
    public List<Sale> findSales(Long fabricId) {
        return sales.findByFabricIdOrderByIdAsc(fabricId);
    }

    @Override
    public double sumMetersSold(Long fabricId) {
        return sales.sumMetersSold(fabricId);
    }
}
