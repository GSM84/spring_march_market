package ru.geekbrains.march.market.cart.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.cart.integrations.ProductServiceIntegration;
import ru.geekbrains.march.market.cart.utils.Cart;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private final RedisTemplate<String, Object> redisTemplate;

    public Cart getCurrentCart(String cartId){
        if (!redisTemplate.hasKey(cartId)) {
            Cart cart = new Cart();
            redisTemplate.opsForValue().set(cartId, cart);
        }
        return (Cart) redisTemplate.opsForValue().get(cartId);
    }

    public void addToCart(String cartId, Long productId){
        execute(cartId, cart -> {
            ProductDto p = productServiceIntegration.findById(productId);
            cart.add(p);
        });
    }

    public void clearCart(String cartId) {
        execute(cartId, Cart::clear);
    }

    public void increaseItemCount(String cartId, Long productId) {
        execute(cartId, cart -> cart.increaseItemCount(productId));
    }

    public void decreaseItemCount(String cartId, Long productId) {
        execute(cartId, cart -> cart.decreaseItemCount(productId));
    }

    public void mergeGuestCart(String username, String guestCartId) {
        if (getCurrentCart(guestCartId).getItems().size() > 0 ) {
            redisTemplate.opsForValue().set(username, getCurrentCart(guestCartId));
        }
    }

    private void execute(String cartId, Consumer<Cart> action) {
        Cart cart = getCurrentCart(cartId);
        action.accept(cart);
        redisTemplate.opsForValue().set(cartId, cart);
    }
}
