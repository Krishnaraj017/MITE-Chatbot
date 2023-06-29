package com.krish.krishbot;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView chatRecyclerView;
    private ChatAdapter chatAdapter;
    private EditText inputEditText;
    private ImageButton sendButton;
    private List<ChatMessage> chatMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chatRecyclerView = findViewById(R.id.idRVChats);
        inputEditText = findViewById(R.id.idEdtMessage);
        sendButton = findViewById(R.id.idIBSend);

        // Set up the RecyclerView and its adapter
        chatMessages = new ArrayList<>();
        chatAdapter = new ChatAdapter(chatMessages);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        chatRecyclerView.setLayoutManager(layoutManager);
        chatRecyclerView.setAdapter(chatAdapter);

        inputEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    sendMessage();
                    return true;
                }
                return false;
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
    }
    private void sendMessage() {
        String userInput = inputEditText.getText().toString().trim();
        if (!userInput.isEmpty()) {
            ChatBotResponse botResponse = ChatBotData.getResponse(userInput);
            String response = botResponse.getMessage();

            addChatMessage(true, userInput);
            addChatMessage(false, response);

            if (botResponse.getUrl() != null) {
                // Handle URL click event here
                // You can open the URL in a browser or perform any other action
                // Replace "https://example.com" with the actual URL from the response
                String url = botResponse.getUrl();
                // Example: Opening the URL in a browser
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }

            inputEditText.setText("");
            chatRecyclerView.smoothScrollToPosition(chatAdapter.getItemCount() - 1);
        }
    }


    private void addChatMessage(boolean isUserMessage, String message) {
        ChatMessage chatMessage = new ChatMessage(isUserMessage, message);
        chatMessages.add(chatMessage);
        chatAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }
}