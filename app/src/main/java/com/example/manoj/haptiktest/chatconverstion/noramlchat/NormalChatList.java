package com.example.manoj.haptiktest.chatconverstion.noramlchat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manoj.haptiktest.DataStore;
import com.example.manoj.haptiktest.R;
import com.example.manoj.haptiktest.models.ChatMessageModel;

import java.util.List;

public class NormalChatList extends Fragment {

    private RecyclerView chatListRecyclerView;
    private ChatListRecyclerAdapter chatListRecyclerAdapter;

    private String userName = "john-t";
    private List<ChatMessageModel> chatMessageList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View baseView = inflater.inflate(R.layout.fragment_normal_chat_list, container, false);

        userName = DataStore.getInstance().getUserName();
        chatListRecyclerView = (RecyclerView) baseView.findViewById(R.id.chat_list_recycler_view);
        DataStore.getInstance().convertChatMessage(userName);
        chatMessageList = DataStore.getInstance().getChatMessageList();
        //set recycler view
        chatListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        chatListRecyclerAdapter = new ChatListRecyclerAdapter(getContext(), chatMessageList);
        chatListRecyclerView.setAdapter(chatListRecyclerAdapter);
        return baseView;
    }
}
