package com.example.demo.modelDto;

import com.example.demo.model.ItemCart;
import com.example.demo.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ShopOrderDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("createDateTime")
    private LocalDateTime createDateTime;
    @JsonProperty("updateDateTime")
    private LocalDateTime updateDateTime;
    @JsonProperty("user")
    private User user;
    @JsonProperty("grandTotalPrice")
    private double grandTotalPrice;

}
