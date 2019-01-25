package com.example.moez_.maps;

import com.google.android.gms.maps.model.LatLng;

public class ModeSpecificVar {
    int mode;
    int width;
    float zoomStart;
    float zoomVis;
    LatLng center;
    LatLng southWest;
    LatLng northEast;

    public ModeSpecificVar(int mode, int width, float zoomStart, float zoomVis, LatLng center, LatLng southWest, LatLng northEast) {
        this.mode = mode;
        this.width = width;
        this.zoomStart = zoomStart;
        this.zoomVis = zoomVis;
        this.center = center;
        this.southWest = southWest;
        this.northEast = northEast;
    }
}
