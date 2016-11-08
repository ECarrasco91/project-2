package com.ezequielc.moviesforsale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class MovieListFragment extends Fragment {
    private OnMovieSelectedListener mListener;
    private MovieAdapter mMovieAdapter;

    public MovieListFragment() {
        // Required empty public constructor
    }

    public  interface OnMovieSelectedListener{
        void onMovieSelected(int selectedMovie);
    }

    public static Fragment newInstance(Bundle bundle, OnMovieSelectedListener listener) {
        MovieListFragment fragment = new MovieListFragment();
        fragment.setArguments(bundle);
        fragment.mListener = listener;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.movie_list_recyclerview);

        MovieSQLHelper db = MovieSQLHelper.getInstance(getContext());
        List<Movies> moviesList = db.getMovieList();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        mMovieAdapter = new MovieAdapter(moviesList, mListener);
        recyclerView.setAdapter(mMovieAdapter);
    }

    public void searchResults(List<Movies> movies){
        mMovieAdapter.replaceData(movies);
    }
}
