package com.mycompany.ordersystem.order.repository;


import com.mycompany.ordersystem.domain.Customer;
import com.mycompany.ordersystem.domain.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImplList implements OrderRepository {
    private List<Order> orders;

    public OrderRepositoryImplList() {
        this.orders = new ArrayList<Order>();
    }

    @Override
    public Order findById(long id) {
        for(Order order : orders) {
            if(order.getId() == id)
                return order;
        }
        return null;
    }

    @Override
    public List<Order> findAll(Customer customer) {
        List<Order> customerOrders = new ArrayList<Order>();
        for(Order order : orders) {
            if(order.getCustomer().getId() == customer.getId())
                customerOrders.add(order);
        }
        return customerOrders;
    }

    @Override
    public void save(Order order) {
        long id = nextOrderId();
        order.setId(id);
        orders.add(order);
    }

    @Override
    public void delete(Order order) {
        for(Order o : orders) {
            if(o.getId() == order.getId()) {
                orders.remove(o);
                return;
            }
        }
    }

    private long nextOrderId() {
        long orderId = 0;
        for(Order o : orders) {
            if(o.getId() > orderId)
                orderId = o.getId();
        }
        return orderId + 1;
    }
}
