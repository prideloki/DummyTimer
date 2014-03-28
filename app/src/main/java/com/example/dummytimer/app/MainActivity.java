package com.example.dummytimer.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    final int REQUEST_TIME=1;
    private long totalMillis=0;
    private long totalMillis2=0;

    TextView timeTextView;
    TextView timeTextView2;

    SimpleTimeFormat timeFormatter=new SimpleTimeFormat();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeTextView=(TextView)findViewById(R.id.totalTime);
        timeTextView2=(TextView)findViewById(R.id.totalTime2);

        timeTextView.setText(timeFormatter.parseLongMills(0));
        timeTextView2.setText(timeFormatter.parseLongMills(0));

        timeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent timerIntent=new Intent(getApplicationContext(),TimerActivity.class);

                startActivityForResult(timerIntent, 0);
            }
        });
        timeTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent timerIntent=new Intent(getApplicationContext(),TimerActivity.class);
                startActivityForResult(timerIntent, 1);
            }
        });
        Button reset=(Button)findViewById(R.id.reset_activity);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeTextView.setText(timeFormatter.parseLongMills(0));
                timeTextView2.setText(timeFormatter.parseLongMills(0));
                totalMillis=0;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){

            long time=data.getExtras().getLong("LongTimer");

            switch (requestCode){
                case 0:
                    Log.i("Test", "tw 1");
                    totalMillis+=time;
                    timeTextView.setText(timeFormatter.parseLongMills(totalMillis));
                    break;
                case 1:
                    totalMillis2+=time;
                    timeTextView2.setText(timeFormatter.parseLongMills(totalMillis2));
                    break;
            }



        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(),"Tap Settings",Toast.LENGTH_SHORT).show();
            Intent settingsIntent=new Intent(MainActivity.this,Settings.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
