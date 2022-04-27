package ru.geekbrains.march.market.auth.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.march.market.auth.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
}
