package service;

import domain.Order;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.List;

@Service
public class SimpleUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public Long save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Double showCardBalance(Long id) {
        return userRepository.showCardBalance(id);
    }

    public User getUserByUserName (String username){
        return userRepository.getUserByUserName(username);
    }

    @Override
    public List<Order> showAllOrders(User user) {
       return userRepository.showAllOrders(user);
    }


}
