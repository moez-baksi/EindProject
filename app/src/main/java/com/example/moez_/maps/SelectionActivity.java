package com.example.moez_.maps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_selection);

        Spinner spinner = findViewById(R.id.selection_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
    }

    // This function starts up thegame
    public void goResults (View view) {
        Intent intent = new Intent(SelectionActivity.this, ScoreActivity.class);
        Spinner spinner = findViewById(R.id.selection_spinner);
        int position =  spinner.getSelectedItemPosition() + 1;
        intent.putExtra("MODE", position);
        startActivity(intent);
    }
}
