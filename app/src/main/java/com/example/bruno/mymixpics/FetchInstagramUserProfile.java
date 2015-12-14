package com.example.bruno.mymixpics;

import android.os.AsyncTask;

import com.example.bruno.mymixpics.model.User;

import java.io.IOException;

import retrofit.Call;
import retrofit.Response;

/**
 * Created by Bruno on 12/4/2015.
 */
public class FetchInstagramUserProfile extends AsyncTask<Call<User>, Void, Response<User>> {

    @Override
    protected Response<User> doInBackground(Call<User>... params) {

        Response<User> userResponse = null;
        try {
            userResponse = params[0].execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userResponse;
    }

    @Override
    protected void onPostExecute(Response<User> result) {

    }
}
