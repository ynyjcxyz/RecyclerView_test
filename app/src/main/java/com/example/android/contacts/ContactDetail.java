package com.example.android.contacts;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ContactDetail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_detail);

        ImageView detail_image = (ImageView) findViewById(R.id.detail_image);
        TextView detail_name = (TextView) findViewById(R.id.detail_name);
        TextView detail_phone_number = (TextView) findViewById(R.id.detail_phone_number);

        Glide.with(ContactDetail.this)
                .asBitmap()
                .load(getIntent().getStringExtra("image"))
                .error(R.drawable.ic_baseline_error_24)
                .into(detail_image);

        detail_name.setText(getIntent().getStringExtra("name"));
        detail_phone_number.setText(getIntent().getStringExtra("phone"));
    }
}
