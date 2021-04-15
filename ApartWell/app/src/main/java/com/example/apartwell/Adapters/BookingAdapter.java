package com.example.apartwell.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apartwell.R;
import com.example.apartwell.models.Booking;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {
    protected List<Booking> bookingList;

    public BookingAdapter(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.booking_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String facility = bookingList.get(position).getFacility();
        holder.mBookingIDView.setText(bookingList.get(position).getBookingID());
        holder.mStatusView.setText(bookingList.get(position).getStatus());
        holder.mFacilityView.setText(bookingList.get(position).getFacility());
        holder.mDateView.setText(bookingList.get(position).getDate());
        holder.mTimeView.setText(bookingList.get(position).getTime());
        holder.mDurationView.setText(bookingList.get(position).getDuration());
        switch(facility){
            case "Badminton" : holder.mImageView.setImageResource(R.drawable.badminton);
            break;
            case "Tennis" : holder.mImageView.setImageResource(R.drawable.tennis);
            break;
            case  "Community Hall" : holder.mImageView.setImageResource(R.drawable.hall);
            break;
            case "Table Tennis" : holder.mImageView.setImageResource(R.drawable.tt);
            break;
        }
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImageView;
        public final TextView mBookingIDView;
        public final TextView mFacilityView;
        public final TextView mStatusView;
        public final TextView mDateView;
        public final TextView mTimeView;
        public final TextView mDurationView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = view.findViewById(R.id.booking_pic);
            mBookingIDView = view.findViewById(R.id.booking_id_txt);
            mFacilityView = view.findViewById(R.id.facility_txt);
            mStatusView = view.findViewById(R.id.status_txt);
            mDateView = view.findViewById(R.id.date_txt);
            mTimeView = view.findViewById(R.id.time_txt);
            mDurationView = view.findViewById(R.id.duration_txt);

        }

    }
}
