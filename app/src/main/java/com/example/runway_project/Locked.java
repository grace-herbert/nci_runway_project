package com.example.runway_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


public class Locked extends AppCompatActivity {

    private Boolean isLocked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locked);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.UK);
        SharedPreferences lockSP = getSharedPreferences("Lock", Context.MODE_PRIVATE);
        SharedPreferences.Editor lockSPEditor = lockSP.edit();
        //check if the app has been locked already or if this is a new lock.
        isLocked = lockSP.getBoolean("isLocked", false);
        // 72hr period
        long period = 1000L * 60L * 60L * 24 * 3L;

            lockSPEditor.putBoolean("isLocked", true);
            Calendar calendar = Calendar.getInstance();
            Date dateTime = calendar.getTime();
            Date dT = new Date(System.currentTimeMillis());
            Long dTInMillis = dT.getTime();
            lockSPEditor.putLong("origDateTimeInMillis", dTInMillis);
            lockSPEditor.commit();
            //some timing option must be placed on this screen to eventually activate the MainActivity after 72hrs

            Timer t = new Timer();
            TimerTask tt = new TimerTask() {
                @Override
                public void run() {
                    Log.d("Debug", "ul22");
                    lockSPEditor.putBoolean("isLocked", false);
                    lockSPEditor.commit();
                    Intent intent = new Intent(Locked.this, MainActivity.class);
                    startActivity(intent);

                }
            };

        Log.d("Debug", "DateTime: " +dateTime);

            t.schedule(tt, period);

    }

}
