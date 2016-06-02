package com.example.manoj.haptiktest.chatconverstion;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.manoj.haptiktest.R;

public class ChatConversation extends AppCompatActivity {

    protected ViewPager viewPager;
    protected TabLayout tabLayout;
    private ChatPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_conversation);
        viewPager = (ViewPager) findViewById(R.id.tabanim_viewpager);

        pagerAdapter = new ChatPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.chat_title));

        tabLayout = (TabLayout) findViewById(R.id.tabanim_tabs);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pagerAdapter.updateFav();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
