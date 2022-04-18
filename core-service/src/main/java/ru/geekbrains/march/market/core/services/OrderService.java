package ru.geekbrains.march.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.core.converters.OrderConverter;
import ru.geekbrains.march.market.core.entities.Order;
import ru.geekbrains.march.market.core.entities.OrderItem;
import ru.geekbrains.march.market.core.entities.User;
import ru.geekbrains.march.market.core.integrations.OrderServiceIntegration;
import ru.geekbrains.march.market.core.repositories.OrderRepository;
import ru.geekbrains.march.market.core.repositories.ProductRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderServiceIntegration orderServiceIntegration;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderConverter orderConverter;

    public Long createNewOrder(User user) {
        CartDto cartDto = orderServiceIntegration.getCurrentCart();
        Long oredrId = null;
        if (!cartDto.getTotalPrice().equals(0)) {
            Order order = orderConverter.dtoToEntity(cartDto, user);
            order.setItems(
            cartDto.getItems().stream().map(i ->
                {
                   OrderItem item = new OrderItem();
                   item.setProduct(productRepository.getById(i.getProductId()));
                   item.setPricePerProduct(i.getPricePerProduct());
                   item.setPrice(i.getPrice());
                   item.setQuantity(i.getQuantity());
                   item.setOrder(order);

                   return item;
                }).collect(Collectors.toList()));
            oredrId = orderRepository.save(order).getId();
           }
            return oredrId;
        }
}
