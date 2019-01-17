package com.example.moez_.maps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ScoreActivity extends AppCompatActivity {

    // On create function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
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
