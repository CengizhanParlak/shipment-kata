package com.trendyol.exceptions.shipment;

public abstract class BaseShipmentException extends Exception {
    protected BaseShipmentException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
