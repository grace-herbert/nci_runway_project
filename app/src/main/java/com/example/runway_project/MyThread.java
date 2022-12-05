package com.example.runway_project;

public class MyThread extends Thread{
    boolean stop = false;
        public void run() {
            while (true) {
                if (stop) {
                    return;
                }
            }
        }
    }
