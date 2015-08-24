package service;

import domain.Order;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import repository.UserRepository;

import java.util.List;


/**
 * Created by Winnie on 8/20/2015.
 */
public interface UserService {

    User getUserById(Long id);
    Long save(User user);
    Double showCardBalance(Long id);
    User getUserByUserName (String username);
    List<Order> showAllOrders(User user);
}
