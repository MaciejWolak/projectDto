package com.example.demo.modelDto;

import com.example.demo.model.Product;

import com.example.demo.model.ShopOrder;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ItemCartDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("product")
    private Product product;
    @JsonProperty("quantity")
    private int quantity;
    @JsonProperty("order")
    private ShopOrder shopOrder;
    @JsonProperty("totalPrice")
    private double totalPrice;
}
