 /* This class is involved making cities, with a name, the coordinates and a hint (country / place
 the city is located) */

package com.example.moez_.maps;

import com.google.android.gms.maps.model.LatLng;

class City {
    String name;
    LatLng coordinates;
    String hint;

    // Constructor
    City(String name, LatLng coordinates, String hint) {
        this.name = name;
        this.coordinates = coordinates;
        this.hint = hint;
    }
}
