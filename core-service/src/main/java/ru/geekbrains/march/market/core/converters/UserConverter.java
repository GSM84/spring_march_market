package ru.geekbrains.march.market.core.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.api.UserDto;
import ru.geekbrains.march.market.core.entities.User;

@Component
public class UserConverter {
    public UserDto entityToDto(User user){
        return new UserDto(user.getUserName(), user.getEmail());
    }
}
