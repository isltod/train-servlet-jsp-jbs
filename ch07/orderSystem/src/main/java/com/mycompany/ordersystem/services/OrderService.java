package com.mycompany.ordersystem.services;

import com.mycompany.ordersystem.domain.Customer;
import com.mycompany.ordersystem.domain.Order;

import java.util.List;

public interface OrderService {
    void purchaseOrder(Order order);
    void cancelOrder(Order order);
    Order getOrder(long id);
    List<Order> getOrders(Customer customer);
}
