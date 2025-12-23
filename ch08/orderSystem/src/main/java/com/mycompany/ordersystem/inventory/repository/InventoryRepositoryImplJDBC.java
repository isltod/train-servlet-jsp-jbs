package com.mycompany.ordersystem.inventory.repository;

import com.mycompany.ordersystem.domain.Inventory;
import com.mycompany.ordersystem.domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryRepositoryImplJDBC implements InventoryRepository{

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public InventoryRepositoryImplJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public long findById(long id) {
        long quantity = 0;
        String sql = "SELECT * FROM inventory WHERE product_id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                quantity = rs.getLong("inventory_quantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quantity;
    }

    @Override
    public void save(long id, long quantity) {
        String sql = "SELECT * FROM inventory WHERE product_id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                update(id, quantity);
            } else {
                insert(id, quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insert(long id, long quantity) throws SQLException {
        String sql = "INSERT INTO inventory (product_id, inventory_quantity) VALUES (?, ?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setLong(1, id);
        pstmt.setLong(2, quantity);
        pstmt.executeUpdate();
    }

    private void update(long id, long quantity) throws SQLException {
        String sql = "UPDATE inventory SET inventory_quantity=? WHERE product_id=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setLong(1, quantity);
        pstmt.setLong(2, id);
        pstmt.executeUpdate();
    }
}
