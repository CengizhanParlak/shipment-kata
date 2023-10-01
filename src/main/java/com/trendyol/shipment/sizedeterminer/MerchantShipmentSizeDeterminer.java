package com.trendyol.shipment.sizedeterminer;

import com.trendyol.exceptions.shipment.NoProductOnBasketException;
import com.trendyol.shipment.ShipmentSize;

public class MerchantShipmentSizeDeterminer extends BaseShipmentSizeDeterminer {
    private final boolean isMerchantForMoreThan2Years;
    private final boolean isInTop100;
    
    public MerchantShipmentSizeDeterminer(
        boolean isMerchantForMoreThanTwoYears,
        boolean isInTop100
    ) {
        this.isMerchantForMoreThan2Years = isMerchantForMoreThanTwoYears;
        this.isInTop100 = isInTop100;
    }
    @Override
    public ShipmentSize getShipmentSize() throws NoProductOnBasketException {
        if (isBasketEmpty()) throw new NoProductOnBasketException();
        return determineShipmentSize();
    }

    @Override
    public ShipmentSize determineShipmentSize() {
        if (isInTop100) return ShipmentSize.SMALL;
        if (isMerchantForMoreThan2Years) return ShipmentSize.MEDIUM;
        return ShipmentSize.LARGE;
    }
}
