package domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "t_order")
@Component("order")
@Scope(value = "prototype")
public class Order {

    @Id
    @GeneratedValue
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    private double price;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "t_order_items",
            joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyJoinColumn(name = "pizza_id", referencedColumnName = "id")
    @Column(name = "items")
    private Map<Pizza, Integer> items = new HashMap<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<Pizza, Integer> getItems() {
        return items;
    }

    public Order() {
        this.date = new Date();
    }

    public Order(int newOrdeId, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addItems(Pizza... items) {
        for (Pizza item : items) {
            Integer count = this.items.get(item);
            count = (count == null ? 1 : count + 1);
            this.items.put(item, count);
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", items= " + items +
                ", orderStatus=" + orderStatus +
                '}';
    }

    public boolean deleteItems(Pizza pizza) {
        Integer count = this.items.get(pizza);
        if (count == null) {
            return false;
        } else if (count == 1) {
            this.items.remove(pizza);
            return true;
        } else {
            count--;
            this.items.put(pizza, count);
            return true;
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setItems(Map<Pizza, Integer> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

