package com.example.android.contacts;

import static com.example.android.contacts.GetUuid.getUuid;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private List<Info> contactInfoList = new ArrayList<>();
    private RecyclerViewAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetRecycleView();
    }

    private void SetRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RetrofitClient
                .getClient()
                .create(DataService.class)
                .getAllContacts(getUuid())
                .enqueue(new Callback<List<Info>>() {
                    @Override
                    public void onResponse
                            (@NonNull Call<List<Info>> call,
                             @NonNull Response<List<Info>> response) {
                        contactInfoList = response.body();
                        Log.d("TAG", "Succeed! Response = " + contactInfoList);
                        recyclerAdapter.setInfoList(contactInfoList);
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Info>> call, @NonNull Throwable t) {
                        Log.d("TAG", "Failed! Response = " + t);
                    }
                });
        recyclerAdapter = new RecyclerViewAdapter(getApplicationContext(), contactInfoList);
        recyclerView.setAdapter(recyclerAdapter);
    }

}