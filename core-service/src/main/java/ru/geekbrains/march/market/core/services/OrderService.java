package ru.geekbrains.march.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.ResourceNotFoundException;
import ru.geekbrains.march.market.core.entities.Order;
import ru.geekbrains.march.market.core.entities.OrderItem;
import ru.geekbrains.march.market.core.integrations.CartServiceIntegration;
import ru.geekbrains.march.market.core.repositories.OrderRepository;
import ru.geekbrains.march.market.core.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartServiceIntegration orderServiceIntegration;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Long createNewOrder(String userName) {
        CartDto cartDto = orderServiceIntegration.getUserCart(userName);
        Long oredrId = null;
        if (!cartDto.getTotalPrice().equals(0)) {

            Order order = new Order();
            order.setUsername(userName);
            order.setTotalPrice(cartDto.getTotalPrice());
            order.setItems(
            cartDto.getItems().stream().map(i ->
                {
                   OrderItem item = new OrderItem();
                   item.setProduct(productRepository.findById(i.getProductId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Продукт с идентификатором %s не найден.", i.getProductId()))));

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
