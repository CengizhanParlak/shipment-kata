package com.trendyol.shipment;

public class NoProductsOnBasket extends Throwable {
    private static final String EXCEPTION_MESSAGE = "No Products on basket to perform the process.";
        public NoProductsOnBasket() {
            super(EXCEPTION_MESSAGE);
        }
}
