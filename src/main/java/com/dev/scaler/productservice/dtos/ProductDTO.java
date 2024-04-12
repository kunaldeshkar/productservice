package com.dev.scaler.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;
}
