package com.amex.api.service;

import com.amex.api.data.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product saveProduct(Product product);

    Product updateProduct(Product product, Long id);

    Optional<Product> fetchProductById(Long productId);

    List<Product> fetchProducts();

    void deleteProductById(Long productId);

}