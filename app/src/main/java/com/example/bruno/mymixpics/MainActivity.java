package com.example.bruno.mymixpics;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.bruno.mymixpics.model.Media;
import com.example.bruno.mymixpics.model.Recent;

import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class MainActivity extends ActionBarActivity {

    @Bind(R.id.cardList)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    final private String userId = "43915154";
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        try {
            recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(), getData());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    refreshItems();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    void refreshItems() throws ExecutionException, InterruptedException {

        List<Media> data = getData();
        onItemsLoadComplete(data);
    }

    void onItemsLoadComplete(List<Media> data) {
        // Update the adapter and notify data set changed
        // ...
        recyclerViewAdapter.setData(data);
        recyclerViewAdapter.notifyDataSetChanged();
        // Stop refresh animation
        swipeRefreshLayout.setRefreshing(false);
    }

    public List<Media> getData() throws ExecutionException, InterruptedException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.instagram.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InstagramService instagramService = retrofit.create(InstagramService.class);
        Call<Recent> recentCall = instagramService.getRecent(userId, BuildConfig.ACCESS_TOKEN, 5, null, null, null, null);
        FetchInstagramRecentData fetchInstagramRecentData = new FetchInstagramRecentData();
        fetchInstagramRecentData.execute(recentCall);
        return fetchInstagramRecentData.get().body().getMediaList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
