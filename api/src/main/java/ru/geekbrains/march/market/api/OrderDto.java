package ru.geekbrains.march.market.api;

import java.math.BigDecimal;
import java.util.List;


public class OrderDto {
    private Long id;
    private BigDecimal totalPrice;
    private List<OrderItemDto> items;
    private String address;
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
