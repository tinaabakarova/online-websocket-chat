package ru.otus.service;

import ru.otus.domain.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getUserByLogin(String login);

    Optional<User> getUserById(long id);

    Iterable<User> getAllUsersStartsWithNickname(String nickname);
}
