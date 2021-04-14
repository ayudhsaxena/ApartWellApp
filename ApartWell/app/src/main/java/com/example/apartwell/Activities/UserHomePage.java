package com.example.apartwell.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apartwell.R;
import com.example.apartwell.models.User;

public class UserHomePage extends AppCompatActivity {
    public static User myUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_page);
        Intent intent = getIntent();
    }

    public void openBookings(View view){
        AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.5F);
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this,BookingActivity.class);
        startActivity(intent);
    }
}