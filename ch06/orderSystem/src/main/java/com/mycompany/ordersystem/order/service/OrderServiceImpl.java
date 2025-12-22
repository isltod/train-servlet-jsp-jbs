package com.mycompany.ordersystem.order.service;

import com.mycompany.ordersystem.domain.Customer;
import com.mycompany.ordersystem.domain.Order;
import com.mycompany.ordersystem.domain.OrderItem;
import com.mycompany.ordersystem.order.repository.OrderRepository;
import com.mycompany.ordersystem.services.InventoryService;
import com.mycompany.ordersystem.services.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private InventoryService inventoryService;

    public OrderServiceImpl(OrderRepository orderRepository, InventoryService inventoryService) {
        this.orderRepository = orderRepository;
        this.inventoryService = inventoryService;
    }

    @Override
    public void purchaseOrder(Order order) {
        for(OrderItem item : order.getItems())
            this.inventoryService.takeInventory(item.getProduct().getId(), item.getQuantity());
        this.orderRepository.save(order);
    }

    @Override
    public void cancelOrder(Order order) {
        for(OrderItem item : order.getItems())
            this.inventoryService.stockInventory(item.getProduct().getId(), item.getQuantity());            
        this.orderRepository.delete(order);
    }

    @Override
    public Order getOrder(long id) {
        return this.orderRepository.findById(id);
    }

    @Override
    public List<Order> getOrders(Customer customer) {
        return this.orderRepository.findAll(customer);
    }
}
