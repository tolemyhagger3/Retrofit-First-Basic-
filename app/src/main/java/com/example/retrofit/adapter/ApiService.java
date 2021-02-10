package com.example.retrofit.adapter;

import com.example.retrofit.model.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("v2/list")
    Call<List<Model>> getImageList();

}
