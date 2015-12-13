package com.example.bruno.mymixpics;

import com.example.bruno.mymixpics.model.Profile;
import com.example.bruno.mymixpics.model.Recent;
import com.example.bruno.mymixpics.model.User;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Bruno on 12/2/2015.
 */
public interface InstagramService {
    @GET("users/{user_id}/media/recent/")
    Call<Recent> getRecent(
            @Path("user_id") String userId,
            @Query("access_token") String accessToken,
            @Query("count") Integer count,
            @Query("min_id") String minId,
            @Query("max_id") String maxId,
            @Query("min_timestamp") Long minTimestamp,
            @Query("max_timestamp") Long maxTimestamp);

    @GET("users/{user-id}/")
    Call<Profile> getProfile (
            @Path("user-id") String userId,
            @Query("access_token") String accessToken);

    @GET("users/search/")
    Call<User> getUserByUserName(
            @Query("q") String userUsername,
            @Query("access_token") String accessToken);

}
