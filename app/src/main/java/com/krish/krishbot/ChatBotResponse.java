package com.krish.krishbot;

public class ChatBotResponse {
    private String message;
    private String url;
    private String contactNumber;

    public ChatBotResponse(String message) {
        this.message = message;
    }

    public ChatBotResponse(String message, String url) {
        this.message = message;
        this.url = url;
    }

    public ChatBotResponse(String message, String url, String contactNumber) {
        this.message = message;
        this.url = url;
        this.contactNumber = contactNumber;
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }

    public String getContactNumber() {
        return contactNumber;
    }
}
