package com.ezequielc.moviesforsale;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by student on 11/2/16.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    private List<Movies> mMoviesList;
    private MovieListFragment.OnMovieSelectedListener mListener;

    public MovieAdapter(List<Movies> moviesList, MovieListFragment.OnMovieSelectedListener listener) {
        mMoviesList = moviesList;
        mListener = listener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.mMovies.setText(mMoviesList.get(position).getName());

        holder.mMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onMovieSelected(mMoviesList.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMoviesList.size();
    }

    // Changes List of Movies Displayed upon Search
    public void replaceData(List<Movies> searchedList){
        mMoviesList = searchedList;
        notifyDataSetChanged();
    }
}
