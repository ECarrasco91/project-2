package com.ezequielc.moviesforsale;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by student on 11/2/16.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    List<Movies> mMoviesList;

    public MovieAdapter(List<Movies> moviesList) {
        mMoviesList = moviesList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.mMovies.setText(mMoviesList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mMoviesList.size();
    }
}
