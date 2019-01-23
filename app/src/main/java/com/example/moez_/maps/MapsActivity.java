package com.example.moez_.maps;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        PokeRequest.Callback {

    // Global variables
    String mode;
    private GoogleMap mMap;
    private Target target;
    int amountRequested;
    String answerPokemonName;
    ArrayList<City> cityArrayList;
    ArrayList<Pokemon> pokemonArrayList;
    GroundOverlay overlay;


    // On create function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        Intent intent = getIntent();
        mode = intent.getStringExtra("MODE");
    }


    // Manipulates the map once available.
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Sets the map type
        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style));
        mMap.getUiSettings().setRotateGesturesEnabled(false);

        // Move the camera so it centers perfectly
        LatLng center = (mode.equals("NE") ? new LatLng(64,5.2) :
                new LatLng(52.363407,5.191517));
        float zoom = mode.equals("NE") ? (float) 3.2 : (float) 6.9;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, zoom));

        // Set listeners
        mMap.setOnCameraMoveListener(new OnCameraMoveListener());
        mMap.setOnGroundOverlayClickListener(new OnGroundOverlayClickListener());

        LatLng southWest = (mode.equals("NE") ? new LatLng(47, - 25) :
                new LatLng(50.8, 3.27));
        LatLng northEast = (mode.equals("NE") ? new LatLng(70, 33) :
                new LatLng(53.34, 7.21));
        mMap.setLatLngBoundsForCameraTarget(new LatLngBounds(southWest, northEast));

        // Reset the amount founds
        amountRequested = 0;
        pokemonArrayList = new ArrayList<>();

        // Create new lists
        ListCities listCity = new ListCities();
        cityArrayList = (mode.equals("NE") ? listCity.getNorthEurope() :
                listCity.getNetherlands());

        // Obtain the cities, pokemon, set time and start function
        setPokemon();
    }

    // The function that makes the request
    public void setPokemon(){
        if (amountRequested < cityArrayList.size()) {
            PokeRequest poke = new PokeRequest(this);
            poke.getPokemon(this);
        }
        else{
            setTime();
        }
    }

    // Function to set and start time
    public void setTime(){
        Chronometer timer = findViewById(R.id.maps_time);
        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();
        findViewById(R.id.maps_progressBar).setVisibility(View.GONE);
        setOverlay();
    }

    // The function that handles placing/removing overlays
    public void setOverlay(){
        // If there is a overlay, remove it and display message
        if (overlay != null) {
            Toast toast = Toast.makeText(this, answerPokemonName
                            + " is succesvol gevangen!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            overlay.remove();
        }

        // Get Random number
        int randomNum = ThreadLocalRandom.current().nextInt(0, cityArrayList.size());

        // Get city
        final City answerCity = cityArrayList.get(randomNum);
        cityArrayList.remove(randomNum);

        // Get pokemon
        Pokemon answerPokemon = pokemonArrayList.get(randomNum);
        answerPokemonName = answerPokemon.name.toUpperCase();
        pokemonArrayList.remove(randomNum);

        // Set grounder overlay and update hint within picasso function
        target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                int width = (mode.equals("NE") ? 50000 : 2000);
                GroundOverlayOptions pokemonSprite = new GroundOverlayOptions().image(
                        BitmapDescriptorFactory.fromBitmap(bitmap)).position(answerCity.coordinates,
                        width);
                overlay = mMap.addGroundOverlay(pokemonSprite);
                overlay.setClickable(true);
                overlay.setVisible(false);

                TextView hint = findViewById(R.id.maps_clue);
                int randomNum = ThreadLocalRandom.current().nextInt(0, 2);
                if (randomNum == 0){
                    hint.setText("Er is een pokemon gezien in de stad " + answerCity.name + "!");
                }
                else{
                    hint.setText("Er is een pokemon gezien in de hoofdstad van " + answerCity.hint
                            + "!");
                }
                TextView remaining = findViewById(R.id.maps_counter);
                remaining.setText("Nog " + (cityArrayList.size() + 1) + " Pokemon!");
            }
            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
            }
            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        };
        // Set Image
        Picasso.with(this).load(answerPokemon.url).into(target);
    }

    // If there is a error with the pokemon
    @Override
    public void gotPokeError(String message) {
        Toast.makeText(this, "Er is iets misgegaan, probeer het opnieuw!",
                Toast.LENGTH_SHORT).show();
    }

    // Set the free pokemon
    @Override
    public void gotPoke(Pokemon pokemon) {
        amountRequested ++;
        pokemonArrayList.add(pokemon);
        setPokemon();
    }

    // This function centers the screen
    public void setCenter (View view) {
        LatLng center = (mode.equals("NE") ? new LatLng(64,5.2) :
                new LatLng(52.363407,5.191517));
        float zoom = mode.equals("NE") ? (float) 3.2 : (float) 6.9;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, zoom));
    }

    // This function let the user return to the home screen
    public void goReturn (View view) {
        Intent intent = new Intent(MapsActivity.this, MainActivity.class);
        startActivity(intent);
    }

    // Implements the navigation button
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Maak het spel af, of druk op 'Stoppen'!",
                Toast.LENGTH_SHORT).show();
    }

    // When the camera moves, check whether it is zoomed enough, and set visibility based on it
    private class OnCameraMoveListener implements GoogleMap.OnCameraMoveListener{
        @Override
        public void onCameraMove() {
            float zoom = mMap.getCameraPosition().zoom;
            int zoomLevel = (mode.equals("NE") ? 7 : 12);
            overlay.setVisible((zoom > zoomLevel));
        }
    }

    // GroundOverlay listener which starts either a new activity or calls the overlay function
    private class OnGroundOverlayClickListener implements GoogleMap.OnGroundOverlayClickListener {
        @Override
        public void onGroundOverlayClick(GroundOverlay groundOverlay) {
            if (overlay.isVisible()){
                if (cityArrayList.size() == 0){
                    overlay.remove();
                    Intent intent = new Intent(MapsActivity.this, ScoreActivity.class);
                    Chronometer timer = findViewById(R.id.maps_time);
                    long score = SystemClock.elapsedRealtime() - timer.getBase();
                    long seconds = TimeUnit.MILLISECONDS.toSeconds(score);
                    String userScore = String.format("%02d seconden", seconds);
                    intent.putExtra("score", userScore);
                    startActivity(intent);
                }
                else{
                    setOverlay();
                }
            }

        }
    }
}