package com.scaler.messengerpocfinal.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UserMessage extends ChatMessage {
    private String username; // Recipient's username
    private Long receiverUserId;

    // Constructors, getters, and setters...
//    public UserMessage(String username, ChatMessage chatMessage) {
//        super(chatMessage.getFrom(), chatMessage.getText());
//        this.username = username;
//    }

    // Getters and setters...
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
