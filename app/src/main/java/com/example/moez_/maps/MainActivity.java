package com.example.moez_.maps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // On create function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // This function starts up the maps activity
    public void starting (View view) {
        Intent intent = new Intent(MainActivity.this, ControlActivity.class);
        startActivity(intent);
    }

    // This function starts up the scores screen
    public void scoring(View view) {
        Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
        startActivity(intent);
    }
}