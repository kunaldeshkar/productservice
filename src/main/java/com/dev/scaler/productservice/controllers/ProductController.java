package com.dev.scaler.productservice.controllers;

import com.dev.scaler.productservice.models.Product;
import com.dev.scaler.productservice.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController // this controller hosting REST HTTP API
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    // localhost:8080/products/1
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    // localhost:8080/products
    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    // createProduct
    // deleteProduct
    // updateProduct (PATCH)
    // replaceProduct (PUT)
}
