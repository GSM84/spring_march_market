package ru.geekbrains.march.market.api;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель токена")
public class JwtResponse {
    @Schema(description = "Токен", required = true, example = "ASDLAH123LAJKDH)(**@!)(#&*)(!@#*")
    private String token;

    public JwtResponse() {
    }

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
