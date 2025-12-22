package com.mycompany.ordersystem.customer.service;

import com.mycompany.ordersystem.services.CustomerService;
import com.mycompany.ordersystem.customer.respository.CustomerRepository;
import com.mycompany.ordersystem.domain.Customer;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getCustomer(long id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> getCustomersByName(String name) {
        return customerRepository.findsByName(name);
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(long id) {
        Customer cust = getCustomer(id);
        if(cust != null)
            customerRepository.delete(id);;
    }
}
