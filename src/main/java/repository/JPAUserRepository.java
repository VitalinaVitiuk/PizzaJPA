package repository;

import domain.Order;
import domain.Pizza;
import domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Winnie on 8/20/2015.
 */

@Repository("userRepository")
public class JPAUserRepository implements UserRepository {

    @PersistenceContext(name = "HiberanteMySQL")
    private EntityManager em;

    @Override
    public User getUserById(Long id) {
        return em.find(User.class, id);
    }


    public User getUserByUserName(String username) {
        Query query = em.createQuery("select u from User u where u.username = :username");
        query.setParameter("username", username);
        List<User> us = query.getResultList();
        System.out.println("=====================  " + us);
        return us.get(0);
    }

    @Override
    public Long save(User user) {
        if (user.getId() == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }
        return user.getId();
    }

    @Override
    public Double showCardBalance(Long id) {
        User user = getUserById(id);
        return user.getAccumulativeCard().getBalance();
    }

    public List<Order> showAllOrders(User user){
        return user.getOrders();
    }
}
