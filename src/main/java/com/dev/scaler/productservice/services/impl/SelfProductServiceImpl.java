package com.dev.scaler.productservice.services.impl;

import com.dev.scaler.productservice.exceptions.NotFoundException;
import com.dev.scaler.productservice.models.Product;
import com.dev.scaler.productservice.repositories.ProductRepository;
import com.dev.scaler.productservice.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
public class SelfProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public SelfProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(Long id) throws NotFoundException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }
}
