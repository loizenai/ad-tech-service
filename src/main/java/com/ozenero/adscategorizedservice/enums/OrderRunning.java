package com.ozenero.adscategorizedservice.enums;

public enum OrderRunning {
    FIRST(1),
    SECOND(2);

    private final int value;
    private OrderRunning(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
