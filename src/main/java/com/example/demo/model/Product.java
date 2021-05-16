package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private Double price;
    //private byte[] image;
    @Column(nullable = false)
    private int quantity;

    @JsonIgnore
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<ItemCart> itemCarts;


    @Transient
    public int getTotalUnitsInOrder() {
        int sum = 0;
        List<ItemCart> itemCarts = getItemCarts();
        if (itemCarts == null){
            return 0;
        }
        else
        for (ItemCart op : itemCarts){
            sum += op.getQuantity();
        }
        return sum;
    }

    @Transient
    public int getCurrentTotalUnitsInStock(){
        return getQuantity()-getTotalUnitsInOrder();
    }
}


