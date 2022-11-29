package com.amex.api.controller;

import com.amex.api.data.Product;
import com.amex.api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public Product saveProduct(@Valid Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.fetchProducts();
    }

    @GetMapping("/product/{id}")
    public Optional<Product> getProductById(@PathVariable("id") Long id) {
        return productService.fetchProductById(id);
    }

    @PutMapping("/product/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable("id") Long id) {
        return productService.updateProduct(product, id);
    }

    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
        return "Deleted successfully";
    }
}
