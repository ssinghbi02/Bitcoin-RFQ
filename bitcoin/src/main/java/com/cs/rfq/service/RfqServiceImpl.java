package com.cs.rfq.service;

import com.cs.rfq.model.Direction;
import com.cs.rfq.model.Order;
import com.cs.rfq.model.Quote;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * {@inheritDoc}
 */
public class RfqServiceImpl implements RfqService {

    private final LiveOrderBoard liveOrderBoard;
    private static final double MARGIN = .02;

    public RfqServiceImpl(final LiveOrderBoard liveOrderBoard) {
        this.liveOrderBoard = liveOrderBoard;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Optional<Quote> quoteFor(final String currency, final int amount) {

        List<Order> orders = liveOrderBoard.ordersFor(currency);

        Optional<Order> bid = findMatchingClientOfferForDirection(amount, orders, Direction.BUY);
        Optional<Order> ask = findMatchingClientOfferForDirection(amount, orders, Direction.SELL);

        if (!bid.isPresent() || !ask.isPresent()) {
            return Optional.empty();
        }

        Quote quote = new Quote(bid.get().getPrice() - MARGIN, ask.get().getPrice() + MARGIN);

        return Optional.of(quote);
    }

    /**
     *
     * @param amount amount
     * @param orders client orders
     * @param direction buy/sell direction
     * @return {@link Order}
     */
    private Optional<Order> findMatchingClientOfferForDirection(int amount, List<Order> orders, Direction direction) {

        Comparator<Order> comparator = (o1, o2) ->
        {
            double diff = o1.getPrice() - o2.getPrice();
            return (diff > 0 ? 1 : (diff == 0 ? 0 : -1));
        };

        Stream<Order> matchingOrders = orders.stream()
                .filter(order -> order.getAmount() == amount)
                .filter(order -> order.getDirection() == direction);

        return direction == Direction.BUY ? matchingOrders.max(comparator) : matchingOrders.min(comparator);
    }

}
