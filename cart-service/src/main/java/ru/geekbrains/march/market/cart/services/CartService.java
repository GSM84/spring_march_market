package ru.geekbrains.march.market.cart.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.cart.integrations.ProductServiceIntegration;
import ru.geekbrains.march.market.cart.utils.Cart;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartService {
    private Cart cart;
    private final ProductServiceIntegration productServiceIntegration;

    @PostConstruct
    public void init(){
        cart = new Cart();
        cart.setItems(new ArrayList<>());
    }

    public Cart getCurrentCart(){
        return cart;
    }

    public void addToCart(Long productId){
        ProductDto p = productServiceIntegration.findById(productId);
        cart.add(p);
    }

    public void clearCart() {
        cart.clear();
    }

    public void increaseItemCount(Long productId) {
        cart.increaseItemCount(productId);
    }

    public void decreaseItenCount(Long productId) {
        cart.decreaseItemCount(productId);
    }
}