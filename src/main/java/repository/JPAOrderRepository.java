package repository;

import domain.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository("orderRepository")
public class JPAOrderRepository implements OrderRepository {

    @PersistenceContext(name = "HiberanteMySQL") //, type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public int getNewOrderID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> getAllOrders() {
        TypedQuery<Order> query =
                em.createQuery("select o from Order o", Order.class);
        return query.getResultList();
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public Order getOrderById(Long id) {
        Order order = em.find(Order.class, id);
        order.getItems().size();
        return order;
    }

    @Override
    public Order save(Order order) {
        em.persist(order);
        return order;
    }

    @Override
    public void update(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
