package com.cs.rfq.service;

import com.cs.rfq.model.Order;

import java.util.List;

/**
 * Created by ssinghbi02 on 24/05/2016.
 */
public interface LiveOrderBoard {
    List<Order> ordersFor(String currency);
}
