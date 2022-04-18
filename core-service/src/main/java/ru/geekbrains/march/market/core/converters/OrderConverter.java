package ru.geekbrains.march.market.core.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.core.entities.Order;
import ru.geekbrains.march.market.core.entities.User;

@Component
public class OrderConverter {

    public Order dtoToEntity(CartDto cartDto, User user) {
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(cartDto.getTotalPrice());
        return  order;
    }
}
