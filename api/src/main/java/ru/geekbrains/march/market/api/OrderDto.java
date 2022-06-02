package ru.geekbrains.march.market.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Модель заказа")
public class OrderDto {
    @Schema(description = "Идентификатор заказа", required = true, example = "12")
    private Long id;
    @Schema(description = "Итоговая стоимость", required = true, example = "1234.12")
    private BigDecimal totalPrice;
    @Schema(description = "Список позиций", required = true)
    private List<OrderItemDto> items;
    @Schema(description = "Адрес доставки", required = false, example = "Москва")
    private String address;
    @Schema(description = "Контактный телефон", required = false, example = "9276010230")
    private String phone;

    public OrderDto() {
    }

    public OrderDto(Long id, BigDecimal totalPrice, List<OrderItemDto> items, String address, String phone) {
        this.totalPrice = totalPrice;
        this.items = items;
        this.id = id;
        this.address = address;
        this.phone = phone;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
