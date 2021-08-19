package ru.otus.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.domain.ChatMessage;
import ru.otus.domain.MessageStatus;


public interface ChatMessageRepository extends CrudRepository<ChatMessage, Long> {
    Iterable<ChatMessage> findAllByChatId(String chatId);

    long countByChatIdAndStatus(String chatId, MessageStatus status);
}