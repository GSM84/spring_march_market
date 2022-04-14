package ru.geekbrains.march.market.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.dtos.UserDto;
import ru.geekbrains.march.market.entities.User;

@Component
public class UserConverter {
    public UserDto entityToDto(User user){
        return new UserDto(user.getUserName(), user.getEmail());
    }
}
