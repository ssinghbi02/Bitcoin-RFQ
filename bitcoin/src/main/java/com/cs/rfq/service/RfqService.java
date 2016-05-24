package com.cs.rfq.service;

import com.cs.rfq.model.Quote;

import java.util.Optional;

/**
 * Rfq service provides quote for bitcoin.
 */
public interface RfqService {

    /**
     *
     * @param currency quote currency
     * @param amount notional
     * @return {@code Quote}
     */
    Optional<Quote> quoteFor(String currency, int amount);
}
