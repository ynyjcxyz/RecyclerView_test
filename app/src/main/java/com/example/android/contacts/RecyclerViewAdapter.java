package com.example.android.contacts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private final Context context;
    private List<Info> contactInfoList;

    public RecyclerViewAdapter(Context context, List<Info> contactInfoList) {
        this.context = context;
        this.contactInfoList = contactInfoList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setInfoList(List<Info> contactInfoList) {
        this.contactInfoList = contactInfoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Glide.with(context)
                .asBitmap()
                .load(contactInfoList.get(position).image())
                .error(R.drawable.ic_baseline_error_24)
                .into(holder.round_img);

        holder.contact_name.setText(contactInfoList.get(position).name());

        holder.parent_layout.setOnClickListener(view -> {
            Intent intent = new Intent(context, ContactDetail.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  //Important
            intent.putExtra("name",contactInfoList.get(position).name());
            intent.putExtra("image",contactInfoList.get(position).image());
            intent.putExtra("phone",contactInfoList.get(position).phone());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if(contactInfoList != null){
            return contactInfoList.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout parent_layout;
        ImageView round_img;
        TextView contact_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent_layout = itemView.findViewById(R.id.parent_layout);
            round_img = itemView.findViewById(R.id.round_img);
            contact_name = itemView.findViewById(R.id.contact_name);
        }
    }

}
