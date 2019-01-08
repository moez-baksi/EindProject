package com.example.moez_.maps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void starting (View view) {
        // Let the start stop and return
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(intent);
    }

    public void scoring(View view) {
        // Let the start stop and return
        Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
        startActivity(intent);
    }
}
