package ru.otus.stompController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.domain.*;
import ru.otus.dto.ChatMessageDto;
import ru.otus.exception.EntityNotFoundException;
import ru.otus.service.ChatMessageService;
import ru.otus.service.ChatService;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;
    private final ChatService chatService;

    @Autowired
    public ChatController(SimpMessagingTemplate messagingTemplate, ChatMessageService chatMessageService, ChatService chatService) {
        this.messagingTemplate = messagingTemplate;
        this.chatMessageService = chatMessageService;
        this.chatService = chatService;
    }

    @MessageMapping("/chat")
    public ChatMessage processMessage(@Payload ChatMessageDto chatMessageDto) {
        String chat_id = chatService.getChatIdBySenderAndRecipient(chatMessageDto.getSenderId(), chatMessageDto.getRecipientId());

        ChatMessage message = ChatMessage.builder()
                .senderId(chatMessageDto.getSenderId())
                .recipientId(chatMessageDto.getRecipientId())
                .body(chatMessageDto.getBody())
                .chatId(chat_id)
                .status(MessageStatus.DELIVERED)
                .build();

        chatMessageService.save(message);
        messagingTemplate.convertAndSendToUser(String.valueOf(message.getRecipientId()),
                "/" + message.getSenderId() + "/queue/messages",
                          message);
        return message;
    }
}
