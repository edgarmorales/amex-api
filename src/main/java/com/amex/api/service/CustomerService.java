package com.amex.api.service;

import com.amex.api.data.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer saveCustomer(Customer customer);

    Customer updateCustomer(Customer customer, Long id);

    Optional<Customer> fetchCustomerById(Long customerId);

    List<Customer> fetchCustomers();

    void deleteCustomerById(Long customerId);
}