package com.trendyol.shipment;

import com.trendyol.exceptions.shipment.BaseShipmentException;
import com.trendyol.shipment.sizedeterminer.BaseShipmentSizeDeterminer;

import java.util.List;

public class Basket {
    private List<Product> products;

    BaseShipmentSizeDeterminer shipmentSizeDeterminer;

    public Basket(BaseShipmentSizeDeterminer shipmentSizeDeterminer) {
        this.shipmentSizeDeterminer = shipmentSizeDeterminer;
    }

    public ShipmentSize getShipmentSize() throws BaseShipmentException {
        return shipmentSizeDeterminer.getShipmentSize();
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        shipmentSizeDeterminer.setProducts(products);
    }

    List<Product> getProducts() {
        return products;
    }
}
