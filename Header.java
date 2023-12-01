package com.example.app;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Header extends AppCompatActivity {

    TextView ProfileName;
    ImageView ProfileImgIcon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.header);

        ProfileName=findViewById(R.id.Name_menu_header1);
        ProfileName.setText(R.string.CGPA);
    }
}
