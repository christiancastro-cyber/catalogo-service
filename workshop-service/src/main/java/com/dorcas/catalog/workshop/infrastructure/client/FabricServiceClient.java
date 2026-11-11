package com.dorcas.catalog.workshop.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "fabric-service")
interface FabricServiceClient {

    @GetMapping("/api/fabrics/{id}")
    FabricResponse getFabric(@PathVariable("id") Long id);
}