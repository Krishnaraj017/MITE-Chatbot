package com.krish.krishbot;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText userMsgEdt;
    private final String BOT_KEY = "bot";
    private ArrayList<MessageModal> messageModalArrayList;
    private MessageRVAdapter messageRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Remove the app name from the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeChatScreen();
    }

    private void initializeChatScreen() {
        RecyclerView chatsRV = findViewById(R.id.idRVChats);
        ImageButton sendMsgIB = findViewById(R.id.idIBSend);
        userMsgEdt = findViewById(R.id.idEdtMessage);

        RequestQueue mRequestQueue = Volley.newRequestQueue(MainActivity.this);
        mRequestQueue.getCache().clear();

        messageModalArrayList = new ArrayList<>();
        sendMsgIB.setOnClickListener(v -> {
            if (userMsgEdt.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter your message..", Toast.LENGTH_SHORT).show();
                return;
            }
            sendMessage(userMsgEdt.getText().toString());
            userMsgEdt.setText("");
        });

        messageRVAdapter = new MessageRVAdapter(messageModalArrayList, this);
        chatsRV.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
        chatsRV.setAdapter(messageRVAdapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void sendMessage(String userMsg) {
        String USER_KEY = "user";
        messageModalArrayList.add(new MessageModal(userMsg, USER_KEY));
        messageRVAdapter.notifyDataSetChanged();

        String url = "http://api.brainshop.ai/get?bid=175431&key=8tiU6hgBqhyvzmQ8&uid=uid&msg=" + userMsg;
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        String botResponse = response.getString("cnt");
                        if (botResponse.isEmpty()) {
                            botResponse = "No response from the bot.";
                        }
                        messageModalArrayList.add(new MessageModal(botResponse, BOT_KEY));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        messageModalArrayList.add(new MessageModal("No response", BOT_KEY));
                    }
                    messageRVAdapter.notifyDataSetChanged();
                },
                error -> {
                    messageModalArrayList.add(new MessageModal("Sorry, an error occurred.", BOT_KEY));
                    Toast.makeText(MainActivity.this, "No response from the bot..", Toast.LENGTH_SHORT).show();
                    messageRVAdapter.notifyDataSetChanged();
                });
        queue.add(jsonObjectRequest);
    }
}
