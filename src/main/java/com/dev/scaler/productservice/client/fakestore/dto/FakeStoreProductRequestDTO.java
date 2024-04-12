package com.dev.scaler.productservice.client.fakestore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductRequestDTO {
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;
}
