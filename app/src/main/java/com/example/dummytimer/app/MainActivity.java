package com.example.dummytimer.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    final int REQUEST_TIME=1;
    private long totalMillis=0;
    TextView timeTextView;
    SimpleTimeFormat timeFormatter=new SimpleTimeFormat();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeTextView=(TextView)findViewById(R.id.totalTime);
        timeTextView.setText(timeFormatter.parseLongMills(0));
        Button start=(Button)findViewById(R.id.start_activity);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent timerIntent=new Intent(getApplicationContext(),TimerActivity.class);
                startActivityForResult(timerIntent, REQUEST_TIME);

            }
        });
        Button reset=(Button)findViewById(R.id.reset_activity);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeTextView.setText(timeFormatter.parseLongMills(0));
                totalMillis=0;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_TIME&&resultCode==RESULT_OK){

            long time=data.getExtras().getLong("LongTimer");
            totalMillis+=time;
            timeTextView.setText(timeFormatter.parseLongMills(totalMillis));
            Toast.makeText(getApplicationContext(),time+"",Toast.LENGTH_SHORT).show();

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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
