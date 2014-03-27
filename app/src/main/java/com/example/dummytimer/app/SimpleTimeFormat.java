package com.example.dummytimer.app;

/**
 * Created by top on 27/3/2557.
 */
public class SimpleTimeFormat {

    public SimpleTimeFormat(){
        super();
    }
    public String parseLongMills(long millis){
        int seconds = (int) (millis / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;
        int hour=seconds/3600;
        return String.format("%02d:%02d:%02d",hour, minutes, seconds);
    }
}
