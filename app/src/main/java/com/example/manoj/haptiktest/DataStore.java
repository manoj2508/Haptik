package com.example.manoj.haptiktest;

import android.util.Log;

import com.example.manoj.haptiktest.models.ChatMessageModel;
import com.example.manoj.haptiktest.models.FavMessageModel;
import com.example.manoj.haptiktest.models.MessageType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by manoj on 02/06/16.
 */
public class DataStore {
    private static DataStore instance;


    private List<ChatMessageModel> chatMessageList;
    private String userName;

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

    public List<FavMessageModel> getFavChatList() {
        List<FavMessageModel> favMessageModels = new ArrayList<>();

        HashMap<String, Integer> totalCountMap = new HashMap<>();
        HashMap<String, Integer> favCountMap = new HashMap<>();

        for (ChatMessageModel chatMessageModel : chatMessageList) {
            String key = chatMessageModel.getUsername();
            Integer preVal = totalCountMap.get(key);
            int totalCount = 0;
            if (preVal != null) {
                totalCount = preVal;
            }

            totalCount++;
            totalCountMap.put(key, totalCount);

            int favCount = 0;
            Integer favVal = favCountMap.get(key);
            if (favVal != null) {
                favCount = favVal;
            }
            if (chatMessageModel.isFavourite()) {
                favCount++;
                Log.d("manoj", "fav find");
            }
            favCountMap.put(key, favCount);
        }

        Set<String> set = new HashSet<>();

        for (ChatMessageModel chatMessageModel : chatMessageList) {
            if (chatMessageModel.getMessageType() == MessageType.FRIEND) {
                FavMessageModel favMessageModel = new FavMessageModel();
                favMessageModel.setName(chatMessageModel.getName());
                favMessageModel.setUsername(chatMessageModel.getUsername());
                favMessageModel.setTotalCount(totalCountMap.get(chatMessageModel.getUsername()));
                favMessageModel.setFavCount(favCountMap.get(chatMessageModel.getUsername()));
                if (!set.contains(chatMessageModel.getUsername())) {
                    set.add(chatMessageModel.getUsername());
                    favMessageModels.add(favMessageModel);
                }

            }
        }
        return favMessageModels;
    }


    public boolean isUserNameContain(String text) {
        for (ChatMessageModel chatMessageModel : chatMessageList) {
            if (chatMessageModel.getUsername().equals(text)) {
                return true;
            }
        }
        return false;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}


