package com.example.manoj.haptiktest.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by manoj on 02/06/16.
 */
public class ChatMessageModel implements Serializable {

    private String body;
    private String username;
    @SerializedName("Name")
    private String name;
    @SerializedName("image-url")
    private String imageUrl;
    @SerializedName("message-time")
    private String messageTime;

    private boolean isFavourite;
    private MessageType messageType;

    public String getBody() {
        return body;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getMessageTime() {
        return messageTime;
    }


    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(boolean isFavourite) {
        this.isFavourite = isFavourite;
    }
}
