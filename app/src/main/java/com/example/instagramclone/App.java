package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("EvGps050NXklc6OtxLSI8ibvUso9dpxvF1OS6UGx")
                .clientKey("8TLgfStFNI3sNB6mDAvanTHBGKFpwRpy1l73chGd")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
