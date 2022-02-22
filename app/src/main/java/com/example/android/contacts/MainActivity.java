package com.example.android.contacts;

import static com.example.android.contacts.GetUuid.getUuid;
import static com.example.android.contacts.InfoRepository.getDto;
import static com.uber.autodispose.AutoDispose.autoDisposable;
import static com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider.from;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private List<Info> contactInfoList = new ArrayList<>();
    RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRecyclerView();
        bindData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // below line is to get our inflater
        MenuInflater inflater = getMenuInflater();

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.search_menu, menu);

        // below line is to get our menu item.
        MenuItem searchItem = menu.findItem(R.id.actionSearch);

        // getting search view of our item.
        SearchView searchView = (SearchView) searchItem.getActionView();

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText);
                return false;
            }
        });
        return true;
    }

    @SuppressLint("NotifyDataSetChanged")
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void filter(String text) {
        System.out.println("contactInfoList: " + contactInfoList);
        List<Info> result = SearchUtil.getFilterList(text,contactInfoList);
        if(result.isEmpty()){
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            recyclerAdapter.setInfoList(result);
        }
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

    @SuppressLint("NotifyDataSetChanged")
    private void onSuccess(List<Info> contactInfo) {
        contactInfoList = contactInfo;
        recyclerAdapter.setInfoList(contactInfo);
        recyclerAdapter.notifyDataSetChanged();
    }
    private void onError(Throwable throwable) {
        Log.d("TAG", "Failed! Response = " + throwable);
    }



}