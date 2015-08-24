package service;

import domain.AccumulativeCard;
import domain.Order;
import domain.Pizza;

import java.util.List;
import java.util.Map;

/**
 * Created by Winnie on 8/9/2015.
 */
public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Order createNewOrder();
    Order placeOrder(Order order);
    boolean cancelOrder(Long orderId, String email);

}


