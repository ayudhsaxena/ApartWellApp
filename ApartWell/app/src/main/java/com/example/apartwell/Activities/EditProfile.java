package com.example.apartwell.Activities;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apartwell.R;
import com.example.apartwell.database.DatabaseHelper;
import com.example.apartwell.models.User;

public class EditProfile extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        User u = UserHomePage.myUser;

        EditText fname_text = findViewById(R.id.hint1);
        fname_text.setText(u.getFirstName());

        EditText lname_text = findViewById(R.id.hint2);
        lname_text.setText(u.getSecondName());

        EditText phone_text = findViewById(R.id.hint3);
        phone_text.setText(u.getMobileNo());

        EditText email_text = findViewById(R.id.hint4);
        email_text.setText(u.getEmail());

        Intent intent = getIntent();

    }

    public void change_data(View view)
    {
        User u = UserHomePage.myUser;

        EditText fname_text =  findViewById(R.id.hint1);
        EditText lname_text =  findViewById(R.id.hint2);
        EditText phone_text =  findViewById(R.id.hint3);
        EditText email_text =  findViewById(R.id.hint4);

        String pass_fname = fname_text.getText().toString();
        String pass_lname = lname_text.getText().toString();
        String pass_email = email_text.getText().toString();
        String pass_phone = phone_text.getText().toString();

        //update_user(u, pass args);
        u.setFirstName(pass_fname);
        u.setSecondName((pass_lname));
        u.setMobileNo(pass_phone);
        u.setEmail(pass_email);

        DatabaseHelper db = DatabaseHelper.getInstance(this);
        db.addUser(u);

        Intent intent = new Intent(this, Profile_view.class);
        startActivity(intent);
    }

}


