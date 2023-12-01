package com.example.app;

import static com.example.app.QuizContentActivity.list;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.Collections;
import java.util.List;

public class QuizQuestionActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    int timeValue=20;
    int index=0;
    int QuestionNo=1;
    int correctCount=0;
    int wrongCount=0;
    List<questionClass> allQuestionList;


    questionClass aClass;
    TextView cardQuestion,optionA,optionB,optionC,optionD;
    TextView Text_count;
    CardView cardOA,cardOB,cardOC,cardOD;
    Button nextBtn,exitBtn;
    ImageButton backBtn;
    ProgressBar Progress_bar_quiz;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question);



        Hooks();






        allQuestionList=list;
        Collections.shuffle(allQuestionList);
        aClass=list.get(index);
        setAllData();
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ExitBuildDialog();
            }
        });

        countDownTimer = new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeValue=timeValue-1;
                Progress_bar_quiz.setProgress(timeValue);


            }
            @Override
            public void onFinish() {
                /*Dialog dialog =new Dialog(QuizQuestionActivity.this);
                correctCount++;
                index++;
                QuestionNo++;
                QuestionText();
                aClass = list.get(index);
                Toast.makeText(getApplicationContext(), "QUESTION NO : "+QuestionNo, Toast.LENGTH_SHORT).show();
                resetColor();
                setAllData();
                enableButton();*/



            }
        }.start();

    }

    private void ExitBuildDialog() {
        AlertDialog .Builder builder=new AlertDialog.Builder(QuizQuestionActivity.this);
        builder.setTitle("EXIT");
        builder.setMessage("ARE YOU SURE YOU WANT TO EXIT THIS QUIZ EXAM");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();

            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              dialog.cancel();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    private void setAllData() {


        cardQuestion.setText(aClass.getQuestion());
        optionA.setText(aClass.getoA());
        optionB.setText(aClass.getoB());
        optionC.setText(aClass.getoC());
        optionD.setText(aClass.getoD());
    }
    public void Correct(CardView cardView){
        cardView.setBackgroundColor(getResources().getColor(R.color.green));

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctCount++;
                index++;
                QuestionNo++;
                QuestionText();
                aClass = list.get(index);
                Toast.makeText(getApplicationContext(), "QUESTION NO : "+QuestionNo, Toast.LENGTH_SHORT).show();
                resetColor();
                setAllData();
                enableButton();


            }
        });

    }

    private void QuestionText() {
        Text_count.setText("QUESTION : "+QuestionNo);
    }

    public void Wrong(CardView cardOA){
        cardOA.setBackgroundColor(getResources().getColor(R.color.red));

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                wrongCount++;
                if (index<list.size()-1){
                    index++;
                    QuestionNo++;
                    aClass=list.get(index);
                    QuestionText();
                    Toast.makeText(getApplicationContext(), "QUESTION NO : "+QuestionNo, Toast.LENGTH_SHORT).show();
                    resetColor();
                    setAllData();
                    enableButton();



                }else {
                    GameWon();
                }

            }
        });

    }

    private void GameWon() {
        Intent i =new Intent(QuizQuestionActivity.this,QuizResultActivity.class);
        i.putExtra("Correct",correctCount);
        i.putExtra("Wrong",wrongCount);
        i.putExtra("QuestionNo",QuestionNo);
        startActivity(i);

    }

    private void Hooks() {


        cardQuestion=findViewById(R.id.Text_question);
        optionA=findViewById(R.id.Text_Answer_A);
        optionB=findViewById(R.id.Text_Answer_B);
        optionC=findViewById(R.id.Text_Answer_C);
        optionD=findViewById(R.id.Text_Answer_D);

        cardOA=findViewById(R.id.Answer_A);
        cardOB=findViewById(R.id.Answer_B);
        cardOC=findViewById(R.id.Answer_C);
        cardOD=findViewById(R.id.Answer_D);

        exitBtn=findViewById(R.id.exit_btn_quiz);
        backBtn=findViewById(R.id.back_btn_quizQuestion);

        Progress_bar_quiz=findViewById(R.id.Progress_bar_quiz);


        Text_count=findViewById(R.id.Text_count);

        nextBtn=findViewById(R.id.next_btn_quiz);
    }
    public void DisableButton(){
        cardOA.setClickable(false);
        cardOB.setClickable(false);
        cardOC.setClickable(false);
        cardOD.setClickable(false);
    }
    public void enableButton(){
        cardOA.setClickable(true);
        cardOB.setClickable(true);
        cardOC.setClickable(true);
        cardOD.setClickable(true);
    }

    public void resetColor(){
        cardOA.setBackgroundColor(getResources().getColor(R.color.white));
        cardOB.setBackgroundColor(getResources().getColor(R.color.white));
        cardOC.setBackgroundColor(getResources().getColor(R.color.white));
        cardOD.setBackgroundColor(getResources().getColor(R.color.white));
    }


    public void optionA_click(View view) {
        DisableButton();
        nextBtn.setClickable(true);
        if (aClass.getoA().equals(aClass.getAns())){
            cardOA.setBackgroundColor(getResources().getColor(R.color.green));

            if (index < list.size() -1 ){
                Correct(cardOA);
            }else {
                GameWon();
                
            }
        }else {
            Wrong(cardOA);
        }
    }

    public void optionB_click(View view) {
        DisableButton();
        nextBtn.setClickable(true);
        if (aClass.getoB().equals(aClass.getAns())){
            cardOB.setBackgroundColor(getResources().getColor(R.color.green));


            if (index < list.size() -1 ){
                Correct(cardOB);
            }else {
                GameWon();

            }
        }else {
            Wrong(cardOB);

        }
    }

    public void optionC_click(View view) {
        DisableButton();
        nextBtn.setClickable(true);
        if (aClass.getoC().equals(aClass.getAns())){
            cardOC.setBackgroundColor(getResources().getColor(R.color.green));

            if (index < list.size() -1 ){
                Correct(cardOC);
            }else {
                GameWon();

            }
        }else {
            Wrong(cardOC);

        }
    }

    public void optionD_click(View view) {
        DisableButton();
        nextBtn.setClickable(true);
        if (aClass.getoD().equals(aClass.getAns())){
            cardOD.setBackgroundColor(getResources().getColor(R.color.green));

            if (index < list.size() -1 ){
                Correct(cardOD);
            }else {
                GameWon();

            }
        }else {
            Wrong(cardOD);

        }
    }
}