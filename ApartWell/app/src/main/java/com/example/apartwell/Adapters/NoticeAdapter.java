package com.example.apartwell.Adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apartwell.R;
import com.example.apartwell.models.Notice;

import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {
    protected List<Notice> noticeList;

    public NoticeAdapter(List<Notice> noticeList) {
        this.noticeList = noticeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return noticeList.size();
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
