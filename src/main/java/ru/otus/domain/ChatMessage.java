package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "chat_messages")
public class ChatMessage {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "message_id", nullable = false, unique = true)
   private Long messageId;

   @Column(name = "chat_id", nullable = false)
   private String chatId;

   @Column(name = "sender_id", nullable = false)
   private long senderId;

   @Column(name = "recipient_id", nullable = false)
   private long recipientId;

   @Column(name = "body", nullable = false)
   private String body;

   @Column(name = "date", nullable = false)
   private int date;

   @Enumerated(EnumType.STRING)
   @Column(name = "status", nullable = false)
   private MessageStatus status;
}
