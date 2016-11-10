package com.ezequielc.moviesforsale;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by student on 11/8/16.
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartViewHolder> {
    private ArrayList<Movies> mListOfMovies;
    private OnMovieRemovedListener mListener;

    public ShoppingCartAdapter(ArrayList<Movies> movies, OnMovieRemovedListener listener){
        mListOfMovies = movies;
        mListener = listener;
    }

    @Override
    public ShoppingCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShoppingCartViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shopping_cart_list_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ShoppingCartViewHolder holder, final int position) {

        // Finalizes the position of each Movie
        final Movies positionOfMovie = mListOfMovies.get(position);

        holder.mMovieShoppingList.setText(positionOfMovie.getName());
        holder.mRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Removes Movies from the Shopping Cart
                ShoppingCart.getInstance().removeMovie(positionOfMovie);

                // Specifies which Movie has been removed to the Shopping Cart
                Toast.makeText(view.getContext(), positionOfMovie.getName()+" Removed",
                        Toast.LENGTH_SHORT).show();

                // Updates the list of Movies in the Shopping Cart
                notifyDataSetChanged();

                // Updates Total when a Movie is Removed
                mListener.onMovieRemoved();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListOfMovies.size();
    }

    // Interface to listen if a Movie has been Removed
    public interface OnMovieRemovedListener {
        void onMovieRemoved();
    }
}
