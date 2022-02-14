package com.example.android.contacts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final Context context;
    private List<Info> contactInfoList;

    public RecyclerViewAdapter(Context context, List<Info> contactInfoList) {
        this.context = context;
        this.contactInfoList = contactInfoList;
    }


    @SuppressLint("NotifyDataSetChanged")
    public void setInfoList(List<Info> listAdd) {
        contactInfoList.clear();
//        contactInfoList.addAll(listAdd);
//        notifyDataSetChanged();
        this.contactInfoList = listAdd;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .asBitmap()
                .load(contactInfoList.get(position).image())
                .error(R.drawable.ic_baseline_error_24)
                .into(holder.round_img);

        holder.contact_name.setText(contactInfoList.get(position).name());

        holder.parent_layout.setOnClickListener(view -> {
            Intent intent = new Intent(context, ContactDetail.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  //Important
            intent.putExtra("name", contactInfoList.get(position).name());
            intent.putExtra("image", contactInfoList.get(position).image());
            intent.putExtra("phone", contactInfoList.get(position).phone());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (contactInfoList != null) {
            return contactInfoList.size();
        }
        return 0;
    }
}