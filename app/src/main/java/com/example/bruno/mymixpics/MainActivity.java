package com.example.bruno.mymixpics;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bruno.mymixpics.model.Profile;
import com.example.bruno.mymixpics.model.Recent;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends ActionBarActivity {

    final private String userId = "368085031";
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
        if (id == R.id.menu_load) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.instagram.com/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            InstagramService instagramService = retrofit.create(InstagramService.class);
            retrofit.Call<Profile> profileCall = instagramService.getProfile(userId, BuildConfig.ACCESS_TOKEN);
            final Call<Recent> recentCall = instagramService.getRecent(userId, BuildConfig.ACCESS_TOKEN, 5, null, null, null, null);
            FetchInstagramRecentData fetchInstagramRecentData = new FetchInstagramRecentData(new FetchInstagramRecentData.AsyncResponse() {
                @Override
                public void processFinish(Response<Recent> output) {
                    Recent recent = output.body();
                    Picasso.with(getApplicationContext())
                            .load(recent.getMediaList().get(1).getImages().getStandardResolution().getUrl())
                            .into(imageView);
                }
            });
            fetchInstagramRecentData.execute(recentCall);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
