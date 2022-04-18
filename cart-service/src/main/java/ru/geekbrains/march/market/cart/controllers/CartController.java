package ru.geekbrains.march.market.cart.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.cart.converters.CartConverter;
import ru.geekbrains.march.market.cart.services.CartService;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping
    public CartDto getCurrentCart() {
        return cartConverter.cartToDto(cartService.getCurrentCart());
    }

    @PostMapping("/add/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void addProductToCartToCart(@PathVariable Long productId){
        System.out.println("add to cart");
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
