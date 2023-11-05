package com.scaler.messengerpocfinal.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    private Long idemPotencyKey;
    private String from;
    private String text;

    // Constructors, getters, and setters...
//    public ChatMessage() {
//    }

    // Getters and setters...
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
