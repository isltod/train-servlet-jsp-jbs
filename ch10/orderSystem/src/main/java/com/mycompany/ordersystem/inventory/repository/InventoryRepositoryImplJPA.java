package com.mycompany.ordersystem.inventory.repository;

import com.mycompany.ordersystem.domain.Inventory;
import com.mycompany.ordersystem.domain.Product;
import jakarta.persistence.EntityManager;

public class InventoryRepositoryImplJPA implements InventoryRepository {

    private EntityManager entityManager;
    public InventoryRepositoryImplJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public long findById(long id) {
        Inventory inventory = entityManager.find(Inventory.class, id);
        if (inventory == null) {
            return 0;
        }
        return inventory.getQuantity();
    }

    @Override
    public void save(long id, long quantity) {
        entityManager.getTransaction().begin();
        Inventory inventory = entityManager.find(Inventory.class, id);
        if (inventory == null) {
            inventory = new Inventory();
            // 종속 엔터티에서는 id 외에도 독립 엔터티 객체도 필드에 넣어줘야 save 되는구나...
            inventory.setId(id);
            Product product = entityManager.find(Product.class, id);
            inventory.setProduct(entityManager.find(Product.class, id));
        }
        inventory.setQuantity(quantity);
        entityManager.persist(inventory);
        entityManager.getTransaction().commit();
    }
}
