package com.example.android_3125_ex8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android_3125_ex8.databinding.RowTimeTableLayoutBinding;

public class TimeTableAdapter extends ArrayAdapter {

    public TimeTableAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public int getCount() {
        return TimeObj.timeObjList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        RowTimeTableLayoutBinding binding = RowTimeTableLayoutBinding.inflate(LayoutInflater.from(getContext()));

        binding.textTimeStamp.setText(TimeObj.timeObjList.get(position).getTime());
        binding.textLapStamp.setText("Lap " + (position + 1));

        return binding.getRoot();
    }
}


