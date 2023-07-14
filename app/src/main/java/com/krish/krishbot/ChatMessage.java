package com.krish.krishbot;
public class ChatMessage {
    private boolean isUserMessage;
    private String message;


    public ChatMessage(boolean isUserMessage, String message) {
        this.isUserMessage = isUserMessage;
        this.message = message;

    }

    public boolean isUserMessage() {
        return isUserMessage;
    }

    public String getMessage() {
        return message;
    }




}
