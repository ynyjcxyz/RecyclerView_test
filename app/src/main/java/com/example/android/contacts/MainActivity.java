package com.example.android.contacts;

import static com.example.android.contacts.GetUuid.getUuid;
import static com.example.android.contacts.InfoRepository.getDto;
import static com.uber.autodispose.AutoDispose.autoDisposable;
import static com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider.from;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private final List<Info> contactInfoList = new ArrayList<>();
    RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRecyclerView();
        bindData();
    }

    private void setRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerViewAdapter(getApplicationContext(),contactInfoList);
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void bindData() {
        getDto(getUuid())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(from(this)))
                .subscribe(this::onSuccess,this::onError);

        /**沒有使用RxJava時採用此方法*/
/**        GetService
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
                });*/
    }

    private void onSuccess(List<Info> contactInfo) {
        recyclerAdapter.setInfoList(contactInfo);
    }

    private void onError(Throwable throwable) {
        Log.d("TAG", "Failed! Response = " + throwable);
    }

}