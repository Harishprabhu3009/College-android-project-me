package com.example.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TimeTableAdapter extends ArrayAdapter<TimeTableList> {
    public TimeTableAdapter(@NonNull Context context, ArrayList<TimeTableList> dataArrayAdapter) {
        super(context, R.layout.time_table_content, dataArrayAdapter);
    }


    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        TimeTableList timeTableList = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.time_table_content, parent, false);

            TextView Subject1 = view.findViewById(R.id.SubjectNameOfTimeTable);
            TextView ClassStength = view.findViewById(R.id.classStengthNumber);
            Subject1.setText(timeTableList.SubjectName);
            ClassStength.setText(timeTableList.ClassStength);

        }


        return view;
    }
}
