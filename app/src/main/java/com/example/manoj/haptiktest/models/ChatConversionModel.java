package com.example.manoj.haptiktest.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by manoj on 02/06/16.
 */
public class ChatConversionModel implements Serializable {

    private int count;
    @SerializedName("messages")
    private List<ChatMessageModel> chatMessageList;

    public int getCount() {
        return count;
    }

    public List<ChatMessageModel> getChatMessageList() {
        return chatMessageList;
    }
}
