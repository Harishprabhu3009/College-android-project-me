package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.color.utilities.Score;

public class QuizResultActivity extends AppCompatActivity {

    TextView attempt1,attempt2;
    int Correct;
    int Wrong;
    Float Score;
    ImageView ShareIntentOnQuiz;
    int QuestionNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result2);


        ShareIntentOnQuiz=findViewById(R.id.ShareIntentOnQuiz);
        attempt1=findViewById(R.id.attempt1);
        attempt2=findViewById(R.id.attempt2);
        Correct=getIntent().getIntExtra("Correct",0);
        Wrong=getIntent().getIntExtra("Wrong",0);
        QuestionNo=getIntent().getIntExtra("QuestionNo",0);

        Score = (float) (QuestionNo / 2);
        if (Score > 3) {
            Toast.makeText(getApplicationContext(), "Pass", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(getApplicationContext(), "Score : "+Score, Toast.LENGTH_SHORT).show();



        ResultContent();

        ShareIntentOnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,"ThankYou For Your Participated On the Our QuizExam");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,"Correct Answers : "+Correct);
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,"Out Of "+QuestionNo+" Questions");
                startActivity(shareIntent);
            }
        });




    }

    private void ResultContent() {
        attempt1.setText("You attempt "+QuestionNo+" Questions an");
        attempt2.setText("From that "+Correct+" Answer is Correct");
    }
}