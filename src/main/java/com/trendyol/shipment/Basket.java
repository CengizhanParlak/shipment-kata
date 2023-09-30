package com.trendyol.shipment;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Basket {

    private static final int MAX_BASKET_SIZE = 5;
    private static final int MIN_BASKET_SIZE_TO_CALCULATE_FURTHER = 3;
    private List<Product> products;

    public ShipmentSize getShipmentSize() throws NoProductsOnBasket {
        if (isBasketEmpty()) throw new NoProductsOnBasket();
        if (isBasketSizeMoreThanMAX()) return ShipmentSize.X_LARGE;
        return getLargestShipmentSize();
    }

    private ShipmentSize getLargestShipmentSize() {
        ShipmentSize largestSize = ShipmentSize.SMALL;
        EnumMap<ShipmentSize, Integer> occurredSizes = new EnumMap<>(ShipmentSize.class);

        for (Product product : products) {
            if (product.getSize().isBiggerThan(largestSize)) {
                largestSize = product.getSize();
            }

            occurredSizes.compute(product.getSize(), (key, count) -> (count == null) ? 1 : count + 1);
        }

        return isBasketSizeLessThanMinThreshold()
                ? largestSize : getMostOccurredOrLargestSize(occurredSizes, largestSize);
    }

    private ShipmentSize getMostOccurredOrLargestSize(EnumMap<ShipmentSize, Integer> map, ShipmentSize largestSize){
        for (Map.Entry<ShipmentSize, Integer> entry : map.entrySet()) {
            if (isSizeOccurredMoreThanThreshold(entry.getValue())){
                return entry.getKey().getOneBiggerSize();
            }
        }
        return largestSize;
    }

    private boolean isSizeOccurredMoreThanThreshold(int occurrence) {
        return occurrence >= ShipmentSize.OCCURRENCE_THRESHOLD;
    }

    private boolean isBasketEmpty() {
        return products.isEmpty();
    }

    private boolean isBasketSizeMoreThanMAX() {
        return products.size() > MAX_BASKET_SIZE;
    }

    private boolean isBasketSizeLessThanMinThreshold() {
        return products.size() < MIN_BASKET_SIZE_TO_CALCULATE_FURTHER;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
