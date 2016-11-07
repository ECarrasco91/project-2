package com.ezequielc.moviesforsale;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by student on 11/2/16.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder {
    public TextView mMovies;

    public MovieViewHolder(View itemView) {
        super(itemView);

        mMovies = (TextView) itemView.findViewById(R.id.movie_list);
    }
}
