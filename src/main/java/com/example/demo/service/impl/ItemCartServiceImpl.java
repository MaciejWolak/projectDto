package com.example.demo.service.impl;


import com.example.demo.mapper.MapStructMapper;
import com.example.demo.model.ItemCart;
import com.example.demo.modelDto.ItemCartDto;
import com.example.demo.repository.ItemCartRepository;
import com.example.demo.service.ItemCartService;
import lombok.Data;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ItemCartServiceImpl implements ItemCartService {

    @Autowired
    private ItemCartRepository itemCartRepository;

    private MapStructMapper mapper = Mappers.getMapper(MapStructMapper.class);

    @Override
    public List<ItemCartDto> getAll() {
        return mapper.itemCartAll(itemCartRepository.findAll());
    }

    @Override
    public ItemCartDto createNew(ItemCartDto itemCartDto) {
        ItemCart itemCart = mapper.itemCartDtoToItemCart(itemCartDto);
        itemCart = itemCartRepository.save(itemCart);
        return mapper.itemCartToItemCartDto(itemCart);
    }

    @Override
    public ItemCartDto getOne(long id) {
        return mapper.itemCartToItemCartDto(itemCartRepository.findById(id).orElseThrow());
    }

    @Override
    public void delete(long id) {
        ItemCart itemCart = itemCartRepository.findById(id).orElseThrow();
        itemCartRepository.delete(itemCart);
    }

    @Override
    public ItemCartDto update(ItemCartDto itemCartDto) {
        ItemCart itemCart = itemCartRepository.findById(itemCartDto.getId()).orElseThrow();
        ItemCart update = mapper.itemCartDtoToItemCart(itemCartDto);
        itemCart.setProduct(update.getProduct());
        itemCart = itemCartRepository.save(itemCart);
        return mapper.itemCartToItemCartDto(itemCart);
    }

}
