package com.mycompany.ordersystem.services;

import com.mycompany.ordersystem.domain.Customer;

import java.util.List;

public interface CustomerService {
    Customer getCustomer(long id);
    List<Customer> getCustomers();
    List<Customer> getCustomersByName(String name);
    void saveCustomer(Customer customer);
    void deleteCustomer(long id);
}
