package ru.geekbrains.march.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.DeliveryDetailsDto;
import ru.geekbrains.march.market.api.ResourceNotFoundException;
import ru.geekbrains.march.market.core.entities.Order;
import ru.geekbrains.march.market.core.entities.OrderItem;
import ru.geekbrains.march.market.core.integrations.CartServiceIntegration;
import ru.geekbrains.march.market.core.repositories.OrderRepository;
import ru.geekbrains.march.market.core.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartServiceIntegration cartServiceIntegration;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void createNewOrder(String userName, DeliveryDetailsDto deliveryDetails) {

        CartDto cartDto = cartServiceIntegration.getUserCart(userName);
        if (cartDto.getItems().isEmpty()) {
            throw new IllegalStateException("Нельзя оформить заказ для пустой корзины!");
        }
        Order order = new Order();
        order.setUsername(userName);
        order.setTotalPrice(cartDto.getTotalPrice());
        if (deliveryDetails != null) {
            order.setAddress(deliveryDetails.getAddress());
            order.setPhone(deliveryDetails.getPhone());
        }

        order.setItems(new ArrayList<>());
        cartDto.getItems().forEach(ci -> {
            OrderItem item = new OrderItem();
            item.setProduct(productRepository.findById(ci.getProductId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Продукт с идентификатором %s не найден.", ci.getProductId()))));
            item.setPricePerProduct(ci.getPricePerProduct());
            item.setPrice(ci.getPrice());
            item.setQuantity(ci.getQuantity());
            order.getItems().add(item);
            item.setOrder(order);
        });

        orderRepository.save(order);
        cartServiceIntegration.clearCart(userName);
    }

    public List<Order> findUserOrders(String userName) {
        return orderRepository.findAllByUsername(userName);
    }
}
