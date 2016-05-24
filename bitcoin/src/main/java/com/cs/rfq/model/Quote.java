package com.cs.rfq.model;

/**
 * Model to represent Quote.
 */
public class Quote {
    public final double bid;

    public final double ask;

    public Quote(final double bid, final double ask) {
        this.bid = bid;
        this.ask = ask;
    }
}
