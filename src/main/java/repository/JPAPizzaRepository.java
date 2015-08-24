package repository;

import domain.Pizza;
import domain.PizzaType;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Winnie on 8/5/2015.
 */

@Repository("pizzaRepository")
public class JPAPizzaRepository implements PizzaRepository {

    @PersistenceContext(name = "HiberanteMySQL")
    private EntityManager em;


    @Override
    public List<Pizza> getAllPizzas() {
        TypedQuery<Pizza> query
                = em.createQuery("select p from Pizza p", Pizza.class);
        return query.getResultList();
    }

    @Override
    public List<Pizza> getPizzasByType(PizzaType type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    @Override
    public Long save(Pizza pizza) {
        if(pizza.getId()==null){
        em.persist(pizza);
        } else {
            em.merge(pizza);
        }
        return pizza.getId();
    }

    @Override
    public Pizza getPizzaById(Long id) {
        return em.find(Pizza.class, id);
    }

}