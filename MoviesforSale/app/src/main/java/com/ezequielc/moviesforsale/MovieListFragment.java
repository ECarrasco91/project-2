package com.ezequielc.moviesforsale;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class MovieListFragment extends Fragment {
    private OnMovieSelectedListener mListener;
    private MovieAdapter mMovieAdapter;
    private List<Movies> mMoviesToDisplay;


    public MovieListFragment() {
        // Required empty public constructor
    }

    public  interface OnMovieSelectedListener{
        void onMovieSelected(int selectedMovie);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getContext().getSystemService(Context.SEARCH_SERVICE);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        ComponentName componentName = new ComponentName(getContext(), MainActivity.class);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));

        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Re-populates RecyclerView
                mMoviesToDisplay.clear();
                mMoviesToDisplay.addAll(MovieSQLHelper.getInstance(getContext())
                        .getMovieList());

                // Replaces Data and Notifies Data Set Changed
                MovieListFragment movieListFragment = (MovieListFragment) getActivity().getSupportFragmentManager()
                        .findFragmentByTag("list");
                movieListFragment.searchResults(mMoviesToDisplay);

                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                return true;
            case R.id.shopping_cart:
                Intent intent = new Intent(getContext(), ShoppingCartActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
}
