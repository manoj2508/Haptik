package com.example.manoj.haptiktest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.manoj.haptiktest.chatconverstion.ChatConversation;
import com.example.manoj.haptiktest.models.ChatConversionModel;
import com.example.manoj.haptiktest.rest.RequestCallback;
import com.example.manoj.haptiktest.rest.Rest;

public class MainActivity extends AppCompatActivity {

    private View progerssBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progerssBar = findViewById(R.id.pogress_bar);
        fetchChatData();
    }

    private void fetchChatData() {
        Rest.GET().load(Constants.CHAT_URL).as(ChatConversionModel.class).withCallback(new RequestCallback<ChatConversionModel>() {
            @Override
            public void onRequestSuccess(ChatConversionModel data) {
                progerssBar.setVisibility(View.GONE);
                Log.d("manoj", "response success" + data.getCount());
                Intent intent = new Intent(getBaseContext(), ChatConversation.class);
                DataStore.getInstance().setChatMessageList(data.getChatMessageList());
                startActivity(intent);
            }

            @Override
            public void onRequestError(String error) {
                progerssBar.setVisibility(View.GONE);
                Toast.makeText(getBaseContext(), "Some Erro" + error, Toast.LENGTH_SHORT).show();
                Log.d("manoj", "response errr :" + error);
            }
        });
    }
}
