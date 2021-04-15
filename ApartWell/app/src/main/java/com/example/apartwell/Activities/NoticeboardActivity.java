package com.example.apartwell.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apartwell.R;

public class NoticeboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticeboard);
    }

    public void backButton(View view){
        Intent intent = new Intent(this,UserHomePage.class);
        startActivity(intent);
    }
}