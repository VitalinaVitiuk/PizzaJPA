package repository;

import domain.Pizza;
import domain.PizzaType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Winnie on 7/23/2015.
 */

public interface PizzaRepository {
    List<Pizza> getAllPizzas();

    List<Pizza> getPizzasByType(PizzaType type);

    public Long save(Pizza pizza);

    Pizza getPizzaById(Long id);
}
