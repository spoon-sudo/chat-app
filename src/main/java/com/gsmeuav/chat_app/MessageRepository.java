package com.gsmeuav.chat_app;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderID(Long senderID);

    List<Message> findByReceiverID(Long receiverID);
}
