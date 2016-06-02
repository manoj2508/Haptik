package com.example.manoj.haptiktest;

import android.app.Application;

import com.example.manoj.haptiktest.rest.Rest;

/**
 * Created by manoj on 02/06/16.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Rest.init(getBaseContext());
    }


}