package com.dev.scaler.productservice.services;

import com.dev.scaler.productservice.dtos.FakeStoreProductDTO;
import com.dev.scaler.productservice.models.Category;
import com.dev.scaler.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FakeStoreProductService implements ProductService{
   private RestTemplate restTemplate;

   public FakeStoreProductService(RestTemplate restTemplate){
       this.restTemplate = restTemplate;
   }
    @Override
    public Product getProductById(Long id) {
        // call Fake Store API to get product with given ID
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProductDTO.class);

        // Convert FakeStoreProductDTO to Product
        if(fakeStoreProductDTO != null)
            return convertFakeStoreDTOToProduct(fakeStoreProductDTO);

        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDTO[] fakeStoreProductDTOS = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);

        List<Product> products = null;
        if (fakeStoreProductDTOS != null){
             products = Arrays.stream(fakeStoreProductDTOS).map(this::convertFakeStoreDTOToProduct)
                    .collect(Collectors.toList());
        }
        return products;
    }

    private Product convertFakeStoreDTOToProduct(FakeStoreProductDTO fakeStoreProductDTO){
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
}
