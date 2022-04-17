package ru.geekbrains.march.market.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.march.market.utils.Cart;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {

    private Long productId;
    private String productTitle;
    private int quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;

}