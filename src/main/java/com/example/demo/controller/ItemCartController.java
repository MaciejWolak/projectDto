package com.example.demo.controller;



import com.example.demo.modelDto.ItemCartDto;
import com.example.demo.service.ItemCartService;
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
public class ItemCartController {
@Autowired
private ItemCartService itemCartService;

    @GetMapping("/itemCarts")
    public ResponseEntity<List<ItemCartDto>> getItemCarts(){
        List<ItemCartDto>itemCarts=itemCartService.getAll();
        if(CollectionUtils.isEmpty(itemCarts)){
            throw new EntityNotFoundException();
        }
        return new ResponseEntity<>(itemCarts, HttpStatus.OK);
    }

    @PostMapping("/itemCarts")
    public ResponseEntity<ItemCartDto> createNewItemCart(@Valid @RequestBody ItemCartDto itemCart){
        ItemCartDto newItemCart = itemCartService.createNew(itemCart);
        return new ResponseEntity<>(newItemCart,HttpStatus.CREATED);
    }

    @GetMapping("/itemCarts/{id}")
    public ResponseEntity<ItemCartDto>getItemCart(@PathVariable long id){
        ItemCartDto itemCart = itemCartService.getOne(id);
        return new ResponseEntity<>(itemCart,HttpStatus.OK);
    }

    @DeleteMapping("/itemCarts/{id}")
    public ResponseEntity<ItemCartDto>deleteItemCart(@PathVariable long id){
        itemCartService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/itemCarts/{id}")
    public ResponseEntity<ItemCartDto>updateItemCart(@PathVariable long id,@RequestBody ItemCartDto itemCart){
        ItemCartDto newItemCart = itemCartService.getOne(id);
        newItemCart.setQuantity(itemCart.getQuantity());
        newItemCart.setProduct(itemCart.getProduct());
        itemCartService.update(newItemCart);
        return new ResponseEntity<>(itemCart,HttpStatus.OK);
    }
}
