package com.example.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MovieActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        textView = findViewById(R.id.tv_movieId);
        Intent intent = getIntent();

        String movieId = intent.getStringExtra("movieName");
        textView.setText(movieId);
    }
}
