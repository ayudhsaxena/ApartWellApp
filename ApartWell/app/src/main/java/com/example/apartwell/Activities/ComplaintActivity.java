package com.example.apartwell.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apartwell.Adapters.ComplaintAdapter;
import com.example.apartwell.R;
import com.example.apartwell.database.DatabaseHelper;
import com.example.apartwell.models.Complaint;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ComplaintActivity extends AppCompatActivity {

    protected RecyclerView recyclerView;
    protected RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    public static Snackbar complaintSnackBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        Intent intent = getIntent();
        String s = "Complaint registered successfully!";
        ConstraintLayout layout = findViewById(R.id.complaint_layout);
        complaintSnackBar = Snackbar.make(layout,s, BaseTransientBottomBar.LENGTH_LONG);
        if( intent.getStringExtra("from") != null && intent.getStringExtra("from").equals("createComplaint")){
            complaintSnackBar.show();
        }
        recyclerView = findViewById(R.id.complaint_list);
        recyclerView.setLayoutManager(layoutManager);
        DatabaseHelper db = DatabaseHelper.getInstance(this);
        List<Complaint> complaintList =  db.getComplaints(UserHomePage.myUser.getUser_id());
        mAdapter = new ComplaintAdapter(complaintList);
        recyclerView.setAdapter(mAdapter);
    }

    public void addComplaintButton(View view){
        AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.5F);
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this,createComplaintActivity.class);
        startActivity(intent);
    }

    public void backButton(View view){
        AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.5F);
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this,UserHomePage.class);
        startActivity(intent);
    }
}