package com.example.apartwell.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apartwell.Adapters.BookingAdapter;
import com.example.apartwell.R;
import com.example.apartwell.database.DatabaseHelper;
import com.example.apartwell.models.Booking;

import java.util.List;

public class BookingActivity extends AppCompatActivity {

    protected RecyclerView recyclerView;
    protected RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        Intent intent = getIntent();
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
}