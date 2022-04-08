package ru.geekbrains.march.market.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.dtos.CartDto;
import ru.geekbrains.march.market.dtos.CartItemDto;
import ru.geekbrains.march.market.utils.Cart;

import java.util.stream.Collectors;

@Component
public class CartConverter {

    public CartDto cartToDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setTotalPrice(cart.getTotalPrice());
        cartDto.setItems(cart.getItems().stream().map(i -> new CartItemDto(
                i.getProductId(),
                i.getProductTitle(),
                i.getQuantity(),
                i.getPricePerProduct(),
                i.getPrice()
        )).collect(Collectors.toList()));

        return  cartDto;
    }
}
