package com.amex.api.service;

import com.amex.api.data.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class CustomerServiceTests {

    @Autowired
    private CustomerService customerService;

    @Test
    @DisplayName("Create a Customer")
    public void test_a_givenCustomerService_whenCustomerIsCreated_thenFetchAndMatchFields() {
        Long customerId = customerService.saveCustomer(
                new Customer(
                        "test-a-customer@test.com",
                        "Tester",
                        "McTester",
                        "123-456-7890"))
                .getId();

        Customer customer = customerService.fetchCustomerById(customerId).get();

        Assertions.assertThat(customer.getEmail()).isEqualTo("test-a-customer@test.com");
        Assertions.assertThat(customer.getFirstName()).isEqualTo("Tester");
        Assertions.assertThat(customer.getLastName()).isEqualTo("McTester");
        Assertions.assertThat(customer.getCellphone()).isEqualTo("123-456-7890");
    }

    @Test
    @DisplayName("Update an Customer")
    public void test_b_givenCustomerService_whenCustomerIsUpdated_thenFetchAndMatchFields() {
        Long customerId = customerService.saveCustomer(
                new Customer(
                        "tester-b-user@test.com",
                        "TesterB",
                        "McTester",
                        "123-456-7890"))
                .getId();

        Customer customer = customerService.fetchCustomerById(customerId).get();
        customer.setEmail(customer.getEmail() + "-UPDATED");
        customer.setFirstName(customer.getFirstName() + "-UPDATED");
        customer.setLastName(customer.getLastName() + "-UPDATED");
        customer.setCellphone(customer.getCellphone() + "-UPDATED");

        customerService.updateCustomer(customer, customerId);

        Customer updatedCustomer = customerService.fetchCustomerById(customerId).get();
        Assertions.assertThat(updatedCustomer.getFirstName()).isEqualTo(customer.getFirstName());
        Assertions.assertThat(updatedCustomer.getEmail()).isEqualTo(customer.getEmail());
        Assertions.assertThat(updatedCustomer.getLastName()).isEqualTo(customer.getLastName());
        Assertions.assertThat(updatedCustomer.getCellphone()).isEqualTo(customer.getCellphone());
    }

    @Test
    @DisplayName("Delete a Customer")
    public void test_c_givenCustomerService_whenCustomerIsDeleted_thenConfirmCustomerNotExists() {
        Long associateId = customerService.saveCustomer(
                new Customer(
                        "tester-c-user@test.com",
                        "TesterC",
                        "McTester",
                        "123-456-7890"))
                .getId();

        customerService.deleteCustomerById(associateId);

        Optional<Customer> possibleCustomer = customerService.fetchCustomerById(associateId);
        Assertions.assertThat(possibleCustomer.isPresent()).isFalse();
    }
}
