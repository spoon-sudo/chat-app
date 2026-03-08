package com.gsmeuav.chat_app;

public class ChatMessage {
    private Long senderId;
    private Long recipientId;
    private String messageContent;

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageContent() {
        return messageContent;
    }

}
