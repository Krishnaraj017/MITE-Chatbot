package com.krish.krishbot;

public class MessageModal {
    private String message;
    private String sender;

    public MessageModal(String message, String sender) {
        this.message = message;
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }
}
