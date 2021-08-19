package ru.otus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.domain.ChatMessage;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageDto {
   private long senderId;
   private long recipientId;
   private String body;

   public ChatMessageDto(ChatMessage chatMessage) {
      this.senderId = chatMessage.getSenderId();
      this.recipientId = chatMessage.getRecipientId();
      this.body = chatMessage.getBody();
   }
}
