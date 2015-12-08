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


    public interface AsyncResponse {
        void processFinish(Response<Recent> output);
    }

    public AsyncResponse delegate = null;

    public FetchInstagramRecentData(AsyncResponse delegate){
        this.delegate = delegate;
    }

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
    protected void onPostExecute(Response<Recent> result) {
        delegate.processFinish(result);
    }
}
