package com.example.apartwell.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apartwell.R;
import com.example.apartwell.models.User;

public class Profile_view extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_view);
        Intent intent = getIntent();
        User u = UserHomePage.myUser;


        TextView name_text = findViewById(R.id.name_txt);
        name_text.setText(u.getFirstName() + " " + u.getSecondName());

        TextView phone_text = findViewById(R.id.phone_txt);
        phone_text.setText(u.getMobileNo());

        TextView username_text = findViewById(R.id.username_txt);
        username_text.setText(u.getUsername());

        TextView email = findViewById(R.id.email_txt);
        email.setText(u.getEmail());

        TextView phone = findViewById(R.id.phone_txt);
        phone.setText(u.getMobileNo());

    }

    public void Logout(View view){
        UserHomePage.myUser = null;
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("from","logout");
        startActivity(intent);
    }

    public void EditProfile(View view){
        Intent intent = new Intent(this, EditProfile.class);
        startActivity(intent);
    }

}
