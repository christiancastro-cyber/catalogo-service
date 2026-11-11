package com.dorcas.catalog.workshop.infrastructure.client;

import com.dorcas.catalog.workshop.domain.FabricCatalog;
import com.dorcas.catalog.workshop.domain.FabricCatalogUnavailableException;
import com.dorcas.catalog.workshop.domain.FabricInfo;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class FabricCatalogFeign implements FabricCatalog {

    private static final Logger log = LoggerFactory.getLogger(FabricCatalogFeign.class);

    private final FabricServiceClient client;
    private final CircuitBreaker circuitBreaker;

    FabricCatalogFeign(FabricServiceClient client, CircuitBreakerFactory<?, ?> circuitBreakerFactory) {
        this.client = client;
        this.circuitBreaker = circuitBreakerFactory.create("fabric-service");
    }

    @Override
    public Optional<FabricInfo> findFabric(Long fabricId) {
        return circuitBreaker.run(
                () -> askFabricService(fabricId),
                error -> {
                    log.warn("fabric-service unavailable: {}", error.toString());
                    throw new FabricCatalogUnavailableException();
                });
    }

    private Optional<FabricInfo> askFabricService(Long fabricId) {
        try {
            FabricResponse fabric = client.getFabric(fabricId);
            return Optional.of(new FabricInfo(fabric.id(), fabric.name(),
                    fabric.pricePerMeter(), fabric.stockMeters()));
        } catch (FeignException.NotFound e) {
            return Optional.empty();
        }
    }
}