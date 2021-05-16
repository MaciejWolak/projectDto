package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ItemCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Product product;

    private int quantity;

    @ManyToOne
    @JoinColumn
    private ShopOrder shopOrder;

    @Transient
    public double getTotalPrice() {
        return getProduct().getPrice() * getQuantity();
    }


}
