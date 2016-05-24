package com.cs.rfq.service;

import com.cs.rfq.model.Order;

import java.util.List;

/**
 * Interface for Live client orders.
 */
public interface LiveOrderBoard {

    /**
     *
     * @param currency currency
     * @return {@link Order}
     */
    List<Order> ordersFor(final String currency);
}
