package com.scaler.messengerpocfinal.models;

public class GroupMessage extends ChatMessage {
    private String groupName;
    private Long groupId;

    // Constructors, getters, and setters...
    public GroupMessage(String groupName, ChatMessage chatMessage) {
//        super(chatMessage.getFrom(), chatMessage.getText());
        this.groupName = groupName;
    }

    // Getters and setters...
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
