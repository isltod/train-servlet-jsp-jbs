package com.mycompany.ordersystem.customer.respository;

import com.mycompany.ordersystem.domain.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImplJDBC implements CustomerRepository {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    // 근데 커넥션만 받아온다? 아...고객, 제품, 제고, 주문 모두 같은 커넥션을 쓰겠다는 얘기구나...
    public CustomerRepositoryImplJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Customer findById(long id) {
        Customer customer = null;
        String sql = "SELECT * FROM customer WHERE customer_id=?";
        try {
            // 여기서도 만들어 쓰기만 하고 close를 하질 않는데...
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getLong("customer_id"));
                customer.setName(rs.getString("customer_name"));
                customer.setAddress(rs.getString("customer_address"));
                customer.setEmail(rs.getString("customer_email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getLong("customer_id"));
                customer.setName(rs.getString("customer_name"));
                customer.setAddress(rs.getString("customer_address"));
                customer.setEmail(rs.getString("customer_email"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public List<Customer> findsByName(String name) {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer WHERE customer_name=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getLong("customer_id"));
                customer.setName(rs.getString("customer_name"));
                customer.setAddress(rs.getString("customer_address"));
                customer.setEmail(rs.getString("customer_email"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public void save(Customer customer) {
        try {
            if (findById(customer.getId()) == null) {
                insert(customer);
            } else {
                update(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insert(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer (customer_name, customer_address, customer_email) VALUES (?, ?, ?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, customer.getName());
        pstmt.setString(2, customer.getAddress());
        pstmt.setString(3, customer.getEmail());
        pstmt.executeUpdate();
    }

    private void update(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET customer_name=?, customer_address=?, customer_email=? WHERE customer_id=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, customer.getName());
        pstmt.setString(2, customer.getAddress());
        pstmt.setString(3, customer.getEmail());
        pstmt.setLong(4, customer.getId());
        pstmt.executeUpdate();
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM customer WHERE customer_id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
