package com.dorcas.catalog.workshop.infrastructure.client;

import com.dorcas.catalog.workshop.domain.FabricCatalog;
import com.dorcas.catalog.workshop.domain.FabricInfo;
import feign.FeignException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class FabricCatalogFeign implements FabricCatalog {

    private final FabricServiceClient client;

    FabricCatalogFeign(FabricServiceClient client) {
        this.client = client;
    }

    @Override
    public Optional<FabricInfo> findFabric(Long fabricId) {
        try {
            FabricResponse fabric = client.getFabric(fabricId);
            return Optional.of(new FabricInfo(fabric.id(), fabric.name(),
                    fabric.pricePerMeter(), fabric.stockMeters()));
        } catch (FeignException.NotFound e) {
            return Optional.empty();
        }
    }
}