package com.example.moez_.maps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ControlActivity extends AppCompatActivity {

    // On create function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
    }

    // This function starts up the Ne game
    public void goStartNE (View view) {
        Intent intent = new Intent(ControlActivity.this, MapsActivity.class);
        intent.putExtra("MODE", "NE");
        startActivity(intent);
    }

    // This function starts up the NL game
    public void goStartNL (View view) {
        Intent intent = new Intent(ControlActivity.this, MapsActivity.class);
        intent.putExtra("MODE", "NL");
        startActivity(intent);
    }
}
