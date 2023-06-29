package com.krish.krishbot;
public class ChatMessage {
    private boolean isUserMessage;
    private String message;
    private boolean isContactNumber;

    public ChatMessage(boolean isUserMessage, String message) {
        this.isUserMessage = isUserMessage;
        this.message = message;
        this.isContactNumber = isContactNumber(message);
    }

    public boolean isUserMessage() {
        return isUserMessage;
    }

    public String getMessage() {
        return message;
    }

    public boolean isContactNumber() {
        return isContactNumber;
    }

    private boolean isContactNumber(String text) {
        // Simple contact number detection logic
        return text.startsWith("+");
    }
}
