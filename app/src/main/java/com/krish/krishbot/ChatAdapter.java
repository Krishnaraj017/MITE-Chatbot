package com.krish.krishbot;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_USER = 1;
    private static final int VIEW_TYPE_BOT = 2;

    private List<ChatMessage> chatMessages;

    public ChatAdapter(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage chatMessage = chatMessages.get(position);
        return chatMessage.isUserMessage() ? VIEW_TYPE_USER : VIEW_TYPE_BOT;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;

        if (viewType == VIEW_TYPE_USER) {
            view = inflater.inflate(R.layout.user_msg, parent, false);
            return new UserMessageViewHolder(view);
        } else {
            view = inflater.inflate(R.layout.bot_msg, parent, false);
            return new BotMessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatMessage chatMessage = chatMessages.get(position);

        if (holder instanceof UserMessageViewHolder) {
            UserMessageViewHolder userViewHolder = (UserMessageViewHolder) holder;
            userViewHolder.bind(chatMessage);
        } else if (holder instanceof BotMessageViewHolder) {
            BotMessageViewHolder botViewHolder = (BotMessageViewHolder) holder;
            botViewHolder.bind(chatMessage);
        }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    private static class UserMessageViewHolder extends RecyclerView.ViewHolder {
        private TextView userTextView;

        public UserMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            userTextView = itemView.findViewById(R.id.idTVUser);
        }

        public void bind(ChatMessage chatMessage) {
            userTextView.setText(chatMessage.getMessage());
        }
    }

    private static class BotMessageViewHolder extends RecyclerView.ViewHolder {
        private TextView botTextView;

        public BotMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            botTextView = itemView.findViewById(R.id.idTVBot);
        }

        public void bind(ChatMessage chatMessage) {
            botTextView.setText(chatMessage.getMessage());
            // Check if the bot message is a URL
            if (isUrl(chatMessage.getMessage())) {
                botTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle URL click event here
                        // You can open the URL in a browser or perform any other action
                        // Extract the URL from the text
                        String url = extractUrl(chatMessage.getMessage());
                        if (url != null) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            v.getContext().startActivity(browserIntent);
                        }
                    }
                });
            } else {
                botTextView.setOnClickListener(null);
            }
        }

        private boolean isUrl(String text) {
            // Simple URL detection logic
            return text.startsWith("http://") || text.startsWith("https://");
        }

        private String extractUrl(String text) {
            // Extract the URL from the text
            if (isUrl(text)) {
                return text;
            }
            return null;
        }
    }
}