package ru.geekbrains.march.market.core.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.ProductDto;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;

    public CartDto getUserCart(String userName) {
        return cartServiceWebClient.get()
                .uri("/api/v1/cart/")
                .header("username", userName)
                .retrieve()
                .bodyToFlux(CartDto.class)
                .blockLast();
    }
}
