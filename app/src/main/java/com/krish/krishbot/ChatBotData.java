package com.krish.krishbot;

import java.util.HashMap;
import java.util.Map;

public class ChatBotData {
    private static final Map<String, String> trainingData = new HashMap<>();

    static {
        trainingData.put("hi", "Hello,feel free to ask questions about mite....");
        trainingData.put("hey", "Hello,feel free to ask questions about mite...");
        trainingData.put("workshop", " https://mite.ac.in/ac_event_category/workshop/");
        trainingData.put("mite", "Mangalore Institute of technology");
        trainingData.put("krishnaraj", "he is a student");
        trainingData.put("courses in mite", "https://www.google.com/search?q=Mangalore+Institute+of+Technology+and+Engineering+course+admissions&ei=Bs2dZPiuFYGbseMPstiHgA0&ved=2ahUKEwiS5PGUjun_AhWkcWwGHYLnCwoQyNoBKAB6BAgREAA&uact=5&oq=courses+at+mangalore+institute+of+technology&gs_lcp=Cgxnd3Mtd2l6LXNlcnAQAzIFCAAQogQyBQgAEKIEMgUIABCiBDIFCAAQogQ6CggAEEcQ1gQQsAM6CgghEKABEMMEEApKBAhBGABQvAFY7hBglhdoAXABeACAAbMBiAHyCJIBAzAuOJgBAKABAcABAcgBCA&sclient=gws-wiz-serp&si=AMnBZoGcKxe_VG3y7RIcCPYEnxcIePrCFWsiFidaGF2NzZb2mxM9C8RzzYOEASlvvyEhAdEDz_aFd75v_pnw3Lza6r0UCnAWIxt7_QAqTmu17NlhZKXX0BuNV3GXJTO46-0VSzBs67cMcPhbTvUrl9rvOw_Ka7adwSCCU3hdreg9ccpfarxXR8EactKy4MNP4bXCZTRRdby-B6GjNXjSZt7i7mkxedrZCQ%3D%3D&ictx=1");
        trainingData.put("courses at mite", "https://www.google.com/search?q=Mangalore+Institute+of+Technology+and+Engineering+course+admissions&ei=Bs2dZPiuFYGbseMPstiHgA0&ved=2ahUKEwiS5PGUjun_AhWkcWwGHYLnCwoQyNoBKAB6BAgREAA&uact=5&oq=courses+at+mangalore+institute+of+technology&gs_lcp=Cgxnd3Mtd2l6LXNlcnAQAzIFCAAQogQyBQgAEKIEMgUIABCiBDIFCAAQogQ6CggAEEcQ1gQQsAM6CgghEKABEMMEEApKBAhBGABQvAFY7hBglhdoAXABeACAAbMBiAHyCJIBAzAuOJgBAKABAcABAcgBCA&sclient=gws-wiz-serp&si=AMnBZoGcKxe_VG3y7RIcCPYEnxcIePrCFWsiFidaGF2NzZb2mxM9C8RzzYOEASlvvyEhAdEDz_aFd75v_pnw3Lza6r0UCnAWIxt7_QAqTmu17NlhZKXX0BuNV3GXJTO46-0VSzBs67cMcPhbTvUrl9rvOw_Ka7adwSCCU3hdreg9ccpfarxXR8EactKy4MNP4bXCZTRRdby-B6GjNXjSZt7i7mkxedrZCQ%3D%3D&ictx=1");
        trainingData.put("research facility", "https://mite.ac.in/academics-research/");
        trainingData.put("research", "https://mite.ac.in/academics-research/");
        trainingData.put("administration", "https://mite.ac.in/administration/");
        trainingData.put("admission", "https://mite.ac.in/admissions/");
        trainingData.put("incubation", "https://mite.ac.in/mite-incubation-center/");
        trainingData.put("placement", "https://mite.ac.in/placements/");
        trainingData.put("Infrastructure", "https://mite.ac.in/infrastructure/");
        trainingData.put("library", "https://mite.ac.in/library/");

        trainingData.put("hostel", "https://mite.ac.in/hostel/");
        trainingData.put("sports", "https://mite.ac.in/physical-well-being/");

        trainingData.put("transportation", "https://mite.ac.in/transportation/");
        trainingData.put("Faculty", "https://auth.dhi-edu.com/auth/realms/regroup/protocol/openid-connect/auth?client_id=regroup_mite&redirect_uri=https%3A%2F%2Fregroup.dhi-edu.com%2Fregroup_mite%2F%23%2Ffaculty%2Fgrievances%2Faddgrievances&state=e6c90fe6-b8bb-4dc2-8967-25f315756495&response_mode=fragment&response_type=code&scope=openid&nonce=d7db1f66-4961-4a78-a687-ae948f4d1137");
        trainingData.put("Student", "https://regroup.dhi-edu.com/regroup_mite/#/student/grievances/addgrievances");
        trainingData.put("sentia", "https://mite.ac.in/sentia/");
        trainingData.put("Ted x mite ", "https://mite.ac.in/tedx-mite/");
        trainingData.put("covid", "https://mite.ac.in/fighting-covid/");
        trainingData.put("contact", "08258262695");
        trainingData.put("contact number", "08258262695");
        trainingData.put("teaching method", "offline teaching method");
        trainingData.put("mode of teaching", "Offline teaching method");
        trainingData.put("Placement", "https://mite.ac.in/placements/");
    }

    public static ChatBotResponse getResponse(String userInput) {
        userInput = userInput.toLowerCase();
        for (String trainingQuestion : trainingData.keySet()) {
            if (userInput.equals(trainingQuestion.toLowerCase())) {
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
        System.out.println("User Input: " + userInput); // Add this line for debugging
        return new ChatBotResponse("I'm sorry, I don't have an answer.");
    }

    private static boolean isUrl(String text) {
        // Simple URL detection logic
        return text.startsWith("http://") || text.startsWith("https://");
    }
}
