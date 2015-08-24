package web;

import domain.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import service.PizzaService;

import java.beans.PropertyEditorSupport;

public abstract class AbstractPizzaController {

    @Autowired
    protected PizzaService pizzaService;

    protected Pizza getPizzaById(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID<0");
        }
        Pizza pizza = pizzaService.getPizzaById(id);
        if (pizza == null) {
            throw new NotFoundPizzaException("Pizza id" + id + " not found");
        }
        return pizza;
    }

    @InitBinder
    private void pizzaBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Pizza.class,
                new PropertyEditorSupport() {
                    @Override
                    public void setAsText(String pizzaId) {
                        Pizza pizza = null;
                        if (pizzaId != null && !pizzaId.trim().isEmpty()) {
                            Long id = Long.valueOf(pizzaId);
                            pizza = getPizzaById(id);
                        }
                        setValue(pizza);
                        System.out.println(pizza);
                    }
                }
        );
    }
}
