package com.cs.rfq.service;

import com.cs.rfq.model.Quote;

import java.util.Optional;

public interface RfqService {
    Optional<Quote> quoteFor(String currency, int amount);
}
