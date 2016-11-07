package com.ezequielc.moviesforsale;

/**
 * Created by student on 11/2/16.
 */

public class Movies {
    private String mName, mDirector, mGenre;
    private int mId, mRunTime, mYearReleased;
    private float mPrice;

    public Movies(int id, String name, String director, String genre,
                  int runTime, int yearReleased, float price) {
        mId = id;
        mName = name;
        mDirector = director;
        mGenre = genre;
        mRunTime = runTime;
        mYearReleased = yearReleased;
        mPrice = price;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDirector() {
        return mDirector;
    }

    public void setDirector(String director) {
        mDirector = director;
    }

    public String getGenre() {
        return mGenre;
    }

    public void setGenre(String genre) {
        mGenre = genre;
    }

    public int getLength() {
        return mRunTime;
    }

    public void setLength(int runTime) {
        mRunTime = runTime;
    }

    public int getYearReleased() {
        return mYearReleased;
    }

    public void setYearRealeased(int yearReleased) {
        mYearReleased = yearReleased;
    }

    public float getPrice() {
        return mPrice;
    }

    public void setPrice(float price) {
        mPrice = price;
    }
}
