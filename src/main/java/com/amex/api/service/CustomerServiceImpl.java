package com.amex.api.service;

import com.amex.api.data.Customer;
import com.amex.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer otherCustomer, Long id) {
        Customer customer = customerRepository.findById(id).get();

        if (!otherCustomer.getFirstName().isEmpty()) {
            customer.setFirstName(otherCustomer.getFirstName());
        }
        if (!otherCustomer.getLastName().isEmpty()) {
            customer.setLastName(otherCustomer.getLastName());
        }
        if (!otherCustomer.getEmail().isEmpty()) {
            customer.setEmail(otherCustomer.getEmail());
        }
        if (!otherCustomer.getCellphone().isEmpty()) {
            customer.setCellphone(otherCustomer.getCellphone());
        }
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> fetchCustomerById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public List<Customer> fetchCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public void deleteCustomerById(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
