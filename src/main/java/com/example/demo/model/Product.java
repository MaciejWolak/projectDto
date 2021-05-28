package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Size(min = 1,max = 25,message = "Product name is mandatory")
    private String name;
    @Size(max = 250)
    private String description;
    @Min(value = 0L, message = "The value must be positive")
    @Column(nullable = false)
    private Double price;
    private String imageUrl;
/*    @Lob
    @Column(name = "Image")
    private byte[] image;*/
    @Min(value = 1L, message = "The value must be positive")
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


