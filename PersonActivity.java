package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PersonActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        bottomNavigationView=findViewById(R.id.Bottom_Navigated);

        bottomNavigationView.setSelectedItemId(R.id.bottom_web_icon);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.bottom_notified:

                        startActivity(new Intent(getApplicationContext(),NotificationActivity.class));
                        overridePendingTransition(0,0);

                        return true;
                    case  R.id.bottom_home:
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.bottom_feed_back:
                        startActivity(new Intent(getApplicationContext(),FeedBackActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.bottom_web_icon:
                        return true;

                }


                return false;
            }
        });




    }
}