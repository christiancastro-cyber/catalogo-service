package com.dorcas.catalog.fabric.infrastructure.persistence;

import com.dorcas.catalog.fabric.domain.Fabric;
import com.dorcas.catalog.fabric.domain.Sale;

final class FabricMapper {

    private FabricMapper(){}

    static FabricEntity toEntity(Fabric fabric){
        return new FabricEntity(fabric.getId(), fabric.getReference(), fabric.getName(),
                fabric.getCategory(), fabric.getPricePerMeter(), fabric.getStockMeters(), fabric.isBeginnerFriendly());
    }

    static Fabric toDomain(FabricEntity entity){
        return new Fabric(entity.getId(), entity.getReference(), entity.getName(),
                entity.getCategory(), entity.getPricePerMeter(), entity.getStockMeters(),
                entity.isBeginnerFriendly());
    }

    static SaleEntity toEntity(Sale sale){
        return new SaleEntity(sale.getId(), sale.getFabricId(), sale.getMeters(),
                sale.getAmount(), sale.getRemainingStock());
    }

    static Sale toDomain(SaleEntity entity){
        return new Sale(entity.getId(), entity.getFabricId(), entity.getMeters(),
                entity.getAmount(), entity.getRemainingStock());
    }



}
