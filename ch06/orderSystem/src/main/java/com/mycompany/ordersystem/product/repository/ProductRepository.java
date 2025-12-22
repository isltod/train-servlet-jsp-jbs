package com.mycompany.ordersystem.product.repository;

import com.mycompany.ordersystem.domain.Product;

import java.util.List;

public interface ProductRepository {
    Product findById(long id);
    List<Product> findAll();
    void save(Product product);
    void delete(long id);
}
