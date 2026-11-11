package com.dorcas.catalog.workshop.infrastructure.web;

import com.dorcas.catalog.workshop.domain.FabricCatalog;
import com.dorcas.catalog.workshop.domain.FabricCatalogUnavailableException;
import com.dorcas.catalog.workshop.domain.FabricInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class KitApiTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    FabricCatalog fabricCatalog;

    @Test
    void kitShowsTheFabricDataFromTheCatalog() throws Exception {
        when(fabricCatalog.findFabric(1L))
                .thenReturn(Optional.of(new FabricInfo(1L, "Algodón", 8.5, 15.0)));

        mockMvc.perform(get("/api/workshops/1/kit"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fabricName").value("Algodón"))
                .andExpect(jsonPath("$.meters").value(2.0))
                .andExpect(jsonPath("$.pricePerMeter").value(8.5))
                .andExpect(jsonPath("$.inStock").value(true));
    }

    @Test
    void workshopWithoutKitReturns404() throws Exception {
        mockMvc.perform(get("/api/workshops/4/kit"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("KIT_NOT_FOUND"));
    }

    @Test
    void kitWhoseFabricNoLongerExistsReturns409() throws Exception {
        when(fabricCatalog.findFabric(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/workshops/1/kit"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code").value("KIT_FABRIC_NOT_FOUND"));
    }

    @Test
    void kitWhenTheFabricCatalogIsDownReturns503() throws Exception {
        when(fabricCatalog.findFabric(1L)).thenThrow(new FabricCatalogUnavailableException());

        mockMvc.perform(get("/api/workshops/1/kit"))
                .andExpect(status().isServiceUnavailable())
                .andExpect(jsonPath("$.code").value("FABRIC_CATALOG_UNAVAILABLE"));
    }
}