package com.example.apartwell.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apartwell.R;
import com.example.apartwell.models.Complaint;

import java.util.List;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ViewHolder> {
    protected List<Complaint> complaintList;

    public ComplaintAdapter(List<Complaint> complaintList) {
        this.complaintList = complaintList;
    }

    @NonNull
    @Override
    public ComplaintAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.complaint_item, parent, false);
        return new ComplaintAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintAdapter.ViewHolder holder, int position) {
        holder.mComplaintIDView.setText(complaintList.get(position).getComplaint_id());
        holder.mStatusView.setText(complaintList.get(position).getStatus());
        holder.mDescriptionView.setText(complaintList.get(position).getDescription());
        holder.mDateTimeView.setText(complaintList.get(position).getDate_time());
        holder.mCommentView.setText(complaintList.get(position).getComments());
        holder.mUrgent.setText(complaintList.get(position).getUrgent());
        holder.mImageView.setImageResource(R.drawable.user);
    }

    @Override
    public int getItemCount() {
        return complaintList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImageView;
        public final TextView mComplaintIDView;
        public final TextView mDescriptionView;
        public final TextView mStatusView;
        public final TextView mUrgent;
        public final TextView mDateTimeView;
        public final TextView mCommentView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = view.findViewById(R.id.complaint_pic);
            mComplaintIDView = view.findViewById(R.id.complaint_id_txt);
            mDescriptionView = view.findViewById(R.id.description_txt);
            mStatusView = view.findViewById(R.id.status_complaint_txt);
            mDateTimeView = view.findViewById(R.id.date_complaint_txt);
            mCommentView = view.findViewById(R.id.comments_txt);
            mUrgent = view.findViewById(R.id.urgent_txt);

        }

    }

}
