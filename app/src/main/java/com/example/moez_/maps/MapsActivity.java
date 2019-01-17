package com.example.moez_.maps;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
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
import com.google.android.gms.maps.model.MapStyleOptions;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        PokeRequest.Callback {

    // Global variables
    private GoogleMap mMap;
    private Target target;
    int amountFound;
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
    }


    // Manipulates the map once available.
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Sets the map type
        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style));
        mMap.getUiSettings().setRotateGesturesEnabled(false);

        // Move the camera so it centers The Netherlands perfectly
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(52.363407,5.191517),
                (float) 7));

        // Set listeners
        mMap.setOnCameraMoveListener(new OnCameraMoveListener());
        mMap.setOnGroundOverlayClickListener(new OnGroundOverlayClickListener());

        // Reset the amount founds
        amountFound = 0;
        amountRequested = 0;

        // Create new lists
        pokemonArrayList = new ArrayList<>();
        cityArrayList = new ArrayList<>();

        // Obtain the cities, pokemon, set time and start function
        listCities();
    }

    // Hardcoded cities
    public void listCities(){
        cityArrayList.add(new City("Groningen", new LatLng(53.2193835,
                6.566501700000003), "Groningen"));
        cityArrayList.add(new City ("Leeuwarden", new LatLng(53.2012334,
                5.799913300000071), "Friesland"));
        cityArrayList.add(new City ("Assen", new LatLng(52.992753,
                6.564228400000047), "Drenthe"));
        cityArrayList.add(new City("Zwolle", new LatLng(52.5167747,
                6.083021899999949), "Overijssel"));
        cityArrayList.add(new City("Lelystad", new LatLng(52.51853699999999,
                5.471421999999961), "Flevoland"));
        cityArrayList.add(new City ("Arnhem", new LatLng(51.9851034,
                5.898729600000024), "Gelderland"));
        cityArrayList.add(new City("Utrecht", new LatLng(52.09073739999999,
                5.121420100000023), "Utrecht"));
        cityArrayList.add(new City("Haarlem", new LatLng(52.3873878,
                4.646219400000064), "Noord-Holland"));
        cityArrayList.add(new City("Den_Haag", new LatLng(52.0704978,
                4.3006999000000405), "Zuid-Holland"));
        cityArrayList.add(new City("Middelburg", new LatLng(51.49879620000001,
                3.610997999999995), "Zeeland"));
        cityArrayList.add(new City(" \t's-Hertogenbosch", new LatLng(51.69781620000001,
                5.303674799999953), "Noord-Brabant"));
        cityArrayList.add(new City("Maastricht", new LatLng(50.8513682,
                5.6909725000000435),  "Limburg"));
        cityArrayList.add(new City("Amsterdam", new LatLng(52.3679843,
                4.903561399999944),  "Nederland"));

        setPokemon();
    }

    // The function that makes the request
    public void setPokemon(){
        if (amountRequested < 13) {
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
            amountFound ++;
            Toast.makeText(this, answerPokemonName + " is succesvol gevangen!",
                    Toast.LENGTH_SHORT).show();
            overlay.remove();
        }

        // Get Random number
        int randomNum = ThreadLocalRandom.current().nextInt(0, 13 - amountFound);

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
                GroundOverlayOptions pokemonSprite = new GroundOverlayOptions().image(
                        BitmapDescriptorFactory.fromBitmap(bitmap)).position(answerCity.coordinates,
                        1000);
                overlay = mMap.addGroundOverlay(pokemonSprite);
                overlay.setClickable(true);

                TextView hint = findViewById(R.id.maps_clue);
                hint.setText("Er is een pokemon gezien in de hoofdstad van " + answerCity.hint
                        + "!");
                TextView remaining = findViewById(R.id.maps_counter);
                remaining.setText("Gevangen: " + amountFound + "/13");
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
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(52.363407,5.191517),
                (float) 7));
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
            overlay.setVisible((zoom > 12));
        }
    }

    // GroundOverlay listener which starts either a new activity or calls the overlay function
    private class OnGroundOverlayClickListener implements GoogleMap.OnGroundOverlayClickListener {
        @Override
        public void onGroundOverlayClick(GroundOverlay groundOverlay) {
            float zoom = mMap.getCameraPosition().zoom;
            if (zoom > 12){
                if (amountFound == 12){
                    Intent intent = new Intent(MapsActivity.this, ScoreActivity.class);
                    startActivity(intent);
                }
                else{
                    setOverlay();
                }
            }

        }
    }
}