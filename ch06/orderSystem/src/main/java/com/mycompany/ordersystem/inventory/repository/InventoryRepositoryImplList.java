package com.mycompany.ordersystem.inventory.repository;

import com.mycompany.ordersystem.domain.Product;
import com.mycompany.ordersystem.product.repository.ProductRepositoryImplList;
import com.mycompany.ordersystem.product.service.ProductServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InventoryRepositoryImplList implements InventoryRepository {
    private Map<Long, Long> inventories;

    public InventoryRepositoryImplList() {
        inventories = new HashMap<Long, Long>();
        List<Product> products = new ProductRepositoryImplList().findAll();
        for(Product product : products) {
            long quantity = product.getId() * 10;
            inventories.put(product.getId(), quantity);
        }
    }

    @Override
    public long findById(long id) {
        Long value = inventories.get(id);
        return Optional.ofNullable(value).orElse(0L);

    }

    @Override
    public void save(long id, long quantity) {
        inventories.put(id, quantity);
    }
}
