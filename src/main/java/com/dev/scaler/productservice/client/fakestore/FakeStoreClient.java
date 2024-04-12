package com.dev.scaler.productservice.client.fakestore;

import com.dev.scaler.productservice.client.fakestore.dto.FakeStoreProductRequestDTO;
import com.dev.scaler.productservice.constants.Constant;
import com.dev.scaler.productservice.client.fakestore.dto.FakeStoreProductDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class FakeStoreClient {
    private RestTemplate restTemplate;

    public FakeStoreClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public Optional<FakeStoreProductDTO> getProductById(Long id){
          FakeStoreProductDTO fakeStoreProductDTO =
                restTemplate.getForObject(Constant.FAKE_STORE_API_BASE_URL+"/{id}", FakeStoreProductDTO.class, id);

        return Optional.ofNullable(fakeStoreProductDTO);
    }

    public Optional<FakeStoreProductDTO[]> getAllProducts(){
        FakeStoreProductDTO[] fakeStoreProductDTOs =
                restTemplate.getForObject(Constant.FAKE_STORE_API_BASE_URL, FakeStoreProductDTO[].class);

        return Optional.ofNullable(fakeStoreProductDTOs);
    }

    public Optional<FakeStoreProductDTO> updateProduct(Long id, FakeStoreProductRequestDTO fakeStoreProductRequestDTO){
        HttpEntity<FakeStoreProductRequestDTO> httpEntity = new HttpEntity<>(fakeStoreProductRequestDTO);

        ResponseEntity<FakeStoreProductDTO> responseEntity
                = restTemplate.exchange(Constant.FAKE_STORE_API_BASE_URL+"/"+id, HttpMethod.PUT, httpEntity, FakeStoreProductDTO.class);

        return Optional.ofNullable(responseEntity.getBody());
    }

    public Optional<FakeStoreProductDTO> createProduct(FakeStoreProductRequestDTO fakeStoreProductRequestDTO) {
        FakeStoreProductDTO fakeStoreProductDTO
                = restTemplate.postForObject(Constant.FAKE_STORE_API_BASE_URL, fakeStoreProductRequestDTO, FakeStoreProductDTO.class);

        return Optional.ofNullable(fakeStoreProductDTO);
    }

    public Optional<FakeStoreProductDTO> deleteProduct(Long id) {
        ResponseEntity<FakeStoreProductDTO> responseEntity
                = restTemplate.exchange(Constant.FAKE_STORE_API_BASE_URL + "/" + id, HttpMethod.DELETE, null, FakeStoreProductDTO.class);

        return Optional.ofNullable(responseEntity.getBody());
    }
}
