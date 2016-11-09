package com.ezequielc.moviesforsale;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ShoppingCartActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ShoppingCartAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        mRecyclerView = (RecyclerView) findViewById(R.id.shopping_cart_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);



        mAdapter = new ShoppingCartAdapter(ShoppingCart.getInstance().getMovies());
        mRecyclerView.setAdapter(mAdapter);
    }
}
