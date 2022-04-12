package ru.geekbrains.march.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Data
public class CartDto {
    private BigDecimal totalPrice;
    private List<CartItemDto> items;
}
