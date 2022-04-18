package ru.geekbrains.march.market.cart.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.CartItemDto;
import ru.geekbrains.march.market.cart.utils.Cart;


import java.util.stream.Collectors;

@Component
public class CartConverter {

    public CartDto cartToDto(Cart cart) {
        return new CartDto(cart.getTotalPrice(), cart.getItems().stream().map(i -> new CartItemDto(
                i.getProductId(),
                i.getProductTitle(),
                i.getQuantity(),
                i.getPricePerProduct(),
                i.getPrice())).collect(Collectors.toList()));
    }
}
