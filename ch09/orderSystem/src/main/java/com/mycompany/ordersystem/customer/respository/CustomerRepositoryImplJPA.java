package com.mycompany.ordersystem.customer.respository;

import com.mycompany.ordersystem.domain.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class CustomerRepositoryImplJPA implements CustomerRepository {
    private EntityManager em;

    public CustomerRepositoryImplJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public Customer findById(long id) {
        return em.find(Customer.class, id);
    }

    @Override
    public List<Customer> findAll() {
        String sql = "SELECT * FROM customer ORDER BY customer_id";
        Query q = em.createNativeQuery(sql, Customer.class);
        List<Customer> customers = q.getResultList();
        return customers;
    }

    @Override
    public List<Customer> findsByName(String name) {
        String sql = "SELECT * FROM customer WHERE customer_name LIKE ?";
        Query q = em.createNativeQuery(sql, Customer.class);
        q.setParameter(1, name);
        List<Customer> customers = q.getResultList();
        return customers;
    }

    @Override
    public void save(Customer customer) {
        em.getTransaction().begin();
        Customer entity = findById(customer.getId());
        // 요게 사실상 Insert가 되는 키이고...여길 안들어가면 Update
        if (entity == null) {
            entity = new Customer();
        }
        entity.setName(customer.getName());
        entity.setAddress(customer.getAddress());
        entity.setEmail(customer.getEmail());
        em.persist(entity);
        em.getTransaction().commit();
    }

    @Override
    public void delete(long id) {
        em.getTransaction().begin();
        Customer entity = findById(id);
        if (entity != null) {
            em.remove(entity);
        }
        em.getTransaction().commit();
    }
}
