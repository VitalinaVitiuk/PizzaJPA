package web;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.OrderService;
import service.PizzaService;
import service.PriceCalculator;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private UserService userService;

    @Autowired
    PriceCalculator priceCalculator;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewPizzas(Model model, HttpServletRequest request) {

        model.addAttribute("pizzas", pizzaService.getAllPizzas());
        if (request.getSession().getAttribute("order") == null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName(); //get logged in username
            User user = userService.getUserByUserName(name);

            Order order = orderService.createNewOrder();
            order.setOrderStatus(OrderStatus.NEW);
            order.setUser(user);
            request.getSession().setAttribute("order", order);
        }

        return "pizzasfororder";
    }


    @RequestMapping(value="/userorders")
    public String viewUserOrders(Model model, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        User user = userService.getUserByUserName(name);
        model.addAttribute("orders", userService.showAllOrders(user));
        model.addAttribute("user", user);
        return "orders";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value="/allorders")
    public String viewAllOrders(Model model, HttpServletRequest request) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }

    @RequestMapping(value = "/save")
    public String addNewOrder(HttpServletRequest request) {
        Order newOrder = (Order) request.getSession().getAttribute("order");
        newOrder.setDate(new Date());
        newOrder.setOrderStatus(OrderStatus.NEW);
        orderService.placeOrder(newOrder);

        request.getSession().removeAttribute("order");
        return "redirect:";
    }

    @RequestMapping(value = "/addpizza", method = RequestMethod.POST)
    public String addPizzaToOrder(@RequestParam("pizzaid") Long id,
                                  HttpServletRequest request,Model model) {
        Order order = (Order) request.getSession().getAttribute("order");
        order.addItems(pizzaService.getPizzaById(id));
        order.setPrice(priceCalculator.recalculatePrice(order));

        return "redirect:";
    }

    @RequestMapping(value = "/removepizza", method = RequestMethod.POST)
    public String removePizzaFromOrder(@RequestParam("pizzaid") Long id, HttpServletRequest request) {
        Order order = (Order) request.getSession().getAttribute("order");
        if (order == null) {
            order = new Order();
            order.setOrderStatus(OrderStatus.NEW);
            request.getSession().setAttribute("order", order);
        }

        order.deleteItems(pizzaService.getPizzaById(id));
        order.setPrice(priceCalculator.recalculatePrice(order));
        return "redirect:";
    }
}