package ru.otus.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.domain.Chat;
import ru.otus.domain.User;
import ru.otus.dto.ChatDto;
import ru.otus.dto.UserDto;
import ru.otus.security.IAuthenticationFacade;
import ru.otus.service.UserService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users/all")
    public List<UserDto> getUsersByNicknameContaining(@RequestParam("nickname") String nickname) {
        Iterable<User> chats = userService.getAllUsersStartsWithNickname(nickname);
        return StreamSupport.stream(chats.spliterator(), false)
                .map(UserDto::new)
                .collect(Collectors.toList());
    }
}
