package com.example.android.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String MOVIE_LIST_URL =
            "https://run.mocky.io/v3/be5b5607-1aad-4e02-935f-d6df54abc102";
    private List<Info> contactInfoList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
    }

    private void loadData() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerAdapter = new RecyclerViewAdapter(getApplicationContext(), contactInfoList);
        recyclerView.setAdapter(recyclerAdapter);

        String reg = "[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}";
        Matcher m = Pattern.compile(reg).matcher(MOVIE_LIST_URL);
        m.find();
        String uuid = m.group();
        System.out.println(uuid);

        DataService dataService = RetrofitClient.getClient().create(DataService.class);

        Call <List<Info>> call = dataService.getAllContacts(uuid);
        call.enqueue(new Callback<List<Info>>() {
            @Override
            public void onResponse(Call<List<Info>> call, Response<List<Info>> response) {
                contactInfoList = response.body();
                Log.d("TAG","Succeed! Response = " + contactInfoList);
                recyclerAdapter.setInfoList(contactInfoList);
            }

            @Override
            public void onFailure(Call<List<Info>> call, Throwable t) {
                Log.d("TAG","Failed! Response = " + t.toString());
            }
        });

/*        Call<List<Info>> call = RetrofitClient.getInstance(getApplicationContext()).getDataService().getAllContacts(uuid);
        call.enqueue(new Callback<List<Info>>() {
            @Override
            public void onResponse(Call<List<Info>> call, Response<List<Info>> response) {
                List<Info> responseList = response.body();
                assert responseList != null;
                contactInfoList.addAll(responseList);

                RecyclerViewAdapter contactAdapter = new RecyclerViewAdapter(MainActivity.this, contactInfoList);
                recyclerView.setAdapter(contactAdapter);

                layoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<List<Info>> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });*/
    }
}