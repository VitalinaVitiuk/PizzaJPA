package web;

import domain.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import service.PizzaService;

import java.beans.PropertyEditorSupport;

/**
 * Created by Winnie on 8/10/2015.
 */
@Controller
@RequestMapping("/pizza1")
public class PizzaController extends AbstractPizzaController {

    @Autowired
    private PizzaService pizzaService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewPizzas(Model model) {
        model.addAttribute("pizzas",
                pizzaService.getAllPizzas());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("name", auth.getName());
        model.addAttribute("roles", auth.getAuthorities().toString());
        return "pizzas";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "newpizza";
    }

    @RequestMapping(value = "/addnew", method = RequestMethod.POST)
    public String addNewPizza(@ModelAttribute Pizza newPizza) {
        pizzaService.addPizza(newPizza);
        return "redirect:";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("pizzaid") Pizza pizza,
                       Model model) {
        model.addAttribute("pizza", pizza);
        return "newpizza";
    }
}