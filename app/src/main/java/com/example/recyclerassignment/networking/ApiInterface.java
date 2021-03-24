package com.example.recyclerassignment.networking;

import com.example.recyclerassignment.models.UsersResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("api/?results=20")
    Call<UsersResponse> getSingleUser();

}