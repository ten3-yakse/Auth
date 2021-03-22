package com.example.auth.Fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.example.auth.Api.ApiClient;
import com.example.auth.Api.movies.Movies;
import com.example.auth.Adapter.MovieAdapter;
import com.example.auth.R;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewFragment extends Fragment {
    Context context;
    private ProgressBar mProgressBar;
    RecyclerView mRecyclerView;

    List<Movies> mMovies = new ArrayList<Movies>();

    public NewFragment() {
        super(R.layout.fragment_new);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceStatem) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new, container, false);
        mProgressBar = view.findViewById(R.id.progressBar2);
        mProgressBar.setVisibility(View.INVISIBLE);
        mMovies = new ArrayList<>();
        mRecyclerView = view.findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(layoutManager);
        MovieAdapter adapter = new MovieAdapter(mMovies);
        mRecyclerView.setAdapter(adapter);

        mProgressBar.setVisibility(View.VISIBLE);

        final Call<List<Movies>> call = ApiClient.getMovieNew().getDate();
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

        return view;

    }
}