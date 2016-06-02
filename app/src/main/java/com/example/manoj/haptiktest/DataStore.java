package com.example.manoj.haptiktest;

import com.example.manoj.haptiktest.models.ChatMessageModel;
import com.example.manoj.haptiktest.models.MessageType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manoj on 02/06/16.
 */
public class DataStore {
    private static DataStore instance;


    private List<ChatMessageModel> chatMessageList;

    private DataStore() {
        chatMessageList = new ArrayList<>();
    }

    public synchronized static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    public List<ChatMessageModel> getChatMessageList() {
        return chatMessageList;
    }

    public void setChatMessageList(List<ChatMessageModel> chatMessageList) {
        this.chatMessageList = chatMessageList;
    }

    public void convertChatMessage(String userName) {
        for (ChatMessageModel chatMessageModel : chatMessageList) {
            if (userName.equals(chatMessageModel.getUsername())) {
                chatMessageModel.setMessageType(MessageType.SELF);
            } else {
                chatMessageModel.setMessageType(MessageType.FRIEND);
            }
        }
    }

    public List<ChatMessageModel> getFavChatList() {
        List<ChatMessageModel> chatMessageModels = new ArrayList<>();
        for (ChatMessageModel chatMessageModel : chatMessageList) {
            if (chatMessageModel.isFavourite()) {
                chatMessageModels.add(chatMessageModel);
            }
        }
        return chatMessageModels;
    }
}


