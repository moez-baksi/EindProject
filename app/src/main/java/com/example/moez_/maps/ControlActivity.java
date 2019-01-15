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

    // This function starts up the maps activity
    public void starting (View view) {
        Intent intent = new Intent(ControlActivity.this, MapsActivity.class);
        startActivity(intent);
    }

    // This function let the user return to the home screen
    public void returning (View view) {
        Intent intent = new Intent(ControlActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
