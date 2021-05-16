package com.example.demo.service;


import com.example.demo.modelDto.ItemCartDto;

import java.util.List;

public interface ItemCartService {
    List<ItemCartDto> getAll();

    ItemCartDto createNew(ItemCartDto itemCartDto);

    ItemCartDto getOne(long id);

    void delete(long id);

    ItemCartDto update(ItemCartDto itemCartDto);

}
