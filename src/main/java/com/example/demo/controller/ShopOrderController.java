package com.example.demo.controller;


import com.example.demo.modelDto.ShopOrderDto;
import com.example.demo.service.ShopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/")
public class ShopOrderController {
    @Autowired
    ShopOrderService shopOrderService;


    @GetMapping("/shopOrders")
    public ResponseEntity<List<ShopOrderDto>> getShopOrder(){
        List<ShopOrderDto>shopOrders=shopOrderService.getAll();
        if(CollectionUtils.isEmpty(shopOrders)){
            throw new EntityNotFoundException();
        }
        return new ResponseEntity<>(shopOrders, HttpStatus.OK);
    }

    @PostMapping("/shopOrders")
    public ResponseEntity<ShopOrderDto> createNewShopOrder(@Valid @RequestBody ShopOrderDto shopOrder){
        ShopOrderDto newShopOrder = shopOrderService.createNew(shopOrder);
        return new ResponseEntity<>(newShopOrder,HttpStatus.CREATED);
    }

    @GetMapping("/shopOrders/{id}")
    public ResponseEntity<ShopOrderDto>getShopOrder(@PathVariable long id){
        ShopOrderDto shopOrder = shopOrderService.getOne(id);
        return new ResponseEntity<>(shopOrder,HttpStatus.OK);
    }

    @DeleteMapping("/shopOrders/{id}")
    public ResponseEntity<ShopOrderDto>deleteShopOrder(@PathVariable long id){
        shopOrderService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/shopOrders/{id}")
    public ResponseEntity<ShopOrderDto>updateShopOrder(@PathVariable long id,@RequestBody ShopOrderDto shopOrder){
        ShopOrderDto newShopOrder = shopOrderService.getOne(id);
        newShopOrder.setUser(newShopOrder.getUser());
        shopOrderService.update(newShopOrder);
        return new ResponseEntity<>(shopOrder,HttpStatus.OK);
    }
}
