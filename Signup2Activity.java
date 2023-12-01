package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Signup2Activity extends AppCompatActivity {

    EditText email_txt, password_txt, re_password_txt;
    Button submit_btn;
    FirebaseAuth mAuth;
    ProgressDialog dialog;
    String uid;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        TextView login_gogo = findViewById(R.id.goto_login);
        mAuth = FirebaseAuth.getInstance();
        dialog=new ProgressDialog(Signup2Activity.this);
        dialog.setCancelable(false);
        dialog.setMessage("LOADING.....");

        email_txt = findViewById(R.id.email_sign);
        password_txt = findViewById(R.id.pass_sign);
        re_password_txt = findViewById(R.id.re_pass_con1);
        submit_btn = findViewById(R.id.sub_sign2_btn);




        //login gogo
        String text1 = "Already Have Account ? Login";
        SpannableString ss = new SpannableString((text1));
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(getApplicationContext(), "LOGIN", Toast.LENGTH_LONG).show();
                Intent Login_intent = new Intent(Signup2Activity.this, LoginActivity.class);
                startActivity(Login_intent);

            }
        };
        ss.setSpan(clickableSpan1, 23, 28, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        login_gogo.setText(ss);
        login_gogo.setMovementMethod(LinkMovementMethod.getInstance());



        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validateEmail() || !validatePassword() || !validateConfirmPassword() ){
                    return;
                } else  {
                    dialog.show();
                    signUpData();
                    AuthencationSignup();
                   // SendEmailLogin();

                }

            }
        });


    }

   /* private void SendEmailLogin() {
        String LoginEmail =email_txt.getText().toString();
        SendMail mail =new SendMail("skcbcastudent@gmail.com","HARISH PRABHU9894209220",""+LoginEmail.toString(),"SIGNUP SUCCESSFULLY ","WEL COME TO SKC CLASS ROOM APP");
        mail.execute();
    }*/

    private void AuthencationSignup() {
       String email = email_txt.getText().toString().trim();
       String password = password_txt.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Account Create successful!", Toast.LENGTH_SHORT).show();
                    FirebaseUser firebaseUser =mAuth.getCurrentUser();
                    uid =firebaseUser.getUid();
                    dialog.dismiss();
                    Intent compAuth =new Intent(Signup2Activity.this,LoginActivity.class);
                    startActivity(compAuth);
                }else {
                    Toast.makeText(getApplicationContext(), "Signup Failed "+" Please try again Later", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                }





            }

        });

    }

    private void signUpData() {

        Intent sign2=getIntent();
        database=FirebaseDatabase.getInstance();
        reference =database.getReference("users");

        String EMAIL_ID=email_txt.getText().toString().trim();
        String PASSWORD_ID=password_txt.getText().toString().trim();
        String NAME =sign2.getStringExtra("NAME");
        String DEPT =sign2.getStringExtra("DEPT");
        String YEAR =sign2.getStringExtra("YEAR");
        String SECTION =sign2.getStringExtra("SECTION");
        String ROLL =sign2.getStringExtra("ROLL");
        String PHONE =sign2.getStringExtra("PHONE");


        HelperClass aClass = new HelperClass(NAME,DEPT,YEAR,SECTION,uid,ROLL,PHONE,EMAIL_ID,PASSWORD_ID);
        reference.child(ROLL).setValue(aClass);

    }



    private Boolean validateEmail() {
        String val = email_txt.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            email_txt.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            email_txt.setError("Invalid email address");
            return false;
        } else {
            email_txt.setError(null);
            return true;
        }
    }


    private Boolean validatePassword() {
        String val = password_txt.getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            password_txt.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            password_txt.setError("Password is too weak");
            return false;
        } else {
            password_txt.setError(null);
            return true;
        }
    }
    private Boolean validateConfirmPassword() {
        String val = re_password_txt.getText().toString();
        String passwordVal = "^" +
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            re_password_txt.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            re_password_txt.setError("Password is too weak");
            return false;
        } else {
            re_password_txt.setError(null);
            return true;
        }
    }
    private Boolean validateFullPass() {



        if (password_txt.equals(re_password_txt)) {
            re_password_txt.setError(null);
            return true;
        }else {
            re_password_txt.setError("Password can be same!!");
            return false;
        }
    }

}