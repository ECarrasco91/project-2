package com.ezequielc.moviesforsale;

import java.util.ArrayList;

/**
 * Created by student on 11/8/16.
 */
public class ShoppingCart {
    //private static ShoppingCart ourInstance = new ShoppingCart();

    private static ShoppingCart shoppingCart = null;
    private static ArrayList<Movies> movies;

    public static ShoppingCart getInstance() {
        //return ourInstance;
        if (shoppingCart == null){
            shoppingCart = new ShoppingCart();
        } return shoppingCart;
    }

    private ShoppingCart() {
        movies = new ArrayList<>();
    }

    public static ArrayList<Movies> getMovies() {
        return movies;
    }

    public static void setMovies(ArrayList<Movies> movies) {
        ShoppingCart.movies = movies;
    }
}
