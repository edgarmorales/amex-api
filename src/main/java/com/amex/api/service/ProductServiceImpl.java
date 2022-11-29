package com.amex.api.service;

import com.amex.api.data.Product;
import com.amex.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product otherProduct, Long id) {
        Product product = productRepository.findById(id).get();

        if (!otherProduct.getName().isEmpty()) {
            product.setName(otherProduct.getName());
        }
        if (!otherProduct.getDescription().isEmpty()) {
            product.setDescription(otherProduct.getDescription());
        }
        product.setPrice(otherProduct.getPrice());
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> fetchProductById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public List<Product> fetchProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }
}
