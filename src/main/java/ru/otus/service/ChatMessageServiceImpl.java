package ru.otus.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.ChatMessageRepository;
import ru.otus.domain.Chat;
import ru.otus.domain.ChatMessage;
import ru.otus.domain.MessageStatus;

import java.util.Optional;


@Service
public class ChatMessageServiceImpl implements ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageServiceImpl(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<ChatMessage> getAll(String chatId) {
        return chatMessageRepository.findAllByChatId(chatId);
    }

    @Override
    @Transactional
    public ChatMessage save(ChatMessage message) {
       return chatMessageRepository.save(message);
    }

    @Override
    @Transactional
    public long countByChatIdAndStatus(String chatId, MessageStatus status) {
        return chatMessageRepository.countByChatIdAndStatus(chatId, status);
    }

    @Override
    @Transactional
    public Optional<ChatMessage> findById(long id) {
        return chatMessageRepository.findById(id);
    }
}
