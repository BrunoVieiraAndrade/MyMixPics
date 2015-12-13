package com.example.bruno.mymixpics;

import android.os.AsyncTask;

import com.example.bruno.mymixpics.model.Recent;

import java.io.IOException;

import retrofit.Call;
import retrofit.Response;

/**
 * Created by Bruno on 12/4/2015.
 */
public class FetchInstagramRecentData extends AsyncTask<Call<Recent>, Void, Response<Recent>> {


    @Override
    protected Response<Recent> doInBackground(Call<Recent>... params) {

        Response<Recent> userResponse = null;
        try {
            userResponse = params[0].execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userResponse;
    }

    @Override
    protected void onPostExecute(Response<Recent> recentResponse) {
        super.onPostExecute(recentResponse);
    }


}
