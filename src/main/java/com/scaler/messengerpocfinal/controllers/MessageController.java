package com.scaler.messengerpocfinal.controllers;

import com.scaler.messengerpocfinal.models.ChatMessage;
import com.scaler.messengerpocfinal.models.GroupMessage;
import com.scaler.messengerpocfinal.models.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public MessageController(SimpMessageSendingOperations messagingTemplate, RedisTemplate<String, Object> redisTemplate) {
        this.messagingTemplate = messagingTemplate;
        this.redisTemplate = redisTemplate;
    }

    @MessageMapping("/chat.private.{userId}")
    public void sendPrivateMessage(@DestinationVariable Long userId, ChatMessage chatMessage) {
        // Check for duplication -> Check if already an Id in the message exists in DB

        // If no,
        // 1. Save the message to DB

        // 2. Put on Redis



        // Use Redis to publish the message to other instances
        redisTemplate.convertAndSend("privateChat", null);
        // If the user is connected to this instance, directly send the message
//        messagingTemplate.convertAndSendToUser(username, "/queue/reply", chatMessage);
    }

    @MessageMapping("/chat.group.{groupName}")
    public void sendGroupMessage(@DestinationVariable String groupName, ChatMessage chatMessage) {
        // Use Redis to publish the message to other instances
        redisTemplate.convertAndSend("groupChat", new GroupMessage(groupName, chatMessage));
        // Send to local subscribers
        messagingTemplate.convertAndSend("/topic/" + groupName, chatMessage);
    }
}

// localhost:8000/ws/chat.private.1473