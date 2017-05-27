package com.example.shubhambhama.ibnox;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SHUB-PC on 22-03-2017.
 */

public class NotificationDataAdapter extends RecyclerView.Adapter<NotificationDataAdapter.NotificationViewHolder> {

    private List<NotificationData> data=new ArrayList<>();

    public NotificationDataAdapter(List<NotificationData> data){
        this.data=data;
    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_notification_row,parent,false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
        holder.nImageView.setImageResource(data.get(position).getImageView());
        holder.nTextView.setText(data.get(position).getTextView());
        holder.nTimeTextView.setText(data.get(position).getTimeTextView());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class NotificationViewHolder extends RecyclerView.ViewHolder {
        private ImageView nImageView;
        private TextView nTextView;
        private TextView nTimeTextView;

        public NotificationViewHolder(View itemView) {
            super(itemView);
            nImageView=(ImageView) itemView.findViewById(R.id.notification_image);
            nTextView=(TextView) itemView.findViewById(R.id.notification_title);
            nTimeTextView=(TextView) itemView.findViewById(R.id.notification_date);
        }
    }
}
