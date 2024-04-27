package com.dev.scaler.productservice.services.impl;

import com.dev.scaler.productservice.client.fakestore.FakeStoreClient;
import com.dev.scaler.productservice.client.fakestore.dto.FakeStoreProductDTO;
import com.dev.scaler.productservice.client.fakestore.dto.FakeStoreProductRequestDTO;
import com.dev.scaler.productservice.exceptions.NotFoundException;
import com.dev.scaler.productservice.models.Category;
import com.dev.scaler.productservice.models.Product;
import com.dev.scaler.productservice.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    private FakeStoreClient fakeStoreClient;

    public FakeStoreProductService(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }

    @Override
    public Product getProductById(Long id) throws NotFoundException {
        // call Fake Store API to get product with given ID
        Optional<FakeStoreProductDTO> optional = fakeStoreClient.getProductById(id);

        // Convert FakeStoreProductDTO to Product
        return optional.map(this::convertFakeStoreDTOToProduct)
                .orElseThrow(() -> new NotFoundException("No Product found with given id."));
    }

    @Override
    public List<Product> getAllProducts() {
        Optional<FakeStoreProductDTO[]> optional = fakeStoreClient.getAllProducts();

        return optional.map(fakeStoreProductDTOS -> Arrays.stream(fakeStoreProductDTOS)
                .map(this::convertFakeStoreDTOToProduct)
                .toList()).orElse(null);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = convertProductToFakeStoreProductRequestDTO(product);
        Optional<FakeStoreProductDTO> optional = fakeStoreClient.updateProduct(id, fakeStoreProductRequestDTO);
        return optional.map(this::convertFakeStoreDTOToProduct)
                .orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = convertProductToFakeStoreProductRequestDTO(product);
        Optional<FakeStoreProductDTO> optional = fakeStoreClient.createProduct(fakeStoreProductRequestDTO);
        return optional.map(this::convertFakeStoreDTOToProduct)
                .orElse(null);
    }

    @Override
    public Product deleteProduct(Long id) {
        Optional<FakeStoreProductDTO> optional = fakeStoreClient.deleteProduct(id);
        return optional.map(this::convertFakeStoreDTOToProduct)
                .orElse(null);
    }

    private Product convertFakeStoreDTOToProduct(FakeStoreProductDTO fakeStoreProductDTO) {
        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setImage(fakeStoreProductDTO.getImage());
        product.setDescription(fakeStoreProductDTO.getDescription());

        Category category = new Category();
        category.setDescription(fakeStoreProductDTO.getCategory());
        product.setCategory(category);

        return product;
    }

    private FakeStoreProductDTO convertProductToFakeStoreProductDTO(Product product){
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setImage(product.getImage());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setId(product.getId());
        fakeStoreProductDTO.setCategory(product.getCategory().getDescription());

        return fakeStoreProductDTO;
    }

    private FakeStoreProductRequestDTO convertProductToFakeStoreProductRequestDTO(Product product){
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = new FakeStoreProductRequestDTO();
        fakeStoreProductRequestDTO.setDescription(product.getDescription());
        fakeStoreProductRequestDTO.setTitle(product.getTitle());
        fakeStoreProductRequestDTO.setImage(product.getImage());
        fakeStoreProductRequestDTO.setPrice(product.getPrice());
        fakeStoreProductRequestDTO.setCategory(product.getCategory().getDescription());

        return fakeStoreProductRequestDTO;
    }
}
