package com.dev.scaler.productservice.services;

import com.dev.scaler.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);

    List<Product> getAllProducts();
}
