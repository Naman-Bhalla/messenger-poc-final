package com.scaler.messengerpocfinal.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.security.Principal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    private final SimpMessageSendingOperations messagingTemplate;
    private final Map<String, String> sessionUserMap = new ConcurrentHashMap<>();

    @Autowired
    public WebSocketEventListener(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        Principal user = event.getUser();
        if (user != null) {
            logger.info("User Connected: " + user.getName());
            sessionUserMap.put(user.getName(), event.getMessage().getHeaders().get("simpSessionId").toString());
            // You can also broadcast that the user is now connected, if needed
        }
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        Principal user = event.getUser();
        if (user != null) {
            logger.info("User Disconnected: " + user.getName());
            sessionUserMap.remove(user.getName());
            // Broadcast to other users that this user has disconnected, if necessary
            // This might involve sending a message to a topic that all users are subscribed to
            messagingTemplate.convertAndSend("/topic/userDisconnect", user.getName());
        }
    }
}
