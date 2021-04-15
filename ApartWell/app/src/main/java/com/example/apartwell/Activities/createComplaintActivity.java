package com.example.apartwell.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apartwell.R;
import com.example.apartwell.database.DatabaseHelper;
import com.example.apartwell.models.Complaint;

import java.util.Calendar;
import java.util.Date;

public class createComplaintActivity extends AppCompatActivity {
    protected EditText mDescription;
    protected Switch mSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_complaint);
        mDescription = findViewById(R.id.description_txt);
        mSwitch = findViewById(R.id.urgent_switch);
    }

    public void registerComplaintButton(View view){
        Complaint c = new Complaint();
        c.setUser_id(UserHomePage.myUser.getUser_id());
        c.setComments("NIL");
        c.setStatus("CREATED");
        c.setDescription(mDescription.getText().toString());

        Date currentTime = Calendar.getInstance().getTime();
        c.setDate_time(currentTime.toString());
        if(mSwitch.isChecked()){
            c.setUrgent("YES");
        }
        else
            c.setUrgent("NO");

        DatabaseHelper db = DatabaseHelper.getInstance(this);
        db.addComplaint(c);

        Intent intent = new Intent(this,ComplaintActivity.class);
        intent.putExtra("from","createComplaint");
        startActivity(intent);
    }

}