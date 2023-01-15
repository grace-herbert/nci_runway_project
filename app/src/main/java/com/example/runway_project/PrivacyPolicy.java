package com.example.runway_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PrivacyPolicy extends AppCompatActivity {

    //solution to app closing on home button found: https://stackoverflow.com/questions/21901015/how-to-kill-the-application-with-the-home-button
    @Override
    public void onPause() {
        super.onPause();
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
    }
}