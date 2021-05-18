package com.example.demo.modelDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("quantity")
    private int quantity;
    @JsonProperty("currentTotalUnitsInStock")
    private int currentTotalUnitsInStock;
    @JsonProperty("totalUnitsInOrder")
    private int totalUnitsInOrder;
}
