package com.cs.rfq.model;

/**
 * Created by ssinghbi02 on 24/05/2016.
 */
public class Quote {
    public final double bid;

    public final double ask;

    public Quote(final double bid, final double ask) {
        this.bid = bid;
        this.ask = ask;
    }
}
