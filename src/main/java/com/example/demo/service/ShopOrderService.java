package com.example.demo.service;

import com.example.demo.modelDto.ShopOrderDto;

import java.util.List;

public interface ShopOrderService {
    List<ShopOrderDto> getAll();

    ShopOrderDto createNew(ShopOrderDto shopOrderDto);

    ShopOrderDto getOne(long id);

    void delete(long id);

    ShopOrderDto update(ShopOrderDto shopOrderDto);
}
