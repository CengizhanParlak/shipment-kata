package com.trendyol.shipment;

public enum ShipmentSize {

    SMALL(0),
    MEDIUM(1),
    LARGE(2),
    X_LARGE(3);

    public final int asInt;

    ShipmentSize(int asInt) {
        this.asInt = asInt;
    }

    static final int OCCURRENCE_THRESHOLD = 3;

    public boolean isBiggerThan(ShipmentSize comparedSize) {
        return this.asInt > comparedSize.asInt;
    }
    public ShipmentSize getOneBiggerSize() {
        int nextSizeIndex = getOneBiggerAsInt(this) % ShipmentSize.values().length;
        return ShipmentSize.values()[nextSizeIndex];
    }

    private int getOneBiggerAsInt(ShipmentSize size) {
        boolean isBiggestSize = size.asInt == ShipmentSize.values().length - 1;
        return isBiggestSize ? size.asInt : size.asInt + 1;
    }
}
