package com.ezequielc.moviesforsale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;


public class MovieDetailFragment extends Fragment {

    public static MovieDetailFragment newInstance(Bundle bundle) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int selectedMovieId = getArguments().getInt("selected_movie", -1);

        Movies selectedMovie = MovieSQLHelper.getInstance(getContext())
                .getMoviesById(selectedMovieId);

        TextView name = (TextView) view.findViewById(R.id.movie_name);
        TextView director = (TextView) view.findViewById(R.id.movie_director);
        TextView genre = (TextView) view.findViewById(R.id.movie_genre);
        TextView runTime = (TextView) view.findViewById(R.id.movie_run_time);
        TextView yearRelease = (TextView) view.findViewById(R.id.movie_release);
        TextView price = (TextView) view.findViewById(R.id.movie_price);

        name.setText(String.format(Locale.getDefault(), "Name: %s", selectedMovie.getName()));
        director.setText(String.format(Locale.getDefault(), "Director: %s", selectedMovie.getDirector()));
        genre.setText(String.format(Locale.getDefault(), "Genre: %s", selectedMovie.getGenre()));
        runTime.setText(String.format(Locale.getDefault(), "Run Time: %d minutes", selectedMovie.getLength()));
        yearRelease.setText(String.format(Locale.getDefault(), "Year Released: %d", selectedMovie.getYearReleased()));

        // Set Prices in Currency Format (Price: %)
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
        double priceValue = Double.valueOf(selectedMovie.getPrice());
        price.setText(currencyFormat.format(priceValue));
    }
}
