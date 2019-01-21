package com.example.moez_.maps;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class ScoreDatabase extends SQLiteOpenHelper {

    // SO it is a singleton
    static ScoreDatabase instance;

    private ScoreDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String exec_statement = "CREATE TABLE entries (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "score TEXT, timestap TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";
        db.execSQL(exec_statement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "entries");
        onCreate(db);
    }

    // Checks if there is a database
    public static ScoreDatabase getInstance(Context context){
        if (instance!= null) {
            return instance;
        }
        else{
            instance = (new ScoreDatabase(context, "entries", null, 1));
            return instance;
        }
    }

    // Select all items in the database
    public ArrayList<Score> selectAll(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM entries ORDER BY score;", null);
        ArrayList <Score> scores = new ArrayList<>();
        while (cursor.moveToNext()){
            String date = cursor.getString(cursor.getColumnIndexOrThrow("timestap"));
            String score = cursor.getString(cursor.getColumnIndexOrThrow("score"));
            Score entry = new Score(date, score);
            scores.add(entry);
        }
        cursor.close();
        return scores;
    }

    public void insert(String scory){
        SQLiteDatabase db = getWritableDatabase();
        String exec_statement = String.format("INSERT INTO entries (score) VALUES ('%s');", scory);
        db.execSQL(exec_statement);
    }
}
