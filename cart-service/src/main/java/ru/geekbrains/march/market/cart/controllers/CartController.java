package ru.geekbrains.march.market.cart.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.StringResponse;
import ru.geekbrains.march.market.cart.converters.CartConverter;
import ru.geekbrains.march.market.cart.services.CartService;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/generate_id")
    public StringResponse generateGuestCartId() {
        return new StringResponse(UUID.randomUUID().toString());
    }

    @GetMapping
    public CartDto getCurrentCart(@RequestHeader(required = false) String username, @RequestParam(name = "guestCartId", required = false) String guestCartId) {
        String currentCartId = selectCartId(username, guestCartId);
        return cartConverter.cartToDto(cartService.getCurrentCart(currentCartId));
    }

    @PostMapping("/{guestCartId}/add/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void addProductToCartToCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId, @PathVariable Long productId){
        String currentCartId = selectCartId(username, guestCartId);
        cartService.addToCart(currentCartId, productId);
    }

    @PostMapping("/{guestCartId}/increase/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void increaseItemCount(@RequestHeader(required = false) String username, @PathVariable String guestCartId,@PathVariable Long productId){
        String currentCartId = selectCartId(username, guestCartId);
        cartService.increaseItemCount(currentCartId, productId);
    }

    @PostMapping("/{guestCartId}/decrease/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void decreaseItemCount(@RequestHeader(required = false) String username, @PathVariable String guestCartId,@PathVariable Long productId){
        String currentCartId = selectCartId(username, guestCartId);
        cartService.decreaseItemCount(currentCartId, productId);
    }

    @PostMapping("/clear")
    @ResponseStatus(HttpStatus.OK)
    public void clearCart(@RequestHeader(required = false) String username, @RequestParam(name = "guestCartId", required = false) String guestCartId){
        String currentCartId = selectCartId(username, guestCartId);
        cartService.clearCart(currentCartId);
    }

    private String selectCartId(String username, String guestCartId) {
        return username != null?username:guestCartId;
    }

}
