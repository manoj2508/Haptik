package com.example.manoj.haptiktest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.manoj.haptiktest.chatconverstion.ChatConversation;
import com.example.manoj.haptiktest.models.ChatConversionModel;
import com.example.manoj.haptiktest.rest.RequestCallback;
import com.example.manoj.haptiktest.rest.Rest;

public class MainActivity extends AppCompatActivity {

    private View progerssBar;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progerssBar = findViewById(R.id.pogress_bar);
        editText = (EditText) findViewById(R.id.user_name);
        findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChatActivity();
            }
        });

        fetchChatData();
    }

    private void openChatActivity() {
        String text = editText.getText().toString();
        if (text.isEmpty()) {
            Toast.makeText(this, "Please enter user name", Toast.LENGTH_SHORT).show();
        } else if (!DataStore.getInstance().isUserNameContain(text)) {
            Toast.makeText(this, "User name not exist", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(getBaseContext(), ChatConversation.class);

            DataStore.getInstance().setUserName(text);
            startActivity(intent);
        }
    }

    private void fetchChatData() {
        Rest.GET().load(Constants.CHAT_URL).as(ChatConversionModel.class).withCallback(new RequestCallback<ChatConversionModel>() {
            @Override
            public void onRequestSuccess(ChatConversionModel data) {
                progerssBar.setVisibility(View.GONE);
                Log.d("manoj", "response success" + data.getCount());
                DataStore.getInstance().setChatMessageList(data.getChatMessageList());
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
