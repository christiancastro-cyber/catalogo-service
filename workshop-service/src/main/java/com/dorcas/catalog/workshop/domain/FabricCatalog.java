package com.dorcas.catalog.workshop.domain;

import java.util.Optional;

public interface FabricCatalog {
    Optional<FabricInfo> findFabric(Long fabricId);
}
