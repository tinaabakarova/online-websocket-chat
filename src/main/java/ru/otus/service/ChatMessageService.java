package ru.otus.service;

import ru.otus.domain.ChatMessage;
import ru.otus.domain.MessageStatus;

import java.util.Optional;

public interface ChatMessageService {
    Iterable<ChatMessage> getAll(String chatId);

    ChatMessage save(ChatMessage message);

    long countByChatIdAndStatus(String chatId, MessageStatus status);

    Optional<ChatMessage> findById(long id);
}
