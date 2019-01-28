/* This activity is used to display the scores based on the mode*/

package com.example.moez_.maps;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {

    // On create function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Intent intent = getIntent();
        int userScore = intent.getIntExtra("score", 0);
        int mode = intent.getIntExtra("MODE", 0);
        ScoreDatabase scoreDatabase = ScoreDatabase.getInstance(getApplicationContext());

        TextView textView = findViewById(R.id.score_title);
        String[] gameModes = getResources().getStringArray(R.array.game_modes);
        String text = gameModes[mode - 1];
        if (userScore > 1){
                scoreDatabase.insert(userScore, mode);
                textView.setText(String.format(text + ": \n" + "Je hebt een score van %s seconden!",
                        userScore));
        }
        else{
            textView.setText(String.format("Dit zijn de resultaten van de \n %s!", text));
        }

        ArrayList<Score> data = scoreDatabase.selectAll(mode);

        TableLayout tableLayout = findViewById(R.id.score_table);
        tableLayout.setStretchAllColumns(true);

        TableRow tableRow = new TableRow(this);

        TextView viewScore = setTableProperties("Tijd: ");
        viewScore.setTypeface(null, Typeface.BOLD);
        tableRow.addView(viewScore, 0);

        TextView viewDate = setTableProperties("Datum: ");
        viewDate.setTypeface(null, Typeface.BOLD);
        tableRow.addView(viewDate, 1);

        tableLayout.addView(tableRow);

        for (int i = 0; i < data.size(); i++){
            String entryDate = data.get(i).date;
            String entryScore = data.get(i).score;

            tableRow = new TableRow(this);

            viewScore = setTableProperties(entryScore);
            tableRow.addView(viewScore, 0);

            viewDate = setTableProperties(entryDate);
            tableRow.addView(viewDate, 1);

            tableLayout.addView(tableRow);
        }
    }

    // Function that sets the table properties
    public TextView setTableProperties(String name){
        TextView textview = new TextView(this);

        textview.setText(name);
        textview.setTextColor(Color.BLACK);
        textview.setGravity(Gravity.CENTER);
        textview.setBackgroundColor(Color.WHITE);

        return textview;
    }

    // Implements the navigation button
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
