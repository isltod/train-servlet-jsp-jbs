package com.mycompany.ordersystem.product.repository;

import com.mycompany.ordersystem.domain.Customer;
import com.mycompany.ordersystem.domain.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class ProductRepositoryImplJPA implements ProductRepository {

    private EntityManager em;

    public ProductRepositoryImplJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public Product findById(long id) {
        return em.find(Product.class, id);
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT * FROM product ORDER BY product_id";
        Query q = em.createNativeQuery(sql, Product.class);
        List<Product> products = q.getResultList();
        return products;
    }

    @Override
    public void save(Product product) {
        em.getTransaction().begin();
        Product entity = findById(product.getId());
        if (entity == null) {
            entity = new Product();
            entity.setId(product.getId());
        }
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        // persist가 insert or save
        em.persist(entity);
        em.getTransaction().commit();
    }

    @Override
    public void delete(long id) {
        em.getTransaction().begin();
        Product entity = findById(id);
        if (entity != null) {
            // remove가 delete
            em.remove(entity);
        }
        em.getTransaction().commit();
    }
}
