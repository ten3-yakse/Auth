package com.example.auth.Api.services;

import com.example.auth.Api.movies.Movies;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface NewService {
    @GET("/movies?filter=new")
    Call<List<Movies>> getDate();
}
