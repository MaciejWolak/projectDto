package com.example.demo.controller;


import com.example.demo.modelDto.ProductDto;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/")
public class ProductController {



    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getProducts(){
        List<ProductDto> products=productService.getAll();
        if(CollectionUtils.isEmpty(products)){
            throw new EntityNotFoundException();
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDto>createNewProduct(@Valid @RequestBody ProductDto newProduct) throws IOException {

        ProductDto persistedProduct = productService.createNew(newProduct);
        return new ResponseEntity<>(persistedProduct,HttpStatus.CREATED);
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto>getProduct(@PathVariable long id){
        ProductDto productDto = productService.getOne(id);
        return new ResponseEntity<>(productDto,HttpStatus.OK);
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<ProductDto>deleteProduct(@PathVariable long id){
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDto>updateProduct(@PathVariable long id,@Valid @RequestBody ProductDto product){
        ProductDto newProduct = productService.getOne(id);
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setPrice(product.getPrice());
        newProduct.setQuantity(product.getQuantity());
        newProduct.setImageUrl(product.getImageUrl());
        productService.update(newProduct);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }


}
