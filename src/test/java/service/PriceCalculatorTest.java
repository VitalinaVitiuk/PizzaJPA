package service;

import domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class PriceCalculatorTest {

    @Autowired
    @InjectMocks
    PriceCalculator priceCalculator;

    @Autowired
    @InjectMocks
    Order order;

//    @Mock
    User user;

    @Mock
    AccumulativeCard accumulativeCard;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    public void prepareTestRecalculatePriceZeroBalance() {
        Mockito.when(accumulativeCard.getBalance()).thenReturn(0d);
        user = new User();
        user.setAccumulativeCard(accumulativeCard);
        order.setUser(user);
        Pizza pizza = new Pizza();
        pizza.setPrice(200d);
        order.addItems(pizza);
    }

    @Test
    public void testRecalculatePriceWithZeroBalance() {
        prepareTestRecalculatePriceZeroBalance();
        Double price = priceCalculator.recalculatePrice(order);
        Double expectedPrice = 200d;
        assertEquals(expectedPrice, price);
    }

    public void prepareTestRecalculatePriceAddingPriceToAccumulativeCart() {
        AccumulativeCard t = new AccumulativeCard();
        t.setBalance(0d);
        user = new User();
        user.setAccumulativeCard(t);
        order.setUser(user);
        Pizza pizza = new Pizza();
        pizza.setPrice(200d);
        order.addItems(pizza);
    }

    @Test
    public void testRecalculatePriceWithAddingPriceToAccumulativeCart() {
        prepareTestRecalculatePriceAddingPriceToAccumulativeCart();
        priceCalculator.recalculatePrice(order);
        Double expected = 200d;
        assertEquals(expected, order.getUser().getAccumulativeCard().getBalance());
    }


    public void prepareTestRecalculatePriceWithDiscountBasedOnAccumulativeCart() {
        AccumulativeCard t = new AccumulativeCard();
        t.setBalance(100d);
        user = new User();
        user.setAccumulativeCard(t);
        order.setUser(user);
        Pizza pizza = new Pizza();
        pizza.setPrice(200d);
        order.addItems(pizza);
    }

    @Test
    public void testRecalculatePriceWithDiscountBasedOnAccumulativeCart() {
        prepareTestRecalculatePriceWithDiscountBasedOnAccumulativeCart();
        Double price =  priceCalculator.recalculatePrice(order);
        Double expected = 190d;
        assertEquals(expected, price);
    }

    public void prepareTestRecalculatePriceWithSeveralPizzas() {
        AccumulativeCard t = new AccumulativeCard();
        t.setBalance(300d);
        user = new User();
        user.setAccumulativeCard(t);
        order.setUser(user);
        Pizza pizza1 = new Pizza();
        pizza1.setName("pizza1");
        pizza1.setPrice(200d);
        pizza1.setType(PizzaType.MEAT);

        Pizza pizza2 = new Pizza();
        pizza2.setName("pizza2");
        pizza2.setPrice(100d);
        pizza2.setType(PizzaType.MEAT);

        Pizza pizza3 = new Pizza();
        pizza3.setName("pizza3");
        pizza3.setPrice(300d);
        pizza3.setType(PizzaType.SEA);

        order.addItems(pizza1,pizza2,pizza3);
    }

    @Test
    public void testRecalculatePriceWithWithSeveralPizzas() {
        prepareTestRecalculatePriceWithSeveralPizzas();
        Double price =  priceCalculator.recalculatePrice(order);
        Double expected = 570d;
        assertEquals(expected, price);
    }
}