package com.example.manoj.haptiktest.chatconverstion;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.manoj.haptiktest.R;

public class ChatConversation extends AppCompatActivity {

    protected ViewPager viewPager;
    protected TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_conversation);
        viewPager = (ViewPager) findViewById(R.id.tabanim_viewpager);

        ChatPagerAdapter reminderPagerAdapter = new ChatPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(reminderPagerAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.chat_title));

        tabLayout = (TabLayout) findViewById(R.id.tabanim_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}
