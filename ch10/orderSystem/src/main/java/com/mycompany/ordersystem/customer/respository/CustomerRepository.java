package com.mycompany.ordersystem.customer.respository;

import com.mycompany.ordersystem.domain.Customer;

import java.util.List;

public interface CustomerRepository {
    Customer findById(long id);
    List<Customer> findAll();
    List<Customer> findsByName(String name);
    void save(Customer customer);
    void delete(long id);
}