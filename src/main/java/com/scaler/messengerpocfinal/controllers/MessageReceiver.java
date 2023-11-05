package com.scaler.messengerpocfinal.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaler.messengerpocfinal.models.GroupMessage;
import com.scaler.messengerpocfinal.models.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MessageReceiver {

    private final SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public MessageReceiver(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void receivePrivateMessage(String message) {
        try {
            UserMessage userMessage = new ObjectMapper().readValue(message, UserMessage.class);
            messagingTemplate.convertAndSendToUser(userMessage.getUsername(), "/queue/reply", userMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveGroupMessage(String message) {
        try {
            GroupMessage groupMessage = new ObjectMapper().readValue(message, GroupMessage.class);
            messagingTemplate.convertAndSend("/topic/" + groupMessage.getGroupName(), groupMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
