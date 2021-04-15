package com.example.apartwell.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apartwell.Adapters.BookingAdapter;
import com.example.apartwell.R;
import com.example.apartwell.database.DatabaseHelper;
import com.example.apartwell.models.Booking;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class BookingActivity extends AppCompatActivity {

    protected RecyclerView recyclerView;
    protected RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    public static Snackbar bookingSnackBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        Intent intent = getIntent();
        String s = "New booking added succesfully!";
        ConstraintLayout layout = findViewById(R.id.booking_layout);
        bookingSnackBar = Snackbar.make(layout,s, BaseTransientBottomBar.LENGTH_LONG);
        if( intent.getStringExtra("from") != null && intent.getStringExtra("from").equals("createBooking")){
            bookingSnackBar.show();
        }
        recyclerView = findViewById(R.id.booking_list);
        recyclerView.setLayoutManager(layoutManager);
        DatabaseHelper db = DatabaseHelper.getInstance(this);
        List<Booking> bookings_list =  db.getBookings(UserHomePage.myUser.getUser_id());
        Log.d("SIZE","size = " + bookings_list.size());
        mAdapter = new BookingAdapter(bookings_list);
        recyclerView.setAdapter(mAdapter);
    }

    public void addBookingButton(View view){
        AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.5F);
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this,createBookingActivity.class);
        startActivity(intent);
    }

    public void backButton(View view){
        Intent intent = new Intent(this,UserHomePage.class);
        startActivity(intent);
    }
}