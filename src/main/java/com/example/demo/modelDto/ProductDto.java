package com.example.demo.modelDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class ProductDto {
    @JsonProperty("id")
    private Long id;
    @Size(min = 1, max = 25, message = "Product name is mandatory")
    @JsonProperty("name")
    private String name;
    @Size(max = 250)
    @JsonProperty("description")
    private String description;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("quantity")
    private int quantity;
    @JsonProperty("totalUnitsInOrder")
    private int totalUnitsInOrder;
/*   @JsonProperty("image")
    private byte[] image;*/
    @JsonProperty("currentTotalUnitsInStock")
    private int currentTotalUnitsInStock;
    @JsonProperty("imageUrl")
    private String imageUrl;
}
