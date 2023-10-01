package com.trendyol.exceptions.shipment;

public class NoProductOnBasketException extends BaseShipmentException {
    private static final String MESSAGE = "There is no product in basket to continue the process";
    public NoProductOnBasketException() {
        super(MESSAGE);
    }
}
