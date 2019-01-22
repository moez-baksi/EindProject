package com.example.moez_.maps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {

    // On create function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Intent intent = getIntent();
        String userScore = intent.getStringExtra("score");
        ScoreDatabase scoreDatabase = ScoreDatabase.getInstance(getApplicationContext());

        if (userScore != null){
                scoreDatabase.insert(userScore);
                TextView textView = findViewById(R.id.score_title);
                textView.setText(String.format("Je hebt een score van %s!", userScore));
        }

        ArrayList<Score> data = scoreDatabase.selectAll();

        TableLayout tableLayout = findViewById(R.id.score_table);
        tableLayout.setStretchAllColumns(true);

        TableRow tableRow = new TableRow(this);
        TextView viewScore = new TextView(this);
        viewScore.setText("Tijd: ");
        viewScore.setGravity(Gravity.CENTER);
        tableRow.addView(viewScore, 0);

        TextView viewDate = new TextView(this);
        viewDate.setText("Datum: ");
        viewDate.setGravity(Gravity.CENTER);
        tableRow.addView(viewDate, 1);

        tableLayout.addView(tableRow);

        for (int i = 0; i < data.size(); i++){
            String entryDate = data.get(i).date;
            String entryScore = data.get(i).score;
            tableRow = new TableRow(this);

            viewScore = new TextView(this);
            viewScore.setText(entryScore);
            viewScore.setGravity(Gravity.CENTER);
            tableRow.addView(viewScore, 0);

            viewDate = new TextView(this);
            viewDate.setText(entryDate);
            viewDate.setGravity(Gravity.CENTER);
            tableRow.addView(viewDate, 1);

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
