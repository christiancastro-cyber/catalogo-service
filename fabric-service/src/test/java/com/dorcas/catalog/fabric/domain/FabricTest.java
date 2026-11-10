package com.dorcas.catalog.fabric.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FabricTest {

    private Fabric lame() {
        return new Fabric("LAM-01", "Lamé", Category.PARTY, 5.0, 6.5, false);
    }

    @Test
    void sellingReducesTheStock() {
        Fabric fabric = lame();

        fabric.sell(2);

        assertThat(fabric.getStockMeters()).isEqualTo(4.5);
    }

    @Test
    void sellingMoreThanAvailableThrowsInsufficientStock() {
        Fabric fabric = lame();

        assertThatThrownBy(() -> fabric.sell(20))
                .isInstanceOf(InsufficientStockException.class)
                .hasMessageContaining("Lamé");
    }

    @Test
    void sellingNegativeMetersThrowsIllegalArgument() {
        Fabric fabric = lame();

        assertThatThrownBy(() -> fabric.sell(-2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void sellingMoreThanAvailableDoesNotChangeTheStock() {
        Fabric fabric = lame();

        assertThatThrownBy(() -> fabric.sell(20))
                .isInstanceOf(InsufficientStockException.class);

        assertThat(fabric.getStockMeters()).isEqualTo(6.5);
    }

    @Test
    void fromFiveMetersATenPercentDiscountApplies() {
        Fabric fabric = lame();

        double price = fabric.priceFor(5);

        assertThat(price).isCloseTo(22.5, within(0.001));
    }
}
