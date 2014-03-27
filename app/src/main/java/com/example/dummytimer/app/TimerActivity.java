package com.example.dummytimer.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by top on 27/3/2557.
 */
public class TimerActivity extends Activity {
    private String TAG = "Timer Activity";
    TextView timerTextView;
    long startTime = 0L;
    long millis=0L;
    private Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            millis = System.currentTimeMillis() - startTime;
            /*
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            */
            timerTextView.setText(String.format(parseLongMills(millis)));
            timerHandler.postDelayed(this, 500);

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        timerTextView = (TextView) findViewById(R.id.showTimer_running_textView);
        timerTextView.setText(parseLongMills(0));
        Button close_activity = (Button) findViewById(R.id.close_act);
        close_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timerHandler.removeCallbacks(timerRunnable);
                finish();

            }
        });
        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);

    }
    private String parseLongMills(long millis){
        int seconds = (int) (millis / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }
    @Override
    public void finish() {
        log("Enter Finish Method "+timerTextView.getText().toString());
        Intent data = new Intent();

        data.putExtra("Timer",timerTextView.getText().toString());
        data.putExtra("LongTimer",millis);
        setResult(RESULT_OK, data);

        super.finish();
    }

    @Override
    protected void onDestroy() {
        timerHandler.removeCallbacks(timerRunnable);
        super.onDestroy();
    }

    private void log(String message) {
        Log.i(TAG, message);
    }
}
