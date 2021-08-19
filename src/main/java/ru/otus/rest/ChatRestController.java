package ru.otus.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.otus.domain.Chat;
import ru.otus.domain.User;
import ru.otus.dto.ChatDto;
import ru.otus.exception.EntityNotFoundException;
import ru.otus.security.IAuthenticationFacade;
import ru.otus.service.ChatService;
import ru.otus.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class ChatRestController {
    private final IAuthenticationFacade authenticationFacade;

    private final ChatService chatService;
    private final UserService userService;

    @Autowired
    public ChatRestController(ChatService chatService, UserService userService, IAuthenticationFacade authenticationFacade) {
        this.chatService = chatService;
        this.userService = userService;
        this.authenticationFacade = authenticationFacade;
    }

    @GetMapping("/api/chat/all")
    public List<ChatDto> getAllChats() {
        String userName = ((UserDetails) authenticationFacade.getAuthentication().getPrincipal()).getUsername();
        Iterable<Chat> chats = chatService.getAllByUserName(userName);
        return StreamSupport.stream(chats.spliterator(), false)
                .map(ChatDto::new)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/api/chat")
    public void deleteChatById(@RequestParam("id") Long recipientId) {
        Map<String, User> users = getSenderAndRecipientByRecipientId(recipientId);
        User sender = users.get("sender");
        User recipient = users.get("recipient");

        String chatIdBySenderAndRecipient = chatService.createChatIdBySenderAndRecipient(sender.getLogin(), recipient.getLogin());
        chatService.deleteAllChatByChatId(chatIdBySenderAndRecipient);
    }

    @PostMapping( "/api/chat")
    public void saveChat(@RequestParam("id")  long recipientId) {
        Map<String, User> users = getSenderAndRecipientByRecipientId(recipientId);
        User sender = users.get("sender");
        User recipient = users.get("recipient");

        String chatIdBySenderAndRecipient = chatService.createChatIdBySenderAndRecipient(sender.getLogin(), recipient.getLogin());

        chatService.createOrGetChat(chatIdBySenderAndRecipient, sender, recipient);
    }

    private Map<String, User> getSenderAndRecipientByRecipientId(long recipientId) {
        String login = ((UserDetails) authenticationFacade.getAuthentication().getPrincipal()).getUsername();

        User sender = userService.getUserByLogin(login).orElseThrow(EntityNotFoundException::new);
        User recipient = userService.getUserById(recipientId).orElseThrow(EntityNotFoundException::new);

        return new HashMap<String, User>(){{
            put("sender", sender);
            put("recipient", recipient);
        }};
    }
}

