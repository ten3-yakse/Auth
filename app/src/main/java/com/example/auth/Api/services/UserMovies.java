package com.example.auth.Api.services;

import com.example.auth.Api.movies.Movies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface UserMovies {

    @GET("/usermovies?filter=compilation")
    Call<List<Movies>> getDate(@Path("token") int token);
}
