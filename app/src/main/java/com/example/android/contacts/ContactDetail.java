package com.example.android.contacts;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import static android.Manifest.permission.CALL_PHONE;

public class ContactDetail extends AppCompatActivity {
    private ImageView detail_image;
    private TextView detail_name;
    private TextView detail_phone_number;
    private Button call_button;
    private Button text_button;
    private ImageView detail_phone_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_detail);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initView();
        setView();
        intentSet();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        detail_image = findViewById(R.id.detail_image);
        detail_name = findViewById(R.id.detail_name);
        detail_phone_number = findViewById(R.id.detail_phone_number);
        call_button = findViewById(R.id.call_button);
        text_button = findViewById(R.id.text_button);
        detail_phone_button = findViewById(R.id.detail_phone_button);
    }

    private void setView() {
        Glide.with(ContactDetail.this)
                .asBitmap()
                .load(getIntent().getStringExtra("image"))
                .error(R.drawable.ic_baseline_error_24)
                .into(detail_image);
        detail_name.setText(getIntent().getStringExtra("name"));
        detail_phone_number.setText(getIntent().getStringExtra("phone"));
    }

    private void intentSet() {
        call_button.setOnClickListener(view -> {
            Intent intent = new Intent
                    (Intent.ACTION_CALL,
                            Uri.parse("tel:" + getIntent().getStringExtra("phone")));
            if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                startActivity(intent);
            } else {
                requestPermissions(new String[]{CALL_PHONE}, 1);
            }
        });

        text_button.setOnClickListener(view -> {
            Intent intent = new Intent
                    (Intent.ACTION_SENDTO,
                            Uri.parse("smsto:" + getIntent().getStringExtra("phone")));
            startActivity(intent);
        });

        detail_phone_button.setOnClickListener(view -> {
            Intent intent_1 = new Intent
                    (Intent.ACTION_CALL,
                            Uri.parse("tel:" + getIntent().getStringExtra("phone")));
            if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                startActivity(intent_1);
            } else {
                requestPermissions(new String[]{CALL_PHONE}, 1);
            }
        });
    }

}