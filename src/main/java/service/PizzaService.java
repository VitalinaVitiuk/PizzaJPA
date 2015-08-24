package service;

import domain.Pizza;

import java.util.List;

/**
 * Created by Winnie on 8/9/2015.
 */
public interface PizzaService {
    List<Pizza> getAllPizzas();
    Long addPizza(Pizza pizza);
    Pizza getPizzaById(Long id);
}
