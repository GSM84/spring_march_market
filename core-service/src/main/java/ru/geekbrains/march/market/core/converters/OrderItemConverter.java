package ru.geekbrains.march.market.core.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.api.OrderItemDto;
import ru.geekbrains.march.market.core.entities.OrderItem;

@Component
public class OrderItemConverter {
    public OrderItemDto entityToDto(OrderItem oi) {
        return new OrderItemDto(
                oi.getProduct().getId()
                , oi.getProduct().getTitle()
                , oi.getQuantity()
                , oi.getPricePerProduct()
                , oi.getPrice());
    }
}
