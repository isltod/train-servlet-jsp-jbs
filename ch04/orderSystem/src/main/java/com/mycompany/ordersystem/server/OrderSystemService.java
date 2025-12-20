package com.mycompany.ordersystem.server;

import com.mycompany.ordersystem.customer.respository.CustomerRepository;
import com.mycompany.ordersystem.customer.respository.CustomerRepositoryImplList;
import com.mycompany.ordersystem.customer.service.CustomerServiceImpl;
import com.mycompany.ordersystem.inventory.repository.InventoryRepository;
import com.mycompany.ordersystem.inventory.repository.InventoryRepositoryImplList;
import com.mycompany.ordersystem.inventory.service.InventoryServiceImpl;
import com.mycompany.ordersystem.order.repository.OrderRepository;
import com.mycompany.ordersystem.order.repository.OrderRepositoryImplList;
import com.mycompany.ordersystem.order.service.OrderServiceImpl;
import com.mycompany.ordersystem.product.repository.ProductRepository;
import com.mycompany.ordersystem.product.repository.ProductRepositoryImplList;
import com.mycompany.ordersystem.product.service.ProductServiceImpl;
import com.mycompany.ordersystem.services.CustomerService;
import com.mycompany.ordersystem.services.InventoryService;
import com.mycompany.ordersystem.services.OrderService;
import com.mycompany.ordersystem.services.ProductService;

public class OrderSystemService {
    private CustomerService customerService;
    private ProductService productService;
    private InventoryService inventoryService;
    private OrderService orderService;

    public OrderSystemService() {
        CustomerRepository customerRepository;
        ProductRepository productRepository;
        InventoryRepository inventoryRepository;
        OrderRepository orderRepository;

        customerRepository = new CustomerRepositoryImplList();
        orderRepository = new OrderRepositoryImplList();
        inventoryRepository = new InventoryRepositoryImplList();
        productRepository = new ProductRepositoryImplList();

        this.customerService = new CustomerServiceImpl(customerRepository);
        this.productService = new ProductServiceImpl(productRepository);
        this.inventoryService = new InventoryServiceImpl(inventoryRepository, productService);
        this.orderService = new OrderServiceImpl(orderRepository, inventoryService);
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public InventoryService getInventoryService() {
        return inventoryService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    // 정적 메서드로 인스턴스를 얻는 방법은 이렇게 복잡한건가? 그냥 자기 인스턴스를 주는 메서드는 없나?
    private static class Singleton {
        static final OrderSystemService instance = new OrderSystemService();
    }

    public static OrderSystemService createInstance() {
        return Singleton.instance;
    }
}
