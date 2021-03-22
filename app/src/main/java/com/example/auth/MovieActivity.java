package com.example.auth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.auth.Adapter.MovieAdapter;
import com.example.auth.Api.ApiClient;
import com.example.auth.Api.model.LoginResponses;
import com.example.auth.Api.movies.Movies;
import com.example.auth.Auth.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieActivity extends AppCompatActivity {
    TextView textView;
    SharedPreferences sPref;
    Context context;
    private ProgressBar mProgressBar;
    RecyclerView mRecyclerView;

    List<Movies> mMovies = new ArrayList<Movies>();

    final String SAVED_TEXT = "saved_text";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        textView = findViewById(R.id.tv_movieId);
        Intent intent = getIntent();
        String movieId = intent.getStringExtra("movieName");

        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        textView.setText(savedText);

        mProgressBar = findViewById(R.id.progressBar4);
        mProgressBar.setVisibility(View.INVISIBLE);

        mMovies = new ArrayList<>();

        mRecyclerView = findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(layoutManager);
        MovieAdapter adapter = new MovieAdapter(mMovies);
        mRecyclerView.setAdapter(adapter);

        mProgressBar.setVisibility(View.VISIBLE);
        int a = Integer.parseInt(savedText);
        final Call<List<Movies>> call = ApiClient.getUserMovies().getDate(a);
        call.enqueue(new Callback<List<Movies>>() {
            @Override
            public void onResponse(Call<List<Movies>> call, Response<List<Movies>> response) {
                if (response.isSuccessful()) {
                    mMovies.addAll(response.body());
                    mRecyclerView.getAdapter().notifyDataSetChanged();
                    mProgressBar.setVisibility(View.INVISIBLE);
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Movies>> call, Throwable t) {
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}
