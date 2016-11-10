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

    public ShoppingCartAdapter(ArrayList<Movies> movies){
        mListOfMovies = movies;
    }

    @Override
    public ShoppingCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShoppingCartViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shopping_cart_list_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ShoppingCartViewHolder holder, final int position) {

        final Movies positionOfMovie = mListOfMovies.get(position);

        holder.mMovieShoppingList.setText(positionOfMovie.getName());
        holder.mRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShoppingCart.getInstance().removeMovie(positionOfMovie);
                Toast.makeText(view.getContext(), positionOfMovie.getName()+" Removed",
                        Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListOfMovies.size();
    }
}
