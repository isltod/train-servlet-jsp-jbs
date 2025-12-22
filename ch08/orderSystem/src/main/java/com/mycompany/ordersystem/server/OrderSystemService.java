package com.mycompany.ordersystem.server;

import com.mycompany.ordersystem.customer.respository.CustomerRepository;
import com.mycompany.ordersystem.customer.respository.CustomerRepositoryImplJDBC;
import com.mycompany.ordersystem.customer.respository.CustomerRepositoryImplList;
import com.mycompany.ordersystem.customer.service.CustomerServiceImpl;
import com.mycompany.ordersystem.inventory.repository.InventoryRepository;
import com.mycompany.ordersystem.inventory.repository.InventoryRepositoryImplList;
import com.mycompany.ordersystem.inventory.service.InventoryServiceImpl;
import com.mycompany.ordersystem.order.repository.OrderRepository;
import com.mycompany.ordersystem.order.repository.OrderRepositoryImplList;
import com.mycompany.ordersystem.order.service.OrderServiceImpl;
import com.mycompany.ordersystem.product.repository.ProductRepository;
import com.mycompany.ordersystem.product.repository.ProductRepositoryImplJDBC;
import com.mycompany.ordersystem.product.repository.ProductRepositoryImplList;
import com.mycompany.ordersystem.product.service.ProductServiceImpl;
import com.mycompany.ordersystem.services.CustomerService;
import com.mycompany.ordersystem.services.InventoryService;
import com.mycompany.ordersystem.services.OrderService;
import com.mycompany.ordersystem.services.ProductService;
import com.mycompany.ordersystem.utils.ConnectionProperties;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class OrderSystemService {
    private CustomerService customerService;
    private ProductService productService;
    private InventoryService inventoryService;
    private OrderService orderService;
    private Properties properties = null;
    private Connection connection = null;

    public OrderSystemService() {
        CustomerRepository customerRepository;
        ProductRepository productRepository;
        InventoryRepository inventoryRepository;
        OrderRepository orderRepository;

        // 이 부분이 다 디비 버전으로 바뀌어야 되는 건데...그러러면 먼저 디비 버전을 만들어야 되겠구나...
        try {
            properties = ConnectionProperties.loadProperties("database.properties");
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            // 이걸 해 놔야 메모리에 오라클 JDBC가 둥둥 떠서, 밑에 DriverManager가 그걸 잡아다 붙여준다고...
            Class.forName(properties.getProperty("driver"));
            // 근데 이렇게 만들기는 하는데...대체 어디서 이걸 닫나?
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        // customerRepository = new CustomerRepositoryImplList();
        customerRepository = new CustomerRepositoryImplJDBC(connection);
        orderRepository = new OrderRepositoryImplList();
        inventoryRepository = new InventoryRepositoryImplList();
        // productRepository = new ProductRepositoryImplList();
        productRepository = new ProductRepositoryImplJDBC(connection);

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
