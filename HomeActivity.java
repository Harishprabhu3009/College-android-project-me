package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth;

    CardView cardView1,cardView2,cardView3,cardView4;
    GoogleSignInClient client;
    BottomNavigationView bottomNavigationView;
    GoogleSignInOptions options;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        cardView1 = findViewById(R.id.card1);
        cardView2 = findViewById(R.id.card2);
        cardView3 = findViewById(R.id.card3);
        cardView4 = findViewById(R.id.card4);
        bottomNavigationView=findViewById(R.id.Bottom_Navigated);

       // ProfileName =findViewById(R.id.Name_menu_header1);
        //ProfileImgIcon=findViewById(R.id.ProfileImg);

        drawerLayout =findViewById(R.id.drawableNavigation);
        Toolbar toolbar= findViewById(R.id.toolbar_home1);

        NavigationView  navigationView =findViewById(R.id.navigate_view1);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.nav_open,R.string.nav_close);


        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();













        bottomNavigationView.setSelectedItemId(R.id.bottom_home);
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
                        return true;
                    case R.id.bottom_feed_back:
                        startActivity(new Intent(getApplicationContext(),FeedBackActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.bottom_web_icon:
                        startActivity(new Intent(getApplicationContext(),PersonActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }


                return false;
            }
        });
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Syllabus =new Intent(HomeActivity.this,SyllabusActivity.class);
                startActivity(Syllabus);
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TimeTable =new Intent(HomeActivity.this,TimeTableActivity.class);
                startActivity(TimeTable);
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Assignment =new Intent(HomeActivity.this,AssignmentActivity.class);
                startActivity(Assignment);
            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent QuizTest =new Intent(HomeActivity.this,QuizContentActivity.class);
                startActivity(QuizTest);


            }
        });





    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.NavidationDashboard:
                startActivity(new Intent(getApplicationContext(), PersonActivity.class));
                overridePendingTransition(0, 0);
                return true;

            case R.id.NavidationLOGOUT_MENU:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                overridePendingTransition(0, 0);
                return true;

            case R.id.NavidationCourses:
                Toast.makeText(this, "Courses is Selected", Toast.LENGTH_SHORT).show();
                overridePendingTransition(0, 0);
                return true;

            case R.id.NavidationSHARE:
                ShareIntent();
                overridePendingTransition(0, 0);
                return true;

            case R.id.NavidationFEEDBACK:
                startActivity(new Intent(getApplicationContext(), FeedBackActivity.class));
                overridePendingTransition(0, 0);
                return true;

            case R.id.NavidationNotification:
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
                overridePendingTransition(0, 0);
                return true;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {

            super.onBackPressed();
        }
    }
    private void ShareIntent(){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Hey, download this app!");
        startActivity(shareIntent);
    }
}



