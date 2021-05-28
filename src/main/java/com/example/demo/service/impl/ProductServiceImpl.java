package com.example.demo.service.impl;


import com.example.demo.mapper.MapStructMapper;
import com.example.demo.model.Product;
import com.example.demo.modelDto.ProductDto;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import lombok.Data;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    private MapStructMapper mapper = Mappers.getMapper(MapStructMapper.class);

    @Override
    public List<ProductDto> getAll() {
        return mapper.productAll(productRepository.findAll());
    }


    @Override
    public ProductDto createNew(ProductDto newProduct) {
        Product product = mapper.productDtoToProduct(newProduct);
        product = productRepository.save(product);
        return mapper.productToProductDto(product);
    }

    @Override
    public ProductDto getOne(long id) {
        return mapper.productToProductDto(productRepository.findById(id).orElseThrow());
    }

    @Override
    public void delete(long id) {
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
    }

    @Override
    public ProductDto update(ProductDto updateProduct) {
        Product product = productRepository.findById(updateProduct.getId()).orElseThrow();
        Product update = mapper.productDtoToProduct(updateProduct);
        product.setName(update.getName());
        product.setDescription(update.getDescription());
        product.setPrice(update.getPrice());
        product.setQuantity(update.getQuantity());
        product.setImageUrl(update.getImageUrl());
        product = productRepository.save(product);
        return mapper.productToProductDto(product);
    }


}
