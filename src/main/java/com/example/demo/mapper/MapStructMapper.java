package com.example.demo.mapper;

import com.example.demo.model.ItemCart;
import com.example.demo.model.Product;
import com.example.demo.model.ShopOrder;
import com.example.demo.model.User;
import com.example.demo.modelDto.*;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
    List<UserDto> usersAll(List<User> users);

    UserGetDto userToUserGetDto(User user);
    User userGetDtoToUser(UserGetDto userGetDto);
    List<UserGetDto> usersGetAll(List<User> users);

    ProductDto productToProductDto(Product product);
    Product productDtoToProduct(ProductDto productDto);
    List<ProductDto> productAll(List<Product> products);

    ItemCartDto itemCartToItemCartDto(ItemCart itemCart);
    ItemCart itemCartDtoToItemCart(ItemCartDto itemCartDto);
    List<ItemCartDto> itemCartAll(List<ItemCart> itemCarts);

    ShopOrderDto shopOrderToShopOrderDto(ShopOrder shopOrder);
    ShopOrder shopOrderDtoToShopOrder(ShopOrderDto shopOrderDto);
    List<ShopOrderDto> shopOrderAll(List<ShopOrder> shopOrders);
}
