package com.example.auth;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.auth.Api.ApiClient;
import com.example.auth.Api.movies.MoviesCover;
import com.example.auth.Fragments.ForMeFragment;
import com.example.auth.Fragments.InTrendFragment;
import com.example.auth.Fragments.NewFragment;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final static String PHOTO_URL = "http://cinema.areas.su/up/images/";
    Button btn_forMe,btn_inTrend, btn_new;
    ImageView img_cover;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_forMe = findViewById(R.id.btn_forme);
        btn_inTrend = findViewById(R.id.btn_intrend);
        btn_new = findViewById(R.id.btn_new);
        img_cover = findViewById(R.id.img_cover);

        final Call<MoviesCover> call = ApiClient.getMovieCover().getDate();
        call.enqueue(new Callback<MoviesCover>() {
            @Override
            public void onResponse(Call<MoviesCover> call, Response<MoviesCover> response) {
                if (response.isSuccessful()){
                    MoviesCover moviesCover = response.body();
                    Picasso.with(MainActivity.this)
                            .load(PHOTO_URL + moviesCover.getBackgroundImage())
                            .into(img_cover);
                }else{
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<MoviesCover> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage() ,Toast.LENGTH_LONG).show();
            }
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_view, ForMeFragment.class, null)
                    .commit();
        }

    }

    public void onClick(View view){
        Drawable myIcon = this.getResources().getDrawable(R.drawable.drawable_bottom);
        switch (view.getId()){
            case R.id.btn_forme:
                btn_forMe.setCompoundDrawablesWithIntrinsicBounds(null, null, null, myIcon);
                btn_inTrend.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                btn_new.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_view, ForMeFragment.class, null)
                        .commit();
                break;
            case R.id.btn_intrend:
                btn_forMe.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                btn_inTrend.setCompoundDrawablesWithIntrinsicBounds(null, null, null, myIcon);
                btn_new.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_view, InTrendFragment.class, null)
                        .commit();
                break;
            case  R.id.btn_new:
                btn_forMe.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                btn_inTrend.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                btn_new.setCompoundDrawablesWithIntrinsicBounds(null, null, null, myIcon);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_view, NewFragment.class, null)
                        .commit();
                break;
        }
    }

}