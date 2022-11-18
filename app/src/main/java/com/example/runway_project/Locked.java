package com.example.runway_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Locked extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locked);

        //some timing option must be placed on this screen to eventually activate the MainActivity after 72hrs
    }
}