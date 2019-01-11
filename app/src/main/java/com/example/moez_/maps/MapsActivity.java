package com.example.moez_.maps;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;

import java.util.concurrent.ThreadLocalRandom;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    GroundOverlay overlay;
    City[] list;

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
        boolean success = googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this,
                R.raw.style));
        mMap.getUiSettings().setRotateGesturesEnabled(false);

        // Move the camera so it centers The Netherlands perfectly
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(52.363407,5.191517), (float) 7.5));;

        // Set listener and go to overlay function
        mMap.setOnCameraMoveListener(new OnCameraMoveListener());
        mMap.setOnGroundOverlayClickListener(new OnGroundOverlayClickListener());
        overlay();
    }

    public void overlay(){
        // Obtain the cities and choose one
        City[] list = listCities();
        int randomNum = ThreadLocalRandom.current().nextInt(0, 14);
        City current = list[randomNum];


        // Set grounder overlay
        GroundOverlayOptions pokiball = new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.mipmap.pokeball)).position(current.coordinates, 1000);
        overlay = mMap.addGroundOverlay(pokiball);
        overlay.setClickable(true);

        // Update hint
        TextView textview = findViewById(R.id.maps_clue);
        textview.setText("Er is een pokemon gezien in de hoofdstad van " + current.hint + "!");
    }

    // Hardcoded cities
    public City[] listCities(){
        list = new City[14];
        list[0] = new City("Groningen", new LatLng(53.2193835, 6.566501700000003), false, "Groningen");
        list[1] = new City ("Leeuwarden", new LatLng(53.2012334, 5.799913300000071), false, "Friesland");
        list[2] = new City ("Assen", new LatLng(52.992753, 6.564228400000047), false, "Drenthe");
        list[3] = new City("Zwolle", new LatLng(52.5167747, 6.083021899999949), false, "Overijssel");
        list[4] = new City("Lelystad", new LatLng(52.51853699999999, 5.471421999999961), false, "Flevoland");
        list[5] = new City ("Arnhem", new LatLng(51.9851034, 5.898729600000024), false, "Gelderland");
        list[6] = new City("Utrecht", new LatLng(52.09073739999999, 5.121420100000023), false, "Utrecht");
        list[7] = new City("Haarlem", new LatLng(52.3873878, 4.646219400000064), false, "Noord-Holland");
        list[8] = new City("Den_Haag", new LatLng(52.0704978, 4.3006999000000405), false, "Zuid-Holland");
        list[9] = new City("Middelburg", new LatLng(51.49879620000001, 3.610997999999995), false, "Zeeland");
        list[10] = new City(" \t's-Hertogenbosch", new LatLng(51.69781620000001, 5.303674799999953), false, "Noord-Brabant");
        list[11] = new City("Maastricht", new LatLng(50.8513682, 5.6909725000000435), false, "Limburg");
        list[12] = new City("Amsterdam", new LatLng(52.3679843, 4.903561399999944), false, "Nederland");
        list[13] = new City("Almere", new LatLng(52.363407,5.191517), false, "Hometown");
        return list;
    }


    // This function let the user return to the home screen
    public void returning (View view) {
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
            overlay.setVisible((zoom > 11));
        };
    }

    private class OnGroundOverlayClickListener implements GoogleMap.OnGroundOverlayClickListener{
        @Override
        public void onGroundOverlayClick(GroundOverlay groundOverlay) {
            overlay.remove();
        }
    }
}
