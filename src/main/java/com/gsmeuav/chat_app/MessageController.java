package com.gsmeuav.chat_app;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/messages/send")
    public Message sendMessage(@RequestParam String content, @RequestParam Long senderId, @RequestParam Long recipientId) {
        return messageService.sendMessage(senderId, recipientId, content);
    }


    @GetMapping("/messages")
    public ResponseEntity<?> getMessages(@RequestParam Long senderId, @RequestParam Long recipientId) {
        return ResponseEntity.ok(messageService.getMessages(senderId, recipientId));
    }

}
