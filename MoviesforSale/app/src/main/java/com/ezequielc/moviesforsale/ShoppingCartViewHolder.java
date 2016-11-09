package com.ezequielc.moviesforsale;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by student on 11/8/16.
 */

public class ShoppingCartViewHolder extends RecyclerView.ViewHolder {
    public TextView mMovieShoppingList;
    public Button mRemoveButton, mCheckoutButton;

    public ShoppingCartViewHolder(View itemView) {
        super(itemView);

        mMovieShoppingList = (TextView) itemView.findViewById(R.id.shopping_list);
        mRemoveButton = (Button) itemView.findViewById(R.id.remove_button);
        mCheckoutButton = (Button) itemView.findViewById(R.id.checkout_button);
    }
}
