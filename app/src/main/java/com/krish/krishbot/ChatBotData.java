package com.krish.krishbot;

import java.util.HashMap;
import java.util.Map;

public class ChatBotData {
    private static final Map<String, String> trainingData = new HashMap<>();

    static {
        trainingData.put("What is an apple?", "An apple is a round fruit that is typically red or green.");
        trainingData.put("What are the health benefits of bananas?", "Bananas are a great source of potassium and provide energy.");
        trainingData.put("How do you eat a pineapple?", "To eat a pineapple, you need to peel off the outer skin and cut it into slices.");
        trainingData.put("hi", "Hello, ask questions about mite");
        trainingData.put("hey","he is a student");
        trainingData.put("mite", "Mangalore Institute of technology");
        trainingData.put("krishnaraj","he is a student");
        trainingData.put("htu", "https://example.com");
        trainingData.put("vtu", "https://vtu.ac.in/");

    }

    public static ChatBotResponse getResponse(String userInput) {
        userInput = userInput.toLowerCase();
        for (String trainingQuestion : trainingData.keySet()) {
            if (userInput.contains(trainingQuestion.toLowerCase())) {
                String response = trainingData.get(trainingQuestion);
                if (isUrl(response)) {
                    // Extract the URL from the response
                    String url = response;
                    return new ChatBotResponse(url, response);
                } else {
                    return new ChatBotResponse(response);
                }
            }
        }
        return new ChatBotResponse("I'm sorry, I don't have an answer.");
    }

    private static boolean isUrl(String text) {
        // Simple URL detection logic
        return text.startsWith("http://") || text.startsWith("https://");
    }
}