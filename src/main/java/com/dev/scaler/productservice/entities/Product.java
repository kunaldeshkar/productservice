package com.dev.scaler.productservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String image;
    @ManyToOne
    private Category category;
}
