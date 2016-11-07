package com.ezequielc.moviesforsale;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ezequielc.moviesforsale.setup.DBAssetHelper;

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
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, movieListFragment)
                .commit();
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
}
