package com.amex.api.service;

import com.amex.api.data.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class ProductServiceTests {

    @Autowired
    private ProductService productService;

    @Test
    @DisplayName("Create an Associate")
    public void test_a_givenProductService_whenProductIsCreated_thenFetchAndMatchFields() {
        Long productId = productService.saveProduct(
                new Product(
                        "Test A Product",
                        "A Test A non-valid Product",
                        new BigDecimal("0.10")))
                .getId();

        Product product = productService.fetchProductById(productId).get();

        Assertions.assertThat(product.getName()).isEqualTo("Test A Product");
        Assertions.assertThat(product.getDescription()).isEqualTo("A Test A non-valid Product");
        Assertions.assertThat(product.getPrice()).isEqualTo(new BigDecimal("0.10"));
    }

    @Test
    @DisplayName("Update a Product")
    public void test_b_givenProductService_whenProductIsUpdated_thenFetchAndMatchFields() {
        Long productId = productService.saveProduct(
                new Product(
                        "Test B Product",
                        "A Test A non-valid Product",
                        new BigDecimal("0.10")))
                .getId();

        Product product = productService.fetchProductById(productId).get();
        product.setDescription(product.getDescription() + "-UPDATED");
        product.setName(product.getName() + "-UPDATED");
        product.setPrice(new BigDecimal("0.20"));

        productService.updateProduct(product, productId);

        Product updatedProduct = productService.fetchProductById(productId).get();
        Assertions.assertThat(updatedProduct.getDescription()).isEqualTo(product.getDescription());
        Assertions.assertThat(updatedProduct.getName()).isEqualTo(product.getName());
        Assertions.assertThat(updatedProduct.getPrice()).isEqualTo(product.getPrice());
    }

    @Test
    @DisplayName("Delete a Product")
    public void test_c_givenProductService_whenProductIsDeleted_thenConfirmProductNotExists() {
        Long productId = productService.saveProduct(
                new Product(
                        "Test C Product",
                        "A Test A non-valid Product",
                        new BigDecimal("0.10")))
                .getId();

        productService.deleteProductById(productId);

        Optional<Product> possibleProduct = productService.fetchProductById(productId);
        Assertions.assertThat(possibleProduct.isPresent()).isFalse();
    }
}
