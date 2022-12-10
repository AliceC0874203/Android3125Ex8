package com.example.android_3125_ex8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.android_3125_ex8.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    // Constants
    public static final String Start = "Start";
    public static final String Stop = "Stop";
    public static final String Lap = "Lap";
    public static final String Reset = "Reset";
    CountDownTimer timer;
    double seconds = 0.0;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new TimeTableAdapter(this, R.layout.row_time_table_layout);
        binding.listView.setAdapter(adapter);

        binding.btnLapReset.setOnClickListener(this);
        binding.btnStartStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == binding.btnStartStop) {
            if (binding.btnStartStop.getText().toString().equals(Start)) {
                //Start
                binding.btnStartStop.setText(Stop);
                binding.btnLapReset.setText(Lap);
                runTimer();
            } else {
                //Stop
                stopTimer();
            }
        } else if (v == binding.btnLapReset) {
            if (binding.btnLapReset.getText().toString().equals(Lap)) {
                //Lap
                TimeObj.timeObjList.add(new TimeObj(binding.textTime.getText().toString()));
                adapter.notifyDataSetChanged();
            } else {
                //Reset
                resetTimer();
            }
        }
    }

    private void runTimer() {
        timer = new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {
                seconds += 1.0;
                binding.textTime.setText(timeToString());
            }

            public void onFinish() {
                timer.start();
            }
        }.start();
    }

    String timeToString() {
        double hours = seconds / 3600;
        double minutes = (seconds % 3600) / 60;
        double totalSeconds = seconds % 60;
        return String.format("%.0f:%.0f:%.0f", hours, minutes, totalSeconds);
    }

    void resetTimer() {
        seconds = 0.0;
        binding.textTime.setText("00:00:00");
        binding.btnLapReset.setText(Lap);
        TimeObj.timeObjList.removeAll(TimeObj.timeObjList);
        adapter.notifyDataSetChanged();
    }

    private void stopTimer() {
        binding.btnStartStop.setText(Start);
        binding.btnLapReset.setText(Reset);
        timer.cancel();
    }
}