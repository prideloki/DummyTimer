package com.example.dummytimer.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

/**
 * Created by top on 27/3/2557.
 */
public class Settings extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final ToggleButton pomdoro=(ToggleButton)findViewById(R.id.togglePomdoro);
        final ToggleButton unlimit=(ToggleButton)findViewById(R.id.toggleUnlimit);
        final ToggleButton autoring=(ToggleButton)findViewById(R.id.toggleAutoring);
        if(TimerActivity.isIsPomodoro()){
            pomdoro.setChecked(true);
            unlimit.setChecked(false);
        }else{
            pomdoro.setChecked(false);
            unlimit.setChecked(true);
        }

        if(TimerActivity.isIsAutoring())autoring.setChecked(true);

        pomdoro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    unlimit.setChecked(false);

                }else{
                    unlimit.setChecked(true);
                }

                TimerActivity.setPomodoro(true);
            }
        });

        unlimit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    pomdoro.setChecked(false);

                }else{
                    pomdoro.setChecked(true);
                }

                TimerActivity.setPomodoro(false);
            }
        });
        autoring.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    TimerActivity.setIsAutoring(true);
                }else{
                    TimerActivity.setIsAutoring(false);
                }
            }
        });
    }
}
