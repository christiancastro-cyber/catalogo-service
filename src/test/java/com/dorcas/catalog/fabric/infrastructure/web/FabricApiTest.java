package com.dorcas.catalog.fabric.infrastructure.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class FabricApiTest {

    @Autowired
    MockMvc mockMvc;

    private void sellTwoMetersOfFabricOne() throws Exception {
        mockMvc.perform(post("/api/fabrics/1/sales")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"meters": 2}
                                """))
                .andExpect(status().isCreated());
    }

    @Test
    void listReturnsTheEightFabrics() throws Exception {
        mockMvc.perform(get("/api/fabrics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(8));
    }

    @Test
    void filteringByCategoryReturnsOnlyThatCategory() throws Exception {
        mockMvc.perform(get("/api/fabrics").param("category", "HOME"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[*].category", everyItem(is("HOME"))));
    }

    @Test
    void gettingAFabricThatDoesNotExistReturns404() throws Exception {
        mockMvc.perform(get("/api/fabrics/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("FABRIC_NOT_FOUND"));
    }

    @Test
    void creatingAFabricReturns201WithLocation() throws Exception {
        String body = """
                {
                  "reference": "MUS-02",
                  "name": "Muselina estampada",
                  "category": "KIDS",
                  "pricePerMeter": 8.50,
                  "stockMeters": 25,
                  "beginnerFriendly": true
                }
                """;

        mockMvc.perform(post("/api/fabrics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.reference").value("MUS-02"))
                .andExpect(jsonPath("$.id").isNumber());
    }

    @Test
    void creatingWithInvalidDataReturns400InvalidData() throws Exception {
        String body = """
                {
                  "reference": "MUS-02",
                  "name": "",
                  "category": "KIDS",
                  "pricePerMeter": -3,
                  "stockMeters": 25,
                  "beginnerFriendly": true
                }
                """;

        mockMvc.perform(post("/api/fabrics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("INVALID_DATA"))
                .andExpect(jsonPath("$.details.length()").value(2));
    }

    @Test
    void creatingWithValuesThatDoNotFitReturns400InvalidJson() throws Exception {
        String body = """
                {
                  "reference": "MUS-02",
                  "name": "Muselina estampada",
                  "category": "CURTAIN",
                  "pricePerMeter": "other",
                  "stockMeters": 25,
                  "beginnerFriendly": true
                }
                """;

        mockMvc.perform(post("/api/fabrics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("INVALID_JSON"));
    }

    @Test
    void creatingWithAnExistingReferenceReturns409() throws Exception {
        String body = """
                {
                  "reference": "ALG-01",
                  "name": "Muselina estampada",
                  "category": "KIDS",
                  "pricePerMeter": 3,
                  "stockMeters": 25,
                  "beginnerFriendly": true
                }
                """;

        mockMvc.perform(post("/api/fabrics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code").value("DUPLICATE_REFERENCE"));
    }

    @Test
    void sellingAddsTheMetersToTheTotalSold() throws Exception {
        sellTwoMetersOfFabricOne();

        mockMvc.perform(get("/api/fabrics/1/sales/total"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.metersSold").value(2.0));
    }

    @Test
    void deletingAFabricWithSalesReturns409() throws Exception {
        sellTwoMetersOfFabricOne();

        mockMvc.perform(delete("/api/fabrics/1"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code").value("FABRIC_HAS_SALES"));
    }
}
