package com.example.runway_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
//            lockSPEditor.putString("dateTime", simpleDateFormat.format(dateTime));
            lockSPEditor.commit();
            //some timing option must be placed on this screen to eventually activate the MainActivity after 72hrs

            Timer t = new Timer();
            TimerTask tt = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("run triggering");
                    lockSPEditor.putBoolean("isLocked", false);
                    lockSPEditor.commit();
                    Intent intent = new Intent(Locked.this, MainActivity.class);
                    startActivity(intent);

                }
            };

            System.out.println("DateTime: " +dateTime);
//            Long tempPeriod = 1000L * 60L * 6L;
            t.schedule(tt, period);
//            t.scheduleAtFixedRate(tt, 0, 2000L);
//            t.scheduleAtFixedRate(tt, dateTime, 5000L);
//        } else if (isLocked){
//            System.out.println("rechecking time of lock.");
//            Long origDateTimeMillis = lockSP.getLong("origDateTimeInMillis", 10000L);
//            Date dT = new Date(System.currentTimeMillis());
//            Long currentDTMillis = dT.getTime();
//
//            Long difference = currentDTMillis - origDateTimeMillis;
//            System.out.println("(currentDTMillis:) " + currentDTMillis + " - (origDateTimeMillis:) " + origDateTimeMillis + " = (difference: )" + difference);
//            System.out.println((1000L * 60L * 2L));
//            if(difference > (1000L * 60L * 2L)){
//                System.out.println("if(difference > (1000L * 60L * 2L)) triggered");
//                lockSPEditor.putBoolean("isLocked", false);
//                lockSPEditor.commit();
//                Intent intent = new Intent(Locked.this, MainActivity.class);
//                startActivity(intent);
//            }else{
//                long newPeriod = period - difference;
//                Calendar calendar = Calendar.getInstance();
//                Date dateTime = calendar.getTime();
//                Timer t = new Timer();
//                TimerTask tt = new TimerTask() {
//                    @Override
//                    public void run() {
//                        System.out.println("unlocking");
//                        lockSPEditor.putBoolean("isLocked", false);
//                        lockSPEditor.commit();
//                        Intent intent = new Intent(Locked.this, MainActivity.class);
//                        startActivity(intent);
//
//                    }
//                };
//
//                System.out.println(dateTime);
//
////                t.scheduleWithFixedDelay()
//                Long tempPeriod = 1000L * 60L * 2L;
//                t.schedule(tt, tempPeriod);
////            t.schedule(tt, newPeriod);
////                t.scheduleAtFixedRate(tt, dateTime, newPeriod);
//            }
}

    }
