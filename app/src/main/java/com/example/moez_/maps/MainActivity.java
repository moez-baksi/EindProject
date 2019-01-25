/* This is the main activity, which lets the user select either to play or view results */

package com.example.moez_.maps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    // On create function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // This function starts up the maps activity
    public void goStart (View view) {
        Intent intent = new Intent(MainActivity.this, ControlActivity.class);
        startActivity(intent);
    }

    // This function starts up the results activity
    public void goScores(View view) {
        Intent intent = new Intent(MainActivity.this, SelectionActivity.class);
        startActivity(intent);
    }

    // Implements the navigation button
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Je kan hier niet terug!",
                Toast.LENGTH_SHORT).show();
    }
}
