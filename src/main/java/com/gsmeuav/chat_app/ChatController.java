package com.gsmeuav.chat_app;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(MessageService messageService, SimpMessagingTemplate messagingTemplate) {
        this.messageService = messageService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat")

    public Message sendMessage(@Payload ChatMessage chatMessage){
        Message savedMessage = messageService.sendMessage(chatMessage.getSenderId(), chatMessage.getRecipientId(), chatMessage.getMessageContent());
        messagingTemplate.convertAndSendToUser(chatMessage.getRecipientId().toString(), "/queue/messages", savedMessage);
        return savedMessage;
    }


}
