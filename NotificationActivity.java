package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageButton ImageBackBtn;
    private static final String TAG ="NOTIFICATION_ADAPTER_TAG";
    private static final String TAG1 ="BOTTOM_NAVIGATION_TAG";
    private static final String TAG2 ="NOTIFICATION_BACK_BTN_TAG";

    RecyclerView  recyclerView;
    DatabaseReference databaseReference;
    NotificationAdapter adapter;
    ArrayList<NotificationList> dataList;
    String [] Title = new String[] {
            "Sports",
            "Games",
            "Evens"
    };
    String [] Contents = new String[] {
            "The sports is the one of the best Games in india",
            "Games is my also like is on Online Games",
            "Evens Will be contacted by previous year 0n Android"
    };

    String [] Date =new String[] {
            "20/02/2023",
            "24/05/2023",
            "10/03/2023"
    };

    ValueEventListener eventListener ;

    //private  NotificationAdapter adapter;
    //private final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    //private final List<NotificationList> dataList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


        bottomNavigationView=findViewById(R.id.Bottom_Navigated);
        ImageBackBtn=findViewById(R.id.NotificationBackBtn);

        recyclerView =findViewById(R.id.NotificationRecyclerView);



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);







        /*recyclerView.setHasFixedSize(true);
 */


        //recyclerView.setLayoutManager(new LinearLayoutManager(NotificationActivity.this));
        //databaseReference=FirebaseDatabase.getInstance().getReference();

        dataList=new ArrayList<>();

        adapter =new NotificationAdapter(this,dataList);
        recyclerView.setAdapter(adapter);


        for (int i = 0 ;i<Title.length;i++)
        {
            NotificationList  list =new NotificationList(Title[i],Contents[i],Date[i]);
            dataList.add(list);
            adapter.notifyDataSetChanged();
        }
      /* databaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {

               dataList.clear();
               for (DataSnapshot NotificationApp : snapshot.child("NotificationApp").getChildren()){


                   if (NotificationApp.hasChild("NotifiedTitle") && NotificationApp.hasChild("NotifiedContents") && NotificationApp.hasChild("NotifiedDate"))
                   {
                       String getTitle = NotificationApp.child("NotifiedTitle").getValue(String.class);
                       String getContents = NotificationApp.child("NotifiedContents").getValue(String.class);
                       String getDate = NotificationApp.child("NotifiedDate").getValue(String.class);

                       NotificationList List = new NotificationList(getTitle,getContents,getDate);
                       dataList.add(List);


                   }

                   adapter =new NotificationAdapter(NotificationActivity.this,dataList);
                   recyclerView.setAdapter(adapter);

               }

           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {


               Toast.makeText(NotificationActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

           }
       });*/

        //dataList = new ArrayList<>();

        //adapter=new NotificationAdapter(NotificationActivity.this,dataList);
        //recyclerView.setAdapter(adapter);






       /* databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for(DataSnapshot dataSnapshot:snapshot.child("NotificationApp").getChildren()){
                    NotificationList user = dataSnapshot.getValue(NotificationList.class);
                    dataList.add(user);
                }

                adapter.notifyDataSetChanged();































            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


*/









        ImageBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG2,"onBackBtn : Successed");
                startActivity(new Intent(NotificationActivity.this,HomeActivity.class));
                finish();
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.bottom_notified);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.bottom_notified:
                        Log.d(TAG1,"onBottomNavigation : Notification is success");

                        return true;
                    case  R.id.bottom_home:
                        Log.d(TAG1,"onBottomNavigation : Home is success");
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.bottom_feed_back:
                        Log.d(TAG1,"onBottomNavigation : FeedBackActivity is success");
                        startActivity(new Intent(getApplicationContext(),FeedBackActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.bottom_web_icon:
                        Log.d(TAG1,"onBottomNavigation : PresonActivity is success");
                        startActivity(new Intent(getApplicationContext(),PersonActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }


                Log.d(TAG1,"onBottomNavigation : Failed");
                return false;
            }
        });




    }
}