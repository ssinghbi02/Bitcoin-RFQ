package com.cs.rfq.service;

import com.cs.rfq.model.Direction;
import com.cs.rfq.model.Order;
import com.cs.rfq.model.Quote;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

/**
 * Created by ssinghbi02 on 24/05/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class RfqServiceImplTest {

    public static final String TEST_CURRENCY = "USD";

    @Mock
    private LiveOrderBoard liveOrderBoard;

    @Before
    public void setUp() {
        when(liveOrderBoard.ordersFor(TEST_CURRENCY)).thenReturn(getOrders());
    }


    @Test
    public void testQuoteForWhenBothBuyAndSellMatchingClientOfferAvailable() {
        RfqService rfqService = new RfqServiceImpl(liveOrderBoard);
        Quote quote = rfqService.quoteFor(TEST_CURRENCY, 200).get();
        Assert.assertTrue(quote.bid == 232.69);
        Assert.assertTrue(quote.ask == 232.75);
    }

    @Test
    public void testQuoteForWhenOnlyBidMatchingClientOfferAvailable() {
        RfqService rfqService = new RfqServiceImpl(liveOrderBoard);
        Optional<Quote> quote = rfqService.quoteFor(TEST_CURRENCY, 500);
        Assert.assertTrue(!quote.isPresent());
    }


    @Test
    public void testQuoteForWhenOnlyAskMatchingClientOfferAvailable() {
        RfqService rfqService = new RfqServiceImpl(liveOrderBoard);
        Optional<Quote> quote = rfqService.quoteFor(TEST_CURRENCY, 300);
        Assert.assertTrue(!quote.isPresent());
    }

    @Test
    public void testQuoteForWhenNoMatchingClientOfferAvailable() {
        RfqService rfqService = new RfqServiceImpl(liveOrderBoard);
        Optional<Quote> quote = rfqService.quoteFor(TEST_CURRENCY, 123);
        Assert.assertTrue(!quote.isPresent());
    }


    private List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(Direction.BUY, 232.71, TEST_CURRENCY, 200));
        orders.add(new Order(Direction.SELL, 232.74, TEST_CURRENCY, 100));
        orders.add(new Order(Direction.SELL, 232.73, TEST_CURRENCY, 200));
        orders.add(new Order(Direction.BUY, 232.71, TEST_CURRENCY, 500));
        orders.add(new Order(Direction.BUY, 232.70, TEST_CURRENCY, 100));
        orders.add(new Order(Direction.SELL, 232.75, TEST_CURRENCY, 200));
        orders.add(new Order(Direction.BUY, 232.69, TEST_CURRENCY, 500));
        orders.add(new Order(Direction.SELL, 232.76, TEST_CURRENCY, 300));
        orders.add(new Order(Direction.BUY, 232.70, TEST_CURRENCY, 200));
        return orders;
    }
}
