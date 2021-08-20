package ru.otus.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.domain.ChatMessage;
import ru.otus.domain.User;
import ru.otus.dto.ChatMessageDto;
import ru.otus.exception.EntityNotFoundException;
import ru.otus.security.IAuthenticationFacade;
import ru.otus.service.ChatMessageService;
import ru.otus.service.ChatService;
import ru.otus.service.UserService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class ChatMessagesRestController {
    private final ChatMessageService chatMessageService;
    private final ChatService chatService;

    @Autowired
    public ChatMessagesRestController(ChatMessageService chatMessageService,
                                      ChatService chatService) {
        this.chatMessageService = chatMessageService;
        this.chatService = chatService;
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public Iterable<ChatMessageDto> findChatMessages (@PathVariable long senderId, @PathVariable long recipientId) {
        String chat_id = chatService.getChatIdBySenderAndRecipient(senderId, recipientId);
        return StreamSupport.stream(chatMessageService.getAll(chat_id).spliterator(), false)
                .map(ChatMessageDto::new)
                .collect(Collectors.toList());
    }
}
