package com.gsmeuav.chat_app;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    Message sendMessage(Long senderID, Long receiverID, String messageContent) {
        Message message = new Message();

        message.setSenderID(senderID);
        message.setReceiverID(receiverID);
        message.setMessageContent(messageContent);
        message.setMessageDate(new java.util.Date());
        return messageRepository.save(message);
    }

    List<Message> getMessages(Long senderID, Long receiverID) {
        List<Message> list1 = new java.util.ArrayList<>(messageRepository.findBySenderID(senderID).stream().filter(m -> m.getReceiverID().equals(receiverID)).toList());

        List<Message> list2 = messageRepository.findBySenderID(receiverID).stream().filter(m -> m.getReceiverID().equals(senderID)).toList();

        list1.addAll(list2);
        return list1;
    }
}
