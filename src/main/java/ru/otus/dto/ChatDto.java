package ru.otus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.domain.Chat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatDto {
    private Long id;
    private long senderId;
    private long recipientId;
    private String recipientName;
    private String senderName;

    public ChatDto(Chat chat) {
        this.id = chat.getId();
        this.senderId = chat.getSender().getId();
        this.recipientId = chat.getRecipient().getId();
        this.recipientName = chat.getRecipient().getName();
        this.senderName = chat.getSender().getName();
    }
}
