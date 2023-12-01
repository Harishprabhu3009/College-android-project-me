package com.example.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuizContentActivity extends AppCompatActivity {
    public static ArrayList<questionClass> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_content);


        TextView SimpleTxT =findViewById(R.id.Quiz_intent_Content);
        RecyclerView listView =findViewById(R.id.ListViewQuiz);







        list=new ArrayList<>();
        list.add(new questionClass( "Which is the part of the computer system that one can physically touch?"," data","operating systems","hardware","software","hardware" ));
        list.add(new questionClass( "A ………. is an electronic device that process data, converting it into information?","computer","processor"," case","stylus","computer" ));
        list.add(new questionClass( "IC chips used in computers are usually made of:","Lead","Silicon","Chromium","Gold","Silicon" ));
        list.add(new questionClass( "Which of the following is not an example of an Operating System?","Windows 98","BSD Unix","Microsoft Office XP","Red Hat Linux","Microsoft Office XP" ));
       list.add(new questionClass( "One Gigabyte is approximately equal is:","1000,000 bytes","1000,000,000 bytes","1000,000,000,000 bytes","None of these","1000,000,000 bytes" ));

        SimpleTxT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Question =new Intent(QuizContentActivity.this,QuizQuestionActivity.class);
                startActivity(Question);
            }
        });
    }
}