package com.market.fishmarket.infostructure.exception.errors;

public class FishNotFoundException extends RuntimeException {
    public FishNotFoundException(String message) {
        super(message);
    }
}
