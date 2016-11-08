package com.ezequielc.moviesforsale;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ezequielc.moviesforsale.setup.DBAssetHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements MovieListFragment.OnMovieSelectedListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Database Setup
        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();

        Fragment movieListFragment = MovieListFragment.newInstance(null, MainActivity.this);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, movieListFragment, "list")
                .commit();

        handleIntent(getIntent());
    }

    @Override
    public void onMovieSelected(int id) {
        Bundle bundle =  new Bundle();
        bundle.putInt("selected_movie", id);
        Fragment movieDetailFragment = MovieDetailFragment.newInstance(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container, movieDetailFragment)
                .commit();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent){
        if (Intent.ACTION_SEARCH.equals(intent.getAction())){

            // Performs Search
            String query = intent.getStringExtra(SearchManager.QUERY);
            List<Movies> movies = MovieSQLHelper.getInstance(this)
                    .searchThroughMovies(query);

            // Replaces Data and Notifies Data Set Changed
            MovieListFragment movieListFragment = (MovieListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.fragment_container);
            movieListFragment.searchResults(movies);
        }
    }
}
