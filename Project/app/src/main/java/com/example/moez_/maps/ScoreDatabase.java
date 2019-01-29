/* This database class handles everything sql related. From selecting to inserting are included */

package com.example.moez_.maps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ScoreDatabase extends SQLiteOpenHelper {

    // SO it is a singleton
    private static ScoreDatabase instance;

    private ScoreDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    // On Create create the table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String exec_statement = "CREATE TABLE entries (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "score INTEGER, mode INTEGER, timestap TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";
        db.execSQL(exec_statement);
    }

    // On upgrade drops it is exist
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "entries");
        onCreate(db);
    }

    // Checks if there is a database
    static ScoreDatabase getInstance(Context context){
        if (instance!= null) {
            return instance;
        }
        else{
            instance = (new ScoreDatabase(context, "entries", null, 1));
            return instance;
        }
    }

    // Select all items in the database
    ArrayList<Score> selectAll(int mode){
        SQLiteDatabase db = getWritableDatabase();
        @SuppressLint("DefaultLocale") String sql = String.format("SELECT * FROM entries " +
                "WHERE mode=%d ORDER BY score;", mode);
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList <Score> scores = new ArrayList<>();
        while (cursor.moveToNext()){
            String date = cursor.getString(cursor.getColumnIndexOrThrow("timestap"));
            int score = cursor.getInt(cursor.getColumnIndexOrThrow("score"));
            @SuppressLint("DefaultLocale") String userScore = String.format("%02d seconden", score);
            Score entry = new Score(date, userScore);
            scores.add(entry);
        }
        cursor.close();
        return scores;
    }

    // The function that lets you insert values based on the mode and score
    void insert(Integer score, Integer mode){
        SQLiteDatabase db = getWritableDatabase();
        @SuppressLint("DefaultLocale") String exec_statement = String.format("INSERT INTO " +
                "entries (score, mode) VALUES ('%d', '%d');", score, mode);
        db.execSQL(exec_statement);
    }
}
