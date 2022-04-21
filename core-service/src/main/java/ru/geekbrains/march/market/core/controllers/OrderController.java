package ru.geekbrains.march.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.api.StringResponse;
import ru.geekbrains.march.market.core.services.OrderService;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StringResponse createNewOrder(Principal principal) {
        System.out.println("oredr request");
        return new StringResponse(orderService.createNewOrder(principal.getName()).toString());
    }
}
