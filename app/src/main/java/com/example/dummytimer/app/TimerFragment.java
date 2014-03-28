package com.example.dummytimer.app;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by top on 28/3/2557.
 */
public class TimerFragment extends Fragment {
    private final String  TAG ="TimerFragment";
    private TextView timerTextView;
    private Button close_activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_timer,container,false);

        timerTextView=(TextView)v.findViewById(R.id.showTimer_running_textView);
        timerTextView.setText(new SimpleTimeFormat().parseLongMills(0));
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        close_activity=(Button)getView().findViewById(R.id.close_act);
        close_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Enter closeActivity");
                if(timerTextView.getText().toString()==null)log("timerTextView String is null");
                Intent data = new Intent();
                data.putExtra("LongTimer", TimerActivity.getMillis());
                getActivity().setResult(getActivity().RESULT_OK, data);
                getActivity().finish();

            }
        });
    }

    public void setTimerTextView(String text){
        timerTextView.setText(text);
    }


    private void log(String message){
        Log.i(TAG,message);
    }
}
