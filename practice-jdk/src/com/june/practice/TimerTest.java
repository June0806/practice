package com.june.practice;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest{
    public static void main(String[] args) throws IOException {
        Timer timer = new Timer();
        class DefaultTimeTask extends TimerTask {
            @Override
            public void run() {
                System.out.println("default timer task");
            }
        }
        DefaultTimeTask defaultTimeTask = new DefaultTimeTask();
        timer.schedule(defaultTimeTask,0);
        timer.purge();
        System.in.read();
    }
}