package com.example.android.contacts;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import static android.Manifest.permission.CALL_PHONE;

public class ContactDetail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String phone = getIntent().getStringExtra("phone");

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
        detail_phone_number.setText(phone);

        Button call_button = findViewById(R.id.call_button);
        call_button.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
            if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED){
                startActivity(intent);
            }else{
                requestPermissions(new String[]{CALL_PHONE}, 1);
            }
        });

        Button text_button = findViewById(R.id.text_button);
        text_button.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phone));
            startActivity(intent);
        });

        ImageView detail_phone_button = findViewById(R.id.detail_phone_button);
        detail_phone_button.setOnClickListener(view -> {
            Intent intent_1 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
            if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED){
                startActivity(intent_1);
            }else{
                requestPermissions(new String[]{CALL_PHONE}, 1);
            }
        });


    }
}
