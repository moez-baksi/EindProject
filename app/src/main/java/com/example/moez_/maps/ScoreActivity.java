package com.example.moez_.maps;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class ScoreActivity extends AppCompatActivity {

    // On create function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        ScoreDatabase scoreDatabase = ScoreDatabase.getInstance(getApplicationContext());
        scoreDatabase.insert("dit is een tweede");
        ArrayList<ScoreClass> data = scoreDatabase.selectAll();
        TableLayout tableLayout = findViewById(R.id.score_table);
        tableLayout.setStretchAllColumns(true);

        for (int i = 0; i < data.size(); i++){
            String entryDate = data.get(i).date;
            String entryScore = data.get(i).score;
            TableRow tableRow = new TableRow(this);

            TextView viewDate = new TextView(this);
            viewDate.setText(entryDate);
            viewDate.setGravity(Gravity.CENTER);
            tableRow.addView(viewDate, 0);

            TextView viewScore = new TextView(this);
            viewScore.setText(entryScore);
            viewDate.setGravity(Gravity.CENTER);
            tableRow.addView(viewScore, 1);

            tableLayout.addView(tableRow);
        }
    }

    // This function let the user return to the home screen
    public void gpBack (View view) {
        Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
        startActivity(intent);
    }

    // Implements the navigation button
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Gebruik de knop 'Terug' om terug te gaan!",
                Toast.LENGTH_SHORT).show();
    }
}
