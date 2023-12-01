package com.example.app;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

     Context context;
     ArrayList<NotificationList> list;

    public NotificationAdapter(Context context, ArrayList<NotificationList> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.notified_content,parent,false);

        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {

        NotificationList list1 =list.get(position);

        holder.recTitle.setText(list1.getNotifiedTitle());
        holder.recContents.setText(list1.getNotifiedContents());
        holder.recDate.setText(list1.getNotifiedDate());




        holder.recCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Ok Success", Toast.LENGTH_SHORT).show();
            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder{

        TextView recTitle,recContents,recDate;
        LinearLayout LayoutTitle;
        CardView recCardView;


        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);

            recTitle=itemView.findViewById(R.id.TextForTitle1);
            recContents=itemView.findViewById(R.id.ContentsOnNotification);
            recDate=itemView.findViewById(R.id.DateOnNotification);
            LayoutTitle=itemView.findViewById(R.id.Notification_Layout_color1);
            recCardView=itemView.findViewById(R.id.CardViewForNotification1);
        }
    }

}