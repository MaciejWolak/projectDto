package com.example.demo.service.impl;


import com.example.demo.mapper.MapStructMapper;
import com.example.demo.model.ShopOrder;
import com.example.demo.modelDto.ShopOrderDto;
import com.example.demo.repository.ShopOrderRepository;
import com.example.demo.service.ShopOrderService;
import lombok.Data;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ShopOrderServiceImpl implements ShopOrderService {

    @Autowired
    private ShopOrderRepository shopOrderRepository;

    private MapStructMapper mapper = Mappers.getMapper(MapStructMapper.class);

    @Override
    public List<ShopOrderDto> getAll() {
        return mapper.shopOrderAll(shopOrderRepository.findAll());
    }

    @Override
    public ShopOrderDto createNew(ShopOrderDto shopOrderDto) {
        ShopOrder shopOrder = mapper.shopOrderDtoToShopOrder(shopOrderDto);
        shopOrder= shopOrderRepository.save(shopOrder);
        return mapper.shopOrderToShopOrderDto(shopOrder);
    }

    @Override
    public ShopOrderDto getOne(long id) {
        return mapper.shopOrderToShopOrderDto(shopOrderRepository.findById(id).orElseThrow());
    }

    @Override
    public void delete(long id) {
        ShopOrder shopOrder = shopOrderRepository.findById(id).orElseThrow();
        shopOrderRepository.delete(shopOrder);
    }

    @Override
    public ShopOrderDto update(ShopOrderDto shopOrderDto) {
        ShopOrder shopOrder = shopOrderRepository.findById(shopOrderDto.getId()).orElseThrow();
        ShopOrder update = mapper.shopOrderDtoToShopOrder(shopOrderDto);
        shopOrder.setUser(update.getUser());
        shopOrder.setCreateDateTime(update.getCreateDateTime());
        shopOrder= shopOrderRepository.save(shopOrder);
        return mapper.shopOrderToShopOrderDto(shopOrder);
    }

}
