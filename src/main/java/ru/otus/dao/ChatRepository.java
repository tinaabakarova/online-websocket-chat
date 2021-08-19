package ru.otus.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.otus.domain.Chat;

import java.util.Optional;

public interface ChatRepository extends CrudRepository<Chat, Long> {
    //TODO: rename login
    @Query("SELECT chat FROM Chat chat WHERE chat.sender.login = ?1")
    Iterable<Chat> findAllByUserName(String username);

    Optional<Chat> findChatByChatId(String chatId);

    @Query("SELECT chat FROM Chat chat WHERE (chat.sender.id = ?1 and chat.recipient.id = ?2) or (chat.sender.id = ?2 and chat.recipient.id = ?1) ")
    Optional<Chat> findBySenderAndRecipient(long senderId, long recipientId);

    @Query("SELECT chat.chatId FROM Chat chat WHERE chat.sender.id = ?1 and chat.recipient.id = ?2")
    String getChatIdBySenderAndRecipient(long senderId, long recipientId);

    boolean existsByChatId(String chatId);

    boolean existsBySender_IdAndRecipient_Id(long senderId, long recipientId);

    void deleteAllByChatId(String chatId);
}
