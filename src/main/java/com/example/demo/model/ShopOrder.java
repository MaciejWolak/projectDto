package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class ShopOrder {
    @Id
    @GeneratedValue
    private Long id;

    @CreationTimestamp
    private LocalDateTime createDateTime;
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    @ManyToOne
    @JoinColumn
    private User user;


    @JsonIgnore
    @OneToMany(mappedBy = "shopOrder",cascade = CascadeType.ALL)
    private List<ItemCart> itemCarts;

    @Transient
    public Double getGrandTotalPrice() {
        double sum = 0D;
        List<ItemCart> itemCarts = getItemCarts();
        if (itemCarts == null){
            return 0.0;
        }
        else
        for (ItemCart op : itemCarts) {
            sum += op.getTotalPrice();
        }
        return sum;
    }


}
