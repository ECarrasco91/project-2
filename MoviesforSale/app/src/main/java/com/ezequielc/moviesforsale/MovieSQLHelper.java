package com.ezequielc.moviesforsale;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 11/2/16.
 */

public class MovieSQLHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "movies.db";
    public static final String TABLE_NAME = "movies_list";

    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_DIRECTOR = "director";
    public static final String COL_GENRE = "genre";
    public static final String COL_RUN_TIME = "run_time";
    public static final String COL_YEAR_RELEASE = "year_release";
    public static final String COL_PRICE = "price";

    public static final String[] MOVIE_COLUMNS = {COL_ID, COL_NAME, COL_DIRECTOR, COL_GENRE,
            COL_RUN_TIME, COL_YEAR_RELEASE, COL_PRICE};

    public static final String CREATE_MOVIE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_NAME + " TEXT, " +
                    COL_DIRECTOR + " TEXT, " +
                    COL_GENRE + " TEXT, " +
                    COL_RUN_TIME + " INT, " +
                    COL_YEAR_RELEASE + " INT," +
                    COL_PRICE + " INT)";

    private static MovieSQLHelper mInstance;

    public static MovieSQLHelper getInstance(Context context){
        if (mInstance == null) {
            mInstance = new MovieSQLHelper((context.getApplicationContext()));
        } return mInstance;
    }

    private MovieSQLHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    // Query Database
    public List<Movies> getMovieList(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                null, null, null, null, null, null);

        List<Movies> movieList = new ArrayList<>();

        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                movieList.add(new Movies(cursor.getInt(cursor.getColumnIndex(COL_ID)),
                        cursor.getString(cursor.getColumnIndex(COL_NAME)),
                        cursor.getString(cursor.getColumnIndex(COL_DIRECTOR)),
                        cursor.getString(cursor.getColumnIndex(COL_GENRE)),
                        cursor.getInt(cursor.getColumnIndex(COL_RUN_TIME)),
                        cursor.getInt(cursor.getColumnIndex(COL_YEAR_RELEASE)),
                        cursor.getInt(cursor.getColumnIndex(COL_PRICE))));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return movieList;
    }

    // Get Movies by Id
    public Movies getMoviesById(int id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, // a. table
                MOVIE_COLUMNS, // b. column names
                COL_ID + " = ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor.moveToFirst()){
            String name  = cursor.getString(cursor.getColumnIndex(COL_NAME));
            String director = cursor.getString(cursor.getColumnIndex(COL_DIRECTOR));
            String genre = cursor.getString(cursor.getColumnIndex(COL_GENRE));
            int runTime = cursor.getInt(cursor.getColumnIndex(COL_RUN_TIME));
            int yearRelease = cursor.getInt(cursor.getColumnIndex(COL_YEAR_RELEASE));
            float price = cursor.getFloat(cursor.getColumnIndex(COL_PRICE));

            cursor.close();
            return new Movies(id, name, director, genre, runTime, yearRelease, price);
        } else {
            cursor.close();
            return null;
        }
    }
}
