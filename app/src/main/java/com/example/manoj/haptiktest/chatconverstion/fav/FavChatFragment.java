package com.example.manoj.haptiktest.chatconverstion.fav;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manoj.haptiktest.DataStore;
import com.example.manoj.haptiktest.R;
import com.example.manoj.haptiktest.models.FavMessageModel;

import java.util.List;

public class FavChatFragment extends Fragment {

    private RecyclerView favChatListRecyclerView;
    private FavChatAdapter favChatAdapter;

    private List<FavMessageModel> favMessageModels;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View baseView = inflater.inflate(R.layout.fragment_fav_chat, container, false);

        favChatListRecyclerView = (RecyclerView) baseView.findViewById(R.id.fav_chat_list_recycler_view);
        favMessageModels = DataStore.getInstance().getFavChatList();
        //set recycler view
        favChatListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        favChatAdapter = new FavChatAdapter(getContext(), favMessageModels);
        favChatListRecyclerView.setAdapter(favChatAdapter);
        return baseView;
    }


    @Override
    public void onResume() {
        Log.d("manoj", "onresume called");
        super.onResume();
        if (favChatAdapter != null) {
            favMessageModels = DataStore.getInstance().getFavChatList();
            favChatAdapter.updateMessageList(favMessageModels);
        }
    }

    public void onUpate() {
        if (favChatAdapter != null) {
            favMessageModels = DataStore.getInstance().getFavChatList();
            favChatAdapter.updateMessageList(favMessageModels);
        }
    }

}
