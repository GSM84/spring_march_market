package ru.geekbrains.march.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.ResourceNotFoundException;
import ru.geekbrains.march.market.core.entities.Order;
import ru.geekbrains.march.market.core.entities.OrderItem;
import ru.geekbrains.march.market.core.entities.User;
import ru.geekbrains.march.market.core.integrations.OrderServiceIntegration;
import ru.geekbrains.march.market.core.repositories.OrderRepository;
import ru.geekbrains.march.market.core.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderServiceIntegration orderServiceIntegration;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserService userService;


    @Transactional
    public Long createNewOrder(String userName) {
        CartDto cartDto = orderServiceIntegration.getCurrentCart();
        Long oredrId = null;
        if (!cartDto.getTotalPrice().equals(0)) {
            User user = userService.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь %s не найден.", userName)));

            Order order = new Order();
            order.setUser(user);
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
