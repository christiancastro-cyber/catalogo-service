package com.dorcas.catalog.workshop.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class KitDetailsTest {

    @Test
    void kitIsInStockWhenTheFabricHasEnoughMeters() {
        Kit kit = new Kit(1L, 2.0);
        FabricInfo cotton = new FabricInfo(1L, "Algodón", 8.5, 15.0);

        KitDetails details = KitDetails.of(kit, cotton);

        assertThat(details.inStock()).isTrue();
    }

    @Test
    void kitIsNotInStockWhenTheFabricHasFewerMetersThanTheKit() {
        Kit kit = new Kit(1L, 2.0);
        FabricInfo almostSoldOut = new FabricInfo(1L, "Algodón", 8.5, 1.5);

        KitDetails details = KitDetails.of(kit, almostSoldOut);

        assertThat(details.inStock()).isFalse();
    }
}