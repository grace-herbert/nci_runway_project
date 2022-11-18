package com.example.runway_project;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Gen2 {
//    public Gen2(){
//        Gen g = new Gen();
//        g.computeGen();
//    }

    public static void main(String[] args){
        Timer t = new Timer();
        TimerTask tt = new TimerTask(){
            @Override
            public void run(){
                System.out.println("running task");
            }
        };

        Calendar startDate = Calendar.getInstance();
        startDate.set(Calendar.DAY_OF_MONTH, 14);
        startDate.set(Calendar.MONTH, 11);
        startDate.set(Calendar.YEAR, 2022);
        startDate.set(Calendar.HOUR_OF_DAY, 11);
        startDate.set(Calendar.MINUTE, 0);
        startDate.set(Calendar.SECOND, 0);
        startDate.set(Calendar.MILLISECOND, 0);

        // 2 week period
        long period = 1000L * 60L * 60L * 24L * 14L;

        t.scheduleAtFixedRate(tt, startDate.getTime(), 120000L);
    }


}
