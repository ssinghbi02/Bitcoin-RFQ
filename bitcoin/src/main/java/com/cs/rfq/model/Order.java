package com.cs.rfq.model;

import com.cs.rfq.model.Direction;

/**
 * Model to represent live client order.
 */
public class Order {
    private final Direction direction;
    private final double price;
    private final String currency;
    private final int amount;

    public Order(Direction direction, double price, String currency, int amount) {
        this.direction = direction;
        this.price = price;
        this.currency = currency;
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public int getAmount() {
        return amount;
    }

    public Direction getDirection() {
        return direction;
    }
}