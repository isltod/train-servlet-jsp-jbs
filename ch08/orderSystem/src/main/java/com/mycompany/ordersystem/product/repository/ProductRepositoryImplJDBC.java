package com.mycompany.ordersystem.product.repository;

import com.mycompany.ordersystem.domain.Customer;
import com.mycompany.ordersystem.domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImplJDBC implements ProductRepository{

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public ProductRepositoryImplJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Product findById(long id) {
        Product product = null;
        String sql = "SELECT * FROM product WHERE product_id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setId(rs.getLong("product_id"));
                product.setName(rs.getString("product_name"));
                product.setDescription(rs.getString("product_description"));
                product.setPrice(rs.getLong("product_price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product ORDER BY product_id ASC";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getLong("product_id"));
                product.setName(rs.getString("product_name"));
                product.setDescription(rs.getString("product_description"));
                product.setPrice(rs.getLong("product_price"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void save(Product product) {
        try {
            if (findById(product.getId()) == null) {
                insert(product);
            } else {
                update(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insert(Product product) throws SQLException {
        String sql = "INSERT INTO product (product_name, product_description, product_price) VALUES (?, ?, ?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, product.getName());
        pstmt.setString(2, product.getDescription());
        pstmt.setLong(3, product.getPrice());
        pstmt.executeUpdate();
    }

    private void update(Product product) throws SQLException {
        String sql = "UPDATE product SET product_name=?, product_description=?, product_price=? WHERE product_id=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, product.getName());
        pstmt.setString(2, product.getDescription());
        pstmt.setLong(3, product.getPrice());
        pstmt.setLong(4, product.getId());
        pstmt.executeUpdate();
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM product WHERE product_id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
