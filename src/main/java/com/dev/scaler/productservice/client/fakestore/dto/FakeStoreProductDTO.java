package com.dev.scaler.productservice.client.fakestore.dto;

import com.dev.scaler.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;
}
