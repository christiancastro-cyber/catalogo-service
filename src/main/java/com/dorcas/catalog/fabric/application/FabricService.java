package com.dorcas.catalog.fabric.application;

import com.dorcas.catalog.fabric.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FabricService {

    private final FabricRepository repository;

    public FabricService(FabricRepository repository) {
        this.repository = repository;
    }

    public List<Fabric> list(Category category, Boolean beginnerFriendly) {
        return repository.search(category, beginnerFriendly);
    }

    public Fabric findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new FabricNotFoundException(id));
    }

    public Fabric create(Fabric fabric) {
        if (repository.existsByReference(fabric.getReference())) {
            throw new DuplicateReferenceException(fabric.getReference());
        }
        return repository.save(fabric);
    }

    @Transactional
    public Fabric update(Long id, Fabric data) {
        Fabric fabric = findById(id);
        if (repository.existsByReferenceInAnotherFabric(data.getReference(), id)) {
            throw new DuplicateReferenceException(data.getReference());
        }
        fabric.update(data.getReference(), data.getName(), data.getCategory(),
                data.getPricePerMeter(), data.getStockMeters(), data.isBeginnerFriendly());
        return repository.save(fabric);
    }

    @Transactional
    public Fabric changePrice(Long id, double newPrice) {
        Fabric fabric = findById(id);
        fabric.changePrice(newPrice);
        return repository.save(fabric);
    }

    @Transactional
    public void delete(Long id) {
        Fabric fabric = findById(id);
        if (repository.hasSales(id)) {
            throw new FabricHasSalesException(id);
        }
        repository.delete(fabric);
    }

    @Transactional
    public Sale sell(Long id, double meters) {
        Fabric fabric = findById(id);
        fabric.sell(meters);
        repository.save(fabric);
        double amount = fabric.priceFor(meters);
        return repository.saveSale(new Sale(fabric.getId(), meters, amount, fabric.getStockMeters()));
    }

    public List<Sale> listSales(Long fabricId) {
        findById(fabricId);
        return repository.findSales(fabricId);
    }

    public List<Fabric> restockList(double minimum) {
        return repository.findWithStockBelow(minimum);
    }

    public double metersSold(Long fabricId) {
        findById(fabricId);
        return repository.sumMetersSold(fabricId);
    }
}
