package com.dev.scaler.productservice.controllers;

import com.dev.scaler.productservice.dtos.ProductDTO;
import com.dev.scaler.productservice.dtos.ProductResponseDTO;
import com.dev.scaler.productservice.exceptions.NotFoundException;
import com.dev.scaler.productservice.models.Category;
import com.dev.scaler.productservice.models.Product;
import com.dev.scaler.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // this controller hosting REST HTTP API
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(@Qualifier("selfProductService") ProductService productService){
        this.productService = productService;
    }

    // localhost:8080/products/1
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable("id") Long id) throws NotFoundException {
        Product product = productService.getProductById(id);
        ProductResponseDTO productResponseDTO = convertProductToProductResponseDTO(product);
        return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
    }

    // localhost:8080/products
    @GetMapping()
    public List<ProductResponseDTO> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return products.stream().map(this::convertProductToProductResponseDTO).toList();
    }

    // replaceProduct (PUT)
    @PutMapping("/{id}")
    public ProductResponseDTO updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO){
        Product product = convertProductDTOtoProduct(productDTO);
        Product productResponse = productService.updateProduct(id, product);
        return convertProductToProductResponseDTO(productResponse);
    }

    // createProduct
    @PostMapping()
    public ProductResponseDTO createProduct(@RequestBody ProductDTO productDTO){
        Product product = convertProductDTOtoProduct(productDTO);
        Product productResponse = productService.createProduct(product);
        return convertProductToProductResponseDTO(productResponse);
    }

    // deleteProduct
    @DeleteMapping("/{id}")
    public ProductResponseDTO deleteProduct(@PathVariable("id") Long id){
        Product productResponse = productService.deleteProduct(id);
        return null;
    }

    // updateProduct (PATCH)


    private Product convertProductDTOtoProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImage(productDTO.getImage());
        product.setTitle(productDTO.getTitle());

        Category category = new Category();
        category.setDescription(productDTO.getCategory());
        product.setCategory(category);

        return product;
    }

    private ProductResponseDTO convertProductToProductResponseDTO(Product product){
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(product.getId());
        productResponseDTO.setTitle(product.getTitle());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setDescription(product.getDescription());
        productResponseDTO.setImage(product.getImage());

        Category category = new Category();
        productResponseDTO.setCategory(category.getDescription());

        return productResponseDTO;
    }
}
