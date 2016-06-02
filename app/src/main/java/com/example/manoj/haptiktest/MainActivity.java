package com.example.manoj.haptiktest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.manoj.haptiktest.chatconverstion.ChatConversation;
import com.example.manoj.haptiktest.models.ChatConversionModel;
import com.example.manoj.haptiktest.rest.RequestCallback;
import com.example.manoj.haptiktest.rest.Rest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchChatData();
    }

    private void fetchChatData() {
        Rest.GET().load(Constants.CHAT_URL).as(ChatConversionModel.class).withCallback(new RequestCallback<ChatConversionModel>() {
            @Override
            public void onRequestSuccess(ChatConversionModel data) {
                Log.d("manoj", "response success" + data.getCount());
                Intent intent = new Intent(getBaseContext(), ChatConversation.class);
                DataStore.getInstance().setChatMessageList(data.getChatMessageList());
                startActivity(intent);
            }

            @Override
            public void onRequestError(String error) {
                Log.d("manoj", "response errr :" + error);
            }
        });
    }
}
