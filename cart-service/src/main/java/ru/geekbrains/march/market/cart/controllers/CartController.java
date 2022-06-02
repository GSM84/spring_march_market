package ru.geekbrains.march.market.cart.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.StringResponse;
import ru.geekbrains.march.market.cart.converters.CartConverter;
import ru.geekbrains.march.market.cart.exceptions.AppError;
import ru.geekbrains.march.market.cart.services.CartService;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Tag(name = "Корзина", description = "Методы работы с корзиной")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @Operation(
            summary = "Создание идентификатора корзины гостя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StringResponse.class))
                    )
            }
    )
    @GetMapping("/generate_id")
    public StringResponse generateGuestCartId() {
        return new StringResponse(UUID.randomUUID().toString());
    }

    @Operation(
            summary = "Получение текущей корзины",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = CartDto.class))
                    )
            }
    )
    @GetMapping
    public CartDto getCurrentCart(@RequestHeader(required = false) String username, @RequestParam(name = "guestCartId", required = false) String guestCartId) {
        String currentCartId = selectCartId(username, guestCartId);
        return cartConverter.cartToDto(cartService.getCurrentCart(currentCartId));
    }

    @Operation(
            summary = "Добавить продукт в корзину",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Неуспешный ответ", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    @PostMapping("/{guestCartId}/add/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void addProductToCartToCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId, @PathVariable Long productId){
        String currentCartId = selectCartId(username, guestCartId);
        cartService.addToCart(currentCartId, productId);
    }

    @Operation(
            summary = "Увеличить количество для продукта с ID",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    @PostMapping("/{guestCartId}/increase/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void increaseItemCount(@RequestHeader(required = false) String username, @PathVariable String guestCartId,@PathVariable Long productId){
        String currentCartId = selectCartId(username, guestCartId);
        cartService.increaseItemCount(currentCartId, productId);
    }

    @Operation(
            summary = "Уменьшить количество для продукта с ID",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    @PostMapping("/{guestCartId}/decrease/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void decreaseItemCount(@RequestHeader(required = false) String username, @PathVariable String guestCartId,@PathVariable Long productId){
        String currentCartId = selectCartId(username, guestCartId);
        cartService.decreaseItemCount(currentCartId, productId);
    }

    @Operation(
            summary = "Очистить корзину",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    @PostMapping("/clear")
    @ResponseStatus(HttpStatus.OK)
    public void clearCart(@RequestHeader(required = false) String username, @RequestParam(name = "guestCartId", required = false) String guestCartId){
        String currentCartId = selectCartId(username, guestCartId);
        cartService.clearCart(currentCartId);
    }

    private String selectCartId(String username, String guestCartId) {
        if (username != null) {
            cartService.mergeGuestCart(username, guestCartId);
        }
        return username != null?username:guestCartId;
    }
}
