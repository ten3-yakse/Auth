package com.example.auth.Api;

import com.example.auth.Api.services.CoverService;
import com.example.auth.Api.services.ForMeService;
import com.example.auth.Api.services.InTrendService;
import com.example.auth.Api.services.LoginService;
import com.example.auth.Api.services.NewService;
import com.example.auth.Api.services.RegisterService;
import com.example.auth.Api.services.UserMovies;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static Retrofit getRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://cinema.areas.su/")
                .build();
        return retrofit;
    }
    public static RegisterService getService(){
        RegisterService registerService = getRetrofit().create(RegisterService.class);
        return  registerService;
    }
    public static LoginService getLogin(){
        LoginService loginService = getRetrofit().create(LoginService.class);

        return  loginService;
    }
    public static NewService getMovieNew(){
        NewService movieService = getRetrofit().create(NewService.class);

        return movieService;
    }
    public static InTrendService getMovieInTrend(){
        InTrendService inTrendService = getRetrofit().create(InTrendService.class);
        return  inTrendService;
    }
    public static ForMeService getMovieForMe(){
        ForMeService forMeService = getRetrofit().create(ForMeService.class);
        return  forMeService;
    }
    public static CoverService getMovieCover(){
        CoverService coverService = getRetrofit().create(CoverService.class);
        return  coverService;
    }
    public static UserMovies getUserMovies(){
        UserMovies userMovies = getRetrofit().create(UserMovies.class);
        return userMovies;
    }
}
