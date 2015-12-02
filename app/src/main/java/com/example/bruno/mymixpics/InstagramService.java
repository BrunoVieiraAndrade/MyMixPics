package com.example.bruno.mymixpics;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Bruno on 12/2/2015.
 */
public interface InstagramService {
    @GET("users/{user-id}/media/recent/?access_token=491862971.e29b7a3.56886123b030410bb65a56f86c5d1731")
    User getUser (@Path("user-id") String userId);
}
