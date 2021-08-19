package ru.otus.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.ChatRepository;
import ru.otus.domain.Chat;
import ru.otus.domain.User;

import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;

    public ChatServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    @Transactional
    public String createOrGetChat(String chatId, User sender, User recipient) {
        if (!this.existsBySenderIdAndRecipientId(sender.getId(), recipient.getId())
                && !this.existsBySenderIdAndRecipientId(recipient.getId(), sender.getId())) {
            chatRepository.save(new Chat(chatId, sender, recipient));
            chatRepository.save(new Chat(chatId, recipient, sender));
            return chatId;
        } else {
            return this.getChatIdBySenderAndRecipient(sender.getId(), recipient.getId());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Chat> getAllByUserName(String userName) {
        return chatRepository.findAllByUserName(userName);
    }

    @Override
    @Transactional
    public Optional<Chat> getChatBySenderAndRecipient(long senderId, long recipientId) {
        return chatRepository.findBySenderAndRecipient(senderId, recipientId);
    }

    @Override
    @Transactional
    public String getChatIdBySenderAndRecipient(long senderId, long recipientId) {
        return chatRepository.getChatIdBySenderAndRecipient(senderId, recipientId);
    }

    @Override
    @Transactional
    public String createChatIdBySenderAndRecipient(String senderId, String recipientId) {
        return String.format("%s_%s", senderId, recipientId);
    }

    @Override
    public boolean existsBySenderIdAndRecipientId(long senderId, long recipientId) {
        return chatRepository.existsBySender_IdAndRecipient_Id(senderId, recipientId);
    }

    @Override
    @Transactional
    public void deleteAllChatByChatId(String chatId) {
        chatRepository.deleteAllByChatId(chatId);
    }

    @Override
    @Transactional
    public void deleteChat(Long id) {
        chatRepository.deleteById(id);
    }


}
