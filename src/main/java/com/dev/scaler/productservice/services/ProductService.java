package com.dev.scaler.productservice.services;

import com.dev.scaler.productservice.exceptions.NotFoundException;
import com.dev.scaler.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id) throws NotFoundException;

    List<Product> getAllProducts();

    Product updateProduct(Long id, Product product);

    Product createProduct(Product product);

    Product deleteProduct(Long id);
}
