package com.example.apartwell.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apartwell.R;
import com.example.apartwell.database.DatabaseHelper;
import com.example.apartwell.models.Booking;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class createBookingActivity extends AppCompatActivity {
    protected Spinner mSpinner;
    protected DatePicker mDate;
    protected TimePicker mTime;
    protected EditText mDuration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_booking);
        Intent intent = getIntent();
        mSpinner = findViewById(R.id.facility_list);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.facility_Array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mDate = findViewById(R.id.datePicker);
        mTime = findViewById(R.id.tp_timepicker);
        mDuration = findViewById(R.id.duration_edit);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void createBookingButton(View view){
        String facility = mSpinner.getSelectedItem().toString();
        int   day  = mDate.getDayOfMonth();
        int   month= mDate.getMonth();
        int   year = mDate.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formatedDate = sdf.format(calendar.getTime());

        int hour = mTime.getHour();
        int minute = mTime.getMinute();
        String time = hour + " : " + minute;

        String duration = mDuration.getText().toString();

        Booking b = new Booking();
        b.setUserID(UserHomePage.myUser.getUser_id());
        b.setFacility(facility);
        b.setStatus("WAIT");
        b.setDate(formatedDate);
        b.setTime(time);
        b.setDuration(duration);
        DatabaseHelper db = DatabaseHelper.getInstance(this);
        db.addBooking(b);
        Intent intent = new Intent(this,BookingActivity.class);
        intent.putExtra("from","createBooking");
        startActivity(intent);
    }
}