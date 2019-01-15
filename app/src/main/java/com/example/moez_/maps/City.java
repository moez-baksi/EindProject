package com.example.moez_.maps;

import com.google.android.gms.maps.model.LatLng;

public class City {
    String name;
    LatLng coordinates;
    String hint;

    public City(String name, LatLng coordinates, String hint) {
        this.name = name;
        this.coordinates = coordinates;
        this.hint = hint;
    }
}
