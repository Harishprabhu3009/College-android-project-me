package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TimeTableActivity extends AppCompatActivity {
    ImageButton backBtn;
    ImageView OnGoing;

    TextView SubjectName ,GoingText;
    int i;

    TimeTableList timeTableList;
    TimeTableAdapter timeTableAdapter;
    ArrayList<TimeTableList> dataArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        backBtn =findViewById(R.id.TimeTableBackBtn);
        ListView TimeTable=findViewById(R.id.TimeTableListView);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        String[] SubjectList ={"TAMIL","ENGLISH","DATA MINING AND BIOINFORMATICS"};
        String[] ClassList ={"QUIZ TEST1","QUIZ TEST2","QUIZ TEST3"};


        for (i = 0; i < SubjectList.length;i++){
            timeTableList =new TimeTableList(SubjectList[i],ClassList[i]);
            dataArrayList.add(timeTableList);
        }
        timeTableAdapter =new TimeTableAdapter(TimeTableActivity.this,dataArrayList);
        TimeTable.setAdapter(timeTableAdapter);
        TimeTable.setClickable(true);


        TimeTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_SHORT).show();


            }
        });






    }
}