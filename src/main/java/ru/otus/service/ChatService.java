package ru.otus.service;

import ru.otus.domain.Chat;
import ru.otus.domain.User;

import java.util.Optional;

public interface ChatService {
    String createOrGetChat(String chatId, User sender, User recipient);

    Iterable<Chat> getAllByUserName(String userName);

    Optional<Chat> getChatBySenderAndRecipient(long senderId, long recipientId);

    String getChatIdBySenderAndRecipient(long senderId, long recipientId);

    void deleteChat(Long id);

    String createChatIdBySenderAndRecipient(String senderId, String recipientId);

    boolean existsBySenderIdAndRecipientId(long senderId, long recipientId);

    void deleteAllChatByChatId(String chatId);
}
