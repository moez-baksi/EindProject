package com.example.moez_.maps;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class ListCities {
    public ListCities() {
    }

    public ArrayList<City> getNetherlands(){
        ArrayList<City> cityArrayList = new ArrayList<>();
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
        return cityArrayList;
    }

    public ArrayList<City> getNorthEurope(){
        ArrayList<City> cityArrayList = new ArrayList<>();
        cityArrayList.add(new City("Reykjavik", new LatLng(64.15,
                -21.95),  "Ijsland"));
        cityArrayList.add(new City("Dublin", new LatLng(53.31,
                -6.233),  "Ierland"));
        cityArrayList.add(new City("Londen", new LatLng(	51.5,
                -0.083333),  "Verenigd Koninkrijk"));
        cityArrayList.add(new City("Kopenhagen", new LatLng(	55.66666667,
                12.583333),  "Denemarken"));
        cityArrayList.add(new City("Oslo", new LatLng(	59.91666667,
                10.75),  "Noorwegen"));
        cityArrayList.add(new City("Stockholm", new LatLng(		59.33333333,
                18.05),  "Zweden"));
        cityArrayList.add(new City("Helsinki", new LatLng(		60.16666667,
                24.933333),  "Finland"));
        cityArrayList.add(new City("Riga", new LatLng(				56.95,
                24.1),  "Letland"));
        cityArrayList.add(new City("Talinn", new LatLng(		59.43333333,
                24.716667),  "Estland"));
        cityArrayList.add(new City("Vilnius", new LatLng(		54.68333333,
                25.28),  "Litouwen"));
        return cityArrayList;
    }

    public ArrayList<City> getWestEurope(){
        ArrayList<City> cityArrayList = new ArrayList<>();
        cityArrayList.add(new City("Parijs", new LatLng(48.86,
                2.35),  "Frankrijk"));
        cityArrayList.add(new City("Madrid", new LatLng(40.42,
                -3.7),  "Spanje"));
        cityArrayList.add(new City("Lissabon", new LatLng(38.71,
                -9.14),  "Portugal"));
        cityArrayList.add(new City("Bern", new LatLng(46.95,
                7.45),  "Zwitserland"));
        cityArrayList.add(new City("Wenen", new LatLng(48.21,
                16.37),  "Oostenrijk"));
        cityArrayList.add(new City("Rome", new LatLng(41.9,
                12.5),  "Italië"));
        cityArrayList.add(new City("Brussel", new LatLng(50.85,
                4.35),  "België"));
        cityArrayList.add(new City("Berlijn", new LatLng(52.52,
                13.4),  "Duitsland"));
        cityArrayList.add(new City("Amsterdam", new LatLng(52.3679843,
                4.903561399999944),  "Nederland"));
        cityArrayList.add(new City("Luxemburg", new LatLng(49.61,
                6.13),  "Luxemburg"));
        return cityArrayList;
    }
}
