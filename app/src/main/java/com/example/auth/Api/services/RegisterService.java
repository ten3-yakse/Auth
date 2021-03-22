package com.example.auth.Api.services;

import com.example.auth.Api.model.RegisterRequest;
import com.example.auth.Api.model.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterService {
    @POST("auth/register")
    Call<RegisterResponse> saveUser(@Body RegisterRequest registerRequest);
}
