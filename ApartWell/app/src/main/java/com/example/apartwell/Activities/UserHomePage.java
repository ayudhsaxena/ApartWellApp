package com.example.apartwell.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.apartwell.R;
import com.example.apartwell.models.User;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class UserHomePage extends AppCompatActivity {
    public static User myUser;
    protected Snackbar loginSnackBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_page);
        Intent intent = getIntent();
        String s = "Login successful!";
        ConstraintLayout layout = findViewById(R.id.userhomepage_layout);
        loginSnackBar = Snackbar.make(layout,s, BaseTransientBottomBar.LENGTH_LONG);
        if( intent.getStringExtra("from") != null && intent.getStringExtra("from").equals("loginPage")){
            loginSnackBar.show();
        }
    }

    public void openBookings(View view){
        AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.5F);
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this,BookingActivity.class);
        startActivity(intent);
    }

    public void openComplaints(View view){
        AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.5F);
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this, ComplaintActivity.class);
        startActivity(intent);
    }

    public void openProfile(View view){
        AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.5F);
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this, Profile_view.class);
        startActivity(intent);
    }

    public void openNoticeboard(View view){
        AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.5F);
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this, NoticeboardActivity.class);
        startActivity(intent);
    }
}