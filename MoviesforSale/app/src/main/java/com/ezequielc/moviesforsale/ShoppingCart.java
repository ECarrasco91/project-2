package com.ezequielc.moviesforsale;

import java.util.ArrayList;

/**
 * Created by student on 11/8/16.
 */
public class ShoppingCart {
    private static ShoppingCart shoppingCart = null;
    private static ArrayList<Movies> movies;

    public static ShoppingCart getInstance() {
        if (shoppingCart == null){
            shoppingCart = new ShoppingCart();
        } return shoppingCart;
    }

    private ShoppingCart() {
        movies = new ArrayList<>();
    }

    public ArrayList<Movies> getMovies() {
        return movies;
    }

    public void addMovie(Movies movie) {
        movies.add(movie);
    }

    public void removeMovie(Movies movie){
        movies.remove(movie);
    }

    public void clearShoppingCart(){
        movies.clear();
    }

    public float getTotal(){
        float total = 0;
        for (int i = 0; i < movies.size(); i++) {
            float priceOfMovie = movies.get(i).getPrice();
            total += priceOfMovie;
        } return total;
    }
}
