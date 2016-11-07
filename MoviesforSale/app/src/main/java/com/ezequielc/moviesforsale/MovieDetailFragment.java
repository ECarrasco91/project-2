package com.ezequielc.moviesforsale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

//        if (selectedMovieId == -1){
//            getActivity().getSupportFragment
//        }

        Movies selectedMovie = MovieSQLHelper.getInstance(getContext())
                .getMoviesById(selectedMovieId);

        TextView name = (TextView) view.findViewById(R.id.movie_name);
        TextView director = (TextView) view.findViewById(R.id.movie_director);
        TextView genre = (TextView) view.findViewById(R.id.movie_genre);
        TextView runTime = (TextView) view.findViewById(R.id.movie_run_time);
        TextView yearRelease = (TextView) view.findViewById(R.id.movie_release);
        TextView price = (TextView) view.findViewById(R.id.movie_price);

        // name.setText(selectedMovie.getName());
        name.setText(String.format(Locale.getDefault(), "Name: %s", selectedMovie.getName()));
        director.setText(selectedMovie.getDirector());
        genre.setText(selectedMovie.getGenre());
        runTime.setText(selectedMovie.getLength());
        yearRelease.setText(selectedMovie.getYearReleased());
        //price.setText(selectedMovie.getPrice());
    }
}
