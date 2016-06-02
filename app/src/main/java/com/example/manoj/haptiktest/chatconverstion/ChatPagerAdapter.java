package com.example.manoj.haptiktest.chatconverstion;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.manoj.haptiktest.chatconverstion.fav.FavChatFragment;
import com.example.manoj.haptiktest.chatconverstion.noramlchat.NormalChatList;

import java.util.ArrayList;
import java.util.List;

public class ChatPagerAdapter extends FragmentStatePagerAdapter {
    private List<String> titles;

    private FavChatFragment favChatFragment;

    public ChatPagerAdapter(FragmentManager fm) {
        super(fm);
        titles = new ArrayList<>();
        titles.add("Chats");
        titles.add("Fav");
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NormalChatList();
        } else {
            favChatFragment = new FavChatFragment();
            return favChatFragment;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }


    public void updateFav() {
        if (favChatFragment != null) {
            favChatFragment.onUpate();
        }
    }

}
