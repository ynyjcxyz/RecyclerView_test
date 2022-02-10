package com.example.android.contacts;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
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