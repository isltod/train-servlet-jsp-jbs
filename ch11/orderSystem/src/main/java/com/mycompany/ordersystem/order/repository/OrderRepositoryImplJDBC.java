package com.mycompany.ordersystem.order.repository;

import com.mycompany.ordersystem.customer.respository.CustomerRepository;
import com.mycompany.ordersystem.domain.Customer;
import com.mycompany.ordersystem.domain.Order;
import com.mycompany.ordersystem.domain.OrderItem;
import com.mycompany.ordersystem.domain.Product;
import com.mycompany.ordersystem.product.repository.ProductRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImplJDBC implements OrderRepository {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;

    public OrderRepositoryImplJDBC(
            Connection conn, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.conn = conn;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Order findById(long id) {
        Order order = null;
        String sql = "SELECT * FROM orders WHERE order_id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                order = getOrder(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public List<Order> findAll(Customer customer) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE customer_id=? ORDER BY order_id";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, customer.getId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Order order = getOrder(rs);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    private Order getOrder(ResultSet resultSet) throws SQLException {
        String sql = "SELECT * FROM order_item WHERE order_id=?";
        long order_id = resultSet.getLong(1);
        pstmt = conn.prepareStatement(sql);
        pstmt.setLong(1, order_id);
        ResultSet rsOrderItems = pstmt.executeQuery();
        List<OrderItem> orderItems = new ArrayList<>();
        while (rsOrderItems.next()) {
            long product_id = rsOrderItems.getLong("product_id");
            Product product = productRepository.findById(product_id);
            long quantity = rsOrderItems.getLong("order_item_quantity");
            OrderItem orderItem = new OrderItem(product, quantity);
            orderItems.add(orderItem);
        }
        long customer_id = resultSet.getLong("customer_id");
        Customer customer = customerRepository.findById(customer_id);
        Order order = new Order(customer, orderItems);
        order.setId(order_id);
        order.setDate(resultSet.getDate("order_date").toLocalDate());
        return order;
    }

    @Override
    public void save(Order order) {
        String sqlOrder = "INSERT INTO orders (customer_id) VALUES (?)";
        String sqlOrderItem =
                "INSERT INTO order_item (product_id, order_item_quantity, order_id) VALUES (?, ?, ?)";
        try {
            // 트랜잭션이라는 건데...
            conn.setAutoCommit(false);
            String[] collNames = new String[]{"order_id"};
            pstmt = conn.prepareStatement(sqlOrder, collNames);
            pstmt.setLong(1, order.getCustomer().getId());
            pstmt.executeUpdate();
            ResultSet rsOrder = pstmt.getGeneratedKeys();
            if (rsOrder.next()) {
                long order_id = rsOrder.getLong(1);
                for (OrderItem orderItem : order.getItems()) {
                    pstmt = conn.prepareStatement(sqlOrderItem);
                    pstmt.setLong(1, orderItem.getProduct().getId());
                    pstmt.setLong(2, orderItem.getQuantity());
                    pstmt.setLong(3, order_id);
                    pstmt.executeUpdate();
                }
                // 여기까지 왔으면 커밋한다?
                conn.commit();
            } else  {
                // 근데 새 주문이 생성 안되면 롤백한다?
                // 뭔가 다른데서 오류가 나도 롤백 아닌가? 그럼 위치가 여기 말고 아래 catch 아닌가?
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Order order) {
        String sql = "DELETE FROM orders WHERE order_id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, order.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
