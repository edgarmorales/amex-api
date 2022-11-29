package com.amex.api;

import com.amex.api.controller.AssociateController;
import com.amex.api.controller.CustomerController;
import com.amex.api.controller.OrderController;
import com.amex.api.controller.ProductController;
import com.amex.api.data.Associate;
import com.amex.api.data.Customer;
import com.amex.api.data.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AmexApiApplicationIntegrationTests {

    @Autowired private TestRestTemplate restTemplate;
    @Autowired private AssociateController associateController;
    @Autowired private CustomerController customerController;
    @Autowired private ProductController productController;
    @Autowired private OrderController orderController;

    @Test
    @DisplayName("Confirm Controllers Loaded")
    void contextLoads() {
        Assertions.assertThat(associateController).isNotNull();
        Assertions.assertThat(customerController).isNotNull();
        Assertions.assertThat(productController).isNotNull();
        Assertions.assertThat(orderController).isNotNull();
    }

    @Test
    @DisplayName("Confirm Associates")
    public void givenGetAssociatesApi_whenAssociateListFetched_thenSizeMatchAndListContainsExpectedAssociateNames() {
        ResponseEntity<Associate[]> response = restTemplate.getForEntity("http://localhost:8080/api/associates", Associate[].class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<Associate> associates = Arrays.stream(response.getBody()).toList();
        Assertions.assertThat(associates).hasSize(1);
        assertThat(associates, hasItem(hasProperty("firstName", is("Edgar"))));
    }

    @Test
    @DisplayName("Confirm Products")
    public void givenGetProductsApi_whenProductsListFetched_thenSizeMatchAndListContainsProductNames() {
        ResponseEntity<Product[]> response = restTemplate.getForEntity("http://localhost:8080/api/products", Product[].class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<Product> products = Arrays.stream(response.getBody()).toList();
        Assertions.assertThat(products).hasSize(3);
        assertThat(products, hasItem(hasProperty("name", is("Apple"))));
        assertThat(products, hasItem(hasProperty("name", is("Orange"))));
        assertThat(products, hasItem(hasProperty("name", is("Melon"))));
    }

    @Test
    @DisplayName("Confirm Customers")
    public void givenGetCustomersApi_whenCustomerListFetched_thenSizeMatchAndListContainsExpectedCustomerNames() {
        ResponseEntity<Customer[]> response = restTemplate.getForEntity("http://localhost:8080/api/customers", Customer[].class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<Customer> customers = Arrays.stream(response.getBody()).toList();
        Assertions.assertThat(customers).hasSize(3);
        assertThat(customers, hasItem(hasProperty("firstName", is("Sophie"))));
        assertThat(customers, hasItem(hasProperty("firstName", is("Isabella"))));
        assertThat(customers, hasItem(hasProperty("firstName", is("Sabrina"))));
    }

    @Test
    @DisplayName("Lookup a Product")
    public void givenGetProductByIdApi_whenProductIsFetched_thenMatchNameAndPrice() {
        ResponseEntity<Product> response = restTemplate.getForEntity("http://localhost:8080/api/product/1", Product.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Product product = response.getBody();
        Assertions.assertThat(product.getName()).isEqualTo("Apple");
        Assertions.assertThat(product.getPrice()).isEqualTo(new BigDecimal(0.60).setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    @DisplayName("Lookup an Associate")
    public void givenGetAssociateByIdApi_whenAssociateIsFetched_thenMatchFirstNameAndLastName() {
        ResponseEntity<Associate> response = restTemplate.getForEntity("http://localhost:8080/api/associate/1", Associate.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Associate associate = response.getBody();
        Assertions.assertThat(associate.getFirstName()).isEqualTo("Edgar");
        Assertions.assertThat(associate.getLastName()).isEqualTo("Bon-Associate");
    }

    @Test
    @DisplayName("Lookup a Customer")
    public void getGetCustomerByIdApi_whenCustomerIsFetched_thenMatchFirstNameAndLastName() {
        ResponseEntity<Customer> response = restTemplate.getForEntity("http://localhost:8080/api/customer/1", Customer.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Customer customer = response.getBody();
        Assertions.assertThat(customer.getFirstName()).isEqualTo("Sophie");
        Assertions.assertThat(customer.getLastName()).isEqualTo("Bon-Customer");
    }
}