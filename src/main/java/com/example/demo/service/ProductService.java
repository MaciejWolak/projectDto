package com.example.demo.service;


import com.example.demo.modelDto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();

    ProductDto createNew(ProductDto newProduct);

    ProductDto getOne(long id);

    void delete(long id);

    ProductDto update(ProductDto updateProduct);

}
