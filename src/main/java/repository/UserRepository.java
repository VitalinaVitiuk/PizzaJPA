package repository;

import domain.Order;
import domain.User;

import java.util.List;

/**
 * Created by Winnie on 8/20/2015.
 */
public interface UserRepository{

    User getUserById(Long id);
    List<Order> showAllOrders(User user);
    Long save(User user);

    Double showCardBalance(Long id);
    User getUserByUserName (String username);
}
