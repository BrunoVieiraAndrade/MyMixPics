package com.example.bruno.mymixpics;

import android.os.AsyncTask;

import com.example.bruno.mymixpics.model.Profile;

import java.io.IOException;

import retrofit.Call;
import retrofit.Response;

/**
 * Created by Bruno on 12/4/2015.
 */
public class FetchInstagramUserProfile extends AsyncTask<Call<Profile>, Void, Response<Profile>> {


    public interface AsyncResponse {
        void processFinish(Response<Profile> output);
    }

    public AsyncResponse delegate = null;

    public FetchInstagramUserProfile(AsyncResponse delegate){
        this.delegate = delegate;
    }

    @Override
    protected Response<Profile> doInBackground(Call<Profile>... params) {

        Response<Profile> userResponse = null;
        try {
            userResponse = params[0].execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userResponse;
    }

    @Override
    protected void onPostExecute(Response<Profile> result) {
        delegate.processFinish(result);
    }
}
