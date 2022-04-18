package ru.geekbrains.march.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.march.market.api.StringResponse;
import ru.geekbrains.march.market.core.services.OrderService;
import ru.geekbrains.march.market.core.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StringResponse createNewOrder(Principal principal) {
        String username = principal.getName();

        return new StringResponse(orderService.createNewOrder(userService.findByUserName(username).get()).toString());
    }
}
