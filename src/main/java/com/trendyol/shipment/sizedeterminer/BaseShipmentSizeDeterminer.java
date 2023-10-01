package com.trendyol.shipment.sizedeterminer;

import com.trendyol.exceptions.shipment.BaseShipmentException;
import com.trendyol.shipment.Product;
import com.trendyol.shipment.ShipmentSize;

import java.util.List;

public abstract class BaseShipmentSizeDeterminer {
    private List<Product> products;

    public abstract ShipmentSize getShipmentSize() throws BaseShipmentException;

    public abstract ShipmentSize determineShipmentSize();
    boolean isBasketEmpty() {
        return products.isEmpty();
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
