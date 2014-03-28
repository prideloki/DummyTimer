package com.example.dummytimer.app;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import android.widget.TextView;

/**
 * Created by top on 27/3/2557.
 */
public class TimerActivity extends FragmentActivity {
    private final String TAG = TimerActivity.class.getCanonicalName();

    private static boolean isPomodoro=true;
    private static boolean isAutoring=true;
    long startTime = 0L;
    private static long millis=0L;
    private TextView timerTextView;
    FragmentTransaction fragmentTransaction;
    private Handler timerHandler = new Handler();
    SimpleTimeFormat timeFormatter=new SimpleTimeFormat();
    NotificationManager notificationManager;

    TimerFragment timerFragment;

    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            millis = System.currentTimeMillis() - startTime;
            timerFragment.setTimerTextView(String.format(timeFormatter.parseLongMills(millis)));
            timerHandler.postDelayed(this, 500);
        }

    };

    Runnable pomTimerRunnable = new Runnable() {
        @Override
        public void run() {
            millis = System.currentTimeMillis() - startTime;
            //for testing
            int seconds = (int) (millis / 1000);

            //int minutes = getMinutes();

            timerFragment.setTimerTextView(String.format(timeFormatter.parseLongMills(millis)));
            if(seconds==4) {
                showNotification();

                timerHandler.removeCallbacks(this);
            }else {
                timerHandler.postDelayed(this, 500);
            }


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        if (findViewById(R.id.timer_fragment_container) != null) {
            timerFragment = new TimerFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.timer_fragment_container, timerFragment);
            fragmentTransaction.commit();

            startTime = System.currentTimeMillis();
            if(isPomodoro) timerHandler.postDelayed(pomTimerRunnable, 0);
            else timerHandler.postDelayed(timerRunnable, 0);







        }
        /*
        timerTextView = (TextView) findViewById(R.id.showTimer_running_textView);
        timerTextView.setText(timeFormatter.parseLongMills(0));
        Button close_activity = (Button) findViewById(R.id.close_act);
        close_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isPomodoro)timerHandler.removeCallbacks(pomTimerRunnable);
                else timerHandler.removeCallbacks(timerRunnable);
                finish();

            }
        });
        */

        /*
        startTime = System.currentTimeMillis();
        if(isPomodoro) timerHandler.postDelayed(pomTimerRunnable, 0);
        else {
            log("Unlimit");

            timerHandler.postDelayed(timerRunnable, 0);

        }
        */
    }




        @Override
        public void finish() {
            log("Enter Finish Method ");


            Intent data = new Intent();
            if(notificationManager!=null)notificationManager.cancelAll();

            if(isPomodoro)timerHandler.removeCallbacks(pomTimerRunnable);
            else timerHandler.removeCallbacks(timerRunnable);
            super.finish();
        }

        @Override
        protected void onDestroy() {
            if(isPomodoro)timerHandler.removeCallbacks(pomTimerRunnable);
            else timerHandler.removeCallbacks(timerRunnable);
            super.onDestroy();
        }




        public void showNotification(){

            Uri soundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Intent intent = this.getIntent();
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            PendingIntent pendingIntent=PendingIntent.getActivity(TimerActivity.this,0,intent,0);
            long[] mVibratePattern = {0, 200, 200, 300};
            NotificationCompat.Builder mBuilder=new NotificationCompat.Builder(getApplicationContext())
                    .setSmallIcon(android.R.drawable.ic_dialog_alert)
                    .setContentTitle("Time UP!")
                    .setContentText("text")
                    .setContentIntent(pendingIntent);
            if(isAutoring){
                mBuilder.setVibrate(mVibratePattern);
                mBuilder.setSound(soundUri);
            }
            notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            mBuilder.setAutoCancel(true);
            notificationManager.notify(0,mBuilder.build());

        }

    private int getMinutes(){
        millis = System.currentTimeMillis() - startTime;

        int seconds = (int) (millis / 1000);
        return seconds / 60;

    }
    private void log(String message) {
        Log.i(TAG, message);
    }
    public static void setPomodoro(boolean isPomodoro) {
        TimerActivity.isPomodoro = isPomodoro;
    }
    public static boolean isIsPomodoro() {
        return isPomodoro;
    }
    public static boolean isIsAutoring() {
        return isAutoring;
    }
    public static long getMillis(){
        return millis;
    }
    public static void setIsAutoring(boolean isAutoring) {
        TimerActivity.isAutoring = isAutoring;
    }

}
