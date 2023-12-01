package com.example.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    EditText email_txt ,pass_txt;
    Button login ;
    private FirebaseAuth mAuth ;
    ProgressDialog dialog;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    ImageButton google_btn;
    String uid,email,name;

    private static  final int RC_SIGN_IN = 100;
    private static final String TAG ="GOOGLE_SIGN_IN_TAG";

    private GoogleSignInClient gClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email_txt = findViewById(R.id.email_con);
        pass_txt = findViewById(R.id.pass_con1);
        login=findViewById(R.id.log_btn);
        mAuth = FirebaseAuth.getInstance();
        google_btn=findViewById(R.id.google_img_btn);
        dialog=new ProgressDialog(LoginActivity.this);
        dialog.setCancelable(false);
        dialog.setMessage("LOADING.....");

        GoogleSignInOptions googleSignInOptions = new  GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        gClient =GoogleSignIn.getClient(this,googleSignInOptions);
        mAuth = FirebaseAuth.getInstance();


        google_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick : begin Google SignIn");
                Intent intent =gClient.getSignInIntent();
                startActivityForResult(intent,RC_SIGN_IN);

            }
        });





        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validateEmail() | !validatePassword()){
                    return;

                }else {
                    dialog.show();
                    AuthenticationLogin();

                }



            }

        });



        // TEXTVIEW CLICK


        TextView tv1 =findViewById(R.id.goto_new_account);
        TextView tv2 =findViewById(R.id.forget);
        String text1 ="Don't  Have  Account ? Create New Account";
        String text2 ="Forget Password ?";
        SpannableString ss = new SpannableString((text1));
        SpannableString sf = new SpannableString((text2));
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick( View view) {
                Toast.makeText(getApplicationContext(),"SIGN UP",Toast.LENGTH_LONG).show();
                Intent sign_intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(sign_intent);
            }
        };
        ClickableSpan clickableSpan2 =new ClickableSpan() {
            @Override
            public void onClick( View view) {
                Toast.makeText(getApplicationContext(),"FORGET PASSWORD",Toast.LENGTH_LONG).show();
                Intent forget_intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(forget_intent);
            }
        };
        ss.setSpan(clickableSpan1,23,41, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sf.setSpan(clickableSpan2,0,17, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tv1.setText(ss);
        tv1.setMovementMethod(LinkMovementMethod.getInstance());
        tv2.setText(sf);
        tv2.setMovementMethod(LinkMovementMethod.getInstance());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN){
            Log.d(TAG,"onActivityResult : Google SignIn intent Result");
            Task<GoogleSignInAccount> accountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account =accountTask.getResult(ApiException.class);
                FirebaseAuthWithGoogleAccount(account);

            }

            catch (Exception e){
                Log.d(TAG,"onActivityResult : "+e.getMessage());

            }
        }


    }

    private void FirebaseAuthWithGoogleAccount(GoogleSignInAccount account) {

        Log.d(TAG,"firebaseAuthWithGoogleAccount : begin firebase auth With google account");
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
        mAuth.signInWithCredential(credential)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Log.d(TAG,"onSuccess : Logged In");

                        FirebaseUser firebaseUser =mAuth.getCurrentUser();
                        uid =firebaseUser.getUid();
                        email =firebaseUser.getEmail();
                        name =firebaseUser.getDisplayName();

                        Log.d(TAG,"onSuccess : Email: "+email);
                        Log.d(TAG,"onSuccess : UID: "+uid);

                        ////

                        if (authResult.getAdditionalUserInfo().isNewUser()){

                            ////
                            Toast.makeText(LoginActivity.this, "Account Created...\n"+email, Toast.LENGTH_SHORT).show();

                        }else {
                            Log.d(TAG,"onSuccess : Existing user ..\n"+email);
                            Toast.makeText(LoginActivity.this, "Existing user ..\n"+email, Toast.LENGTH_SHORT).show();
                        }
                        UidForDataBase();

                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                        finish();



                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG,"onSuccess : Logged Failed"+e.getMessage());
                    }
                });

    }


    private void AuthenticationLogin() {
        String email =email_txt.getText().toString().trim();
        String password=pass_txt.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()){
                Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                Intent login =new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(login);
            }else {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Login Failed!!", Toast.LENGTH_SHORT).show();
            }
            }
        });
    }

    // storage of uid
    private void UidForDataBase(){

        database=FirebaseDatabase.getInstance();
        reference =database.getReference("users");

        UidGetClass aClass =new UidGetClass(name,uid,email);
        reference.child(name).setValue(aClass);


    }

    //email validate on login
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

    //password validate on login
    private Boolean validatePassword() {
        String val = pass_txt.getText().toString();
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
            pass_txt.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            pass_txt.setError("Password is too weak");
            return false;
        } else {
            pass_txt.setError(null);
            return true;
        }
    }


}
