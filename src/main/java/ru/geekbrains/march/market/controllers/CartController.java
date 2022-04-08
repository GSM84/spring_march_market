package ru.geekbrains.march.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.converters.CartConverter;
import ru.geekbrains.march.market.dtos.CartDto;
import ru.geekbrains.march.market.entities.Product;
import ru.geekbrains.march.market.services.CartService;
import ru.geekbrains.march.market.utils.Cart;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping
    public CartDto getCurrentCart() {
        System.out.println("cart requested "+ cartService.getCurrentCart().getItems().size());
        return cartConverter.cartToDto(cartService.getCurrentCart());
    }

    @PostMapping("/add/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void addProductToCartToCart(@PathVariable Long productId){
        cartService.addToCart(productId);
    }

    @PostMapping("/increase/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void increaseItemCount(@PathVariable Long productId){
        cartService.increaseItemCount(productId);
    }

    @PostMapping("/decrease/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void decreaseItemCount(@PathVariable Long productId){
        cartService.decreaseItenCount(productId);
    }

    @PostMapping("/clear")
    @ResponseStatus(HttpStatus.OK)
    public void clearCart(){
        cartService.clearCart();
    }

}
