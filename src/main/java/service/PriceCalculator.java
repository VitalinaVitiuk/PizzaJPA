package service;

import domain.AccumulativeCard;
import domain.Order;
import domain.Pizza;
import domain.User;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Winnie on 8/23/2015.
 */
@Service
public class PriceCalculator {

    public Double recalculatePrice(Order order) {
        double totalPrice = 0;

        Set<Pizza> pizzasInOrder = order.getItems().keySet();

        for (Pizza p : pizzasInOrder) {

            int count = order.getItems().get(p);
            double pizzaPrice = p.getPrice();
            double exactP = pizzaPrice * count;
            totalPrice += exactP;
        }

        AccumulativeCard accumulativeCard = order.getUser().getAccumulativeCard();
        totalPrice -= accumulativeCard.getBalance() * 0.1;
        if (totalPrice < 0) {
            totalPrice = 0;
        }
        order.setPrice(totalPrice);

        Double newBalance = accumulativeCard.getBalance() + totalPrice;
        accumulativeCard.setBalance(newBalance);
        order.getUser().setAccumulativeCard(accumulativeCard);
        return totalPrice;
    }
}
