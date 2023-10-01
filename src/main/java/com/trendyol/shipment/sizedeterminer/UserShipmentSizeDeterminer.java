package com.trendyol.shipment.sizedeterminer;

import com.trendyol.exceptions.shipment.NoProductOnBasketException;
import com.trendyol.shipment.Product;
import com.trendyol.shipment.ShipmentSize;

import java.util.EnumMap;
import java.util.Map;

public class UserShipmentSizeDeterminer extends BaseShipmentSizeDeterminer {
    private static final int MAX_BASKET_SIZE = 5;
    private static final int MIN_BASKET_SIZE_TO_CALCULATE_FURTHER = 3;

    private static final int OCCURRENCE_THRESHOLD = 3;
    @Override
    public ShipmentSize getShipmentSize() throws NoProductOnBasketException {
        if (isBasketEmpty()) throw new NoProductOnBasketException();
        if (isBasketSizeMoreThanMAX()) return ShipmentSize.X_LARGE;
        return determineShipmentSize();
    }

    @Override
    public ShipmentSize determineShipmentSize() {
        ShipmentSize largestSize = ShipmentSize.SMALL;
        EnumMap<ShipmentSize, Integer> occurredSizes = new EnumMap<>(ShipmentSize.class);

        for (Product product : getProducts()) {
            if (product.getSize().isBiggerThan(largestSize)) {
                largestSize = product.getSize();
            }

            occurredSizes.compute(product.getSize(), (key, count) -> (count == null) ? 1 : count + 1);
        }

        return isBasketSizeLessThanMinThreshold()
                ? largestSize : getMostOccurredOrLargestSize(occurredSizes, largestSize);
    }

    private ShipmentSize getMostOccurredOrLargestSize(
            EnumMap<ShipmentSize, Integer> map,
            ShipmentSize largestSize
    ){
        for (Map.Entry<ShipmentSize, Integer> entry : map.entrySet()) {
            if (isSizeOccurredMoreThanThreshold(entry.getValue())){
                return entry.getKey().getOneBiggerSize();
            }
        }
        return largestSize;
    }

    boolean isBasketSizeMoreThanMAX() {
        return getProducts().size() > MAX_BASKET_SIZE;
    }

    boolean isBasketSizeLessThanMinThreshold() {
        return getProducts().size() < MIN_BASKET_SIZE_TO_CALCULATE_FURTHER;
    }

    boolean isSizeOccurredMoreThanThreshold(int occurrence) {
        return occurrence >= OCCURRENCE_THRESHOLD;
    }
}
