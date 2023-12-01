package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class SyllabusActivity extends AppCompatActivity {
    ImageButton SyllabusBackBtn;
    int i;
    DatabaseReference database;


    //SyllabusAdapter adapterSyllabus ;
    //SyllabusList listSyllabus;
    //ArrayList<SyllabusList> dataArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);

        SyllabusBackBtn=findViewById(R.id.SyllabusBackBtn);

        ListView listViewSyllabus = findViewById(R.id.SyllabusListView);

       /* int [] SyllabusImage ={R.drawable.c,R.drawable.dotnet,R.drawable.java};
        int [] SyllabusClassLength ={20,30,50};
        String [] SyllabusSubjectOnSyllabus = {"C PROGRAMMING","DOTNET PROGRAMMING","JAVA PROGRAMMING"};
        String [] SyllabusTitleOnSyllabus ={"C DATATYPES","DOTNET FRAMEWORK","OOPS CONCEPTS"};
        String [] SyllabusSubTitle ={"UNIT-I","UNIT-V","UNIT-III"};

        for (i = 0; i < SyllabusImage.length;i++){
            listSyllabus =new SyllabusList(SyllabusImage[i],SyllabusClassLength[i],SyllabusSubjectOnSyllabus[i],SyllabusTitleOnSyllabus[i],SyllabusSubTitle[i]);
            dataArrayList.add(listSyllabus);
        }
        adapterSyllabus =new SyllabusAdapter(SyllabusActivity.this,dataArrayList);
        listViewSyllabus.setAdapter(adapterSyllabus);
        listViewSyllabus.setClickable(true);

        listViewSyllabus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
            }
        });*/







        SyllabusBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });










    }
}