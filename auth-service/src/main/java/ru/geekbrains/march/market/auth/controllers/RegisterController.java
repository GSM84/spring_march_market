package ru.geekbrains.march.market.auth.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.march.market.api.RegisterUserDto;
import ru.geekbrains.march.market.api.StringResponse;
import ru.geekbrains.march.market.auth.exceptions.AppError;
import ru.geekbrains.march.market.auth.services.UserService;

@RestController
@RequiredArgsConstructor
@Tag(name = "Регистрация", description = "Методы работы с регистраций новых пользователей")
public class RegisterController {
    private final UserService userService;

    @Operation(
            summary = "Регистрация нового пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "201"
                    ),
                    @ApiResponse(
                            description = "Неуспешный ответ", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerNewUser(@RequestBody RegisterUserDto registerUserDto){
        userService.registerNewUser(registerUserDto);
    }
}