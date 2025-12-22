package com.mycompany.ordersystem.product.service;

import com.mycompany.ordersystem.domain.Product;
import com.mycompany.ordersystem.product.repository.ProductRepository;
import com.mycompany.ordersystem.services.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public Product getProduct(long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(long id) {
        Product prod = getProduct(id);
        if(prod != null)
            productRepository.delete(id);
    }
}
