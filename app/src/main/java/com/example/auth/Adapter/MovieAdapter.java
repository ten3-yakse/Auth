package com.example.auth.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.auth.Api.movies.Movies;
import com.example.auth.MovieActivity;
import com.example.auth.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    private final static String PHOTO_URL = "http://cinema.areas.su/up/images/";
    private List<Movies> mMovie;
    private Context mContext;

    public MovieAdapter(List<Movies> movies) {
        this.mMovie = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Movies movies = mMovie.get(position);

        Picasso.with(mContext)
                .load(PHOTO_URL + movies.getPoster())
                .resize(500,700)
                .into(holder.flowerImageView);
        holder.textViewName.setText(movies.getName());
        holder.textViewDesc.setText(movies.getDescription());
        holder.textViewAge.setText(movies.getAge() + "+");
        holder.btn_watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MovieActivity.class).putExtra("movieName", movies.getName());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mMovie == null) {
            return 0;
        }
        return mMovie.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView flowerImageView;
        ImageView btn_watch;
        TextView textViewName;
        TextView textViewDesc;
        TextView textViewAge;

        ViewHolder(View itemView) {
            super(itemView);
            flowerImageView = (ImageView) itemView.findViewById(R.id.itemImageView);
            textViewName = itemView.findViewById(R.id.itemTextViewName);
            textViewDesc = itemView.findViewById(R.id.itemTextViewDesc);
            textViewAge = itemView.findViewById(R.id.itemTextViewAge);
            btn_watch = itemView.findViewById(R.id.btn_watch);
        }
    }
}
