package com.ezequielc.moviesforsale;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ShoppingCartActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ShoppingCartAdapter mAdapter;
    private Button mCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        mRecyclerView = (RecyclerView) findViewById(R.id.shopping_cart_recyclerview);
        mCheckout = (Button) findViewById(R.id.checkout_button);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mAdapter = new ShoppingCartAdapter(ShoppingCart.getInstance().getMovies());
        mRecyclerView.setAdapter(mAdapter);

        mCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShoppingCart.getInstance().clearShoppingCart();
                Toast.makeText(ShoppingCartActivity.this, "Purchase Completed!",
                        Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();
            }
        });

    }
}
