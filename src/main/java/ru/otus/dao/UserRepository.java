package ru.otus.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.domain.User;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByName(String name);
    Boolean existsByName(String name);
    Boolean existsByEmail(String email);
    Optional<User> findByLogin(String login);
    Iterable<User> findByNicknameStartingWith(String nickname);
}
