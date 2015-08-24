package service;

import domain.AccumulativeCard;
import domain.Order;
import domain.OrderStatus;
import domain.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.OrderRepository;
import repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Winnie on 8/9/2015.
 */
@Service("orderService")
public class SimpleOrderService implements OrderService {

    @Autowired
    private UserRepository userRepository;
    private OrderRepository orderRepository;

    @Autowired
    public SimpleOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.getOrderById(id);
    }

    @Override
    public Order createNewOrder() {
        Order newOrder = createEmptyOrder();
        return newOrder;

    }

    @Lookup(value = "order")
    public Order createEmptyOrder() {
        return null;
    }

    boolean isWorkingDay() {
//        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
//        return dayOfWeek != DayOfWeek.SUNDAY;
        return true;
    }

    @Override
    @Transactional
    public Order placeOrder(Order order) {
        if (!isWorkingDay()) {
            throw new IllegalStateException();
        }
        if (order.getItems().isEmpty()) {
            throw new IllegalArgumentException();
        }
        userRepository.save(order.getUser());
        return orderRepository.save(order);
    }

    @Override
    public boolean cancelOrder(Long orderId, String email) {
        Order order = orderRepository.getOrderById(orderId);
        order.setOrderStatus(OrderStatus.CANCELLED);
        orderRepository.update(order);
        return true;
    }




}

