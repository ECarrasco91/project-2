package com.ezequielc.moviesforsale;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ShoppingCartActivity extends AppCompatActivity
        implements ShoppingCartAdapter.OnMovieRemovedListener {
    private RecyclerView mRecyclerView;
    private ShoppingCartAdapter mAdapter;
    private TextView mTotal;
    private Button mCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        mRecyclerView = (RecyclerView) findViewById(R.id.shopping_cart_recyclerview);
        mTotal = (TextView) findViewById(R.id.display_total);
        mCheckout = (Button) findViewById(R.id.checkout_button);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mAdapter = new ShoppingCartAdapter(ShoppingCart.getInstance().getMovies(), this);
        mRecyclerView.setAdapter(mAdapter);

        displayTotal();

        mCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clears Movies from the Shopping Cart
                ShoppingCart.getInstance().clearShoppingCart();
                Toast.makeText(ShoppingCartActivity.this, "Purchase Completed!",
                        Toast.LENGTH_SHORT).show();

                // Goes to the MainActivity
                Intent intent = new Intent(ShoppingCartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    // Displays Total
    public void displayTotal(){
        float floatTotal = ShoppingCart.getInstance().getTotal();
        String stringTotal = Float.valueOf(floatTotal).toString();
        mTotal.setText("Total: $"+stringTotal);
    }

    @Override
    public void onMovieRemoved() {
        // Updates Total when a Movie is Removed
        displayTotal();
    }
}
