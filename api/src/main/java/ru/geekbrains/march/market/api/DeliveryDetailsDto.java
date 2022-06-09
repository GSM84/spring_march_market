package ru.geekbrains.march.market.api;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель адреса доставки")
public class DeliveryDetailsDto {
    @Schema(description = "Адрес доставки", required = false, example = "Москва")
    private String address;

    @Schema(description = "Контактный телефон", required = false, example = "92760123912")
    private String phone;

    public DeliveryDetailsDto() {
    }

    public DeliveryDetailsDto(String address, String phone) {
        this.address = address;
        this.phone = phone;
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
