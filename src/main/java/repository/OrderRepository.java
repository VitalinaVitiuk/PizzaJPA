package repository;

import domain.Order;

import java.util.List;

/**
 * Created by Winnie on 8/7/2015.
 */



public interface OrderRepository {

    int getNewOrderID();

    List<Order> getAllOrders();

    Order getOrderById(Long id);

    Order save(Order order);

    void update(Order order);

}