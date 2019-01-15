package com.example.moez_.maps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class PokeRequest implements  Response.Listener<JSONObject>, Response.ErrorListener{

    // Constructor
    Context context;
    PokeRequest(Context con){
        context = con;
    }

    // Callback
    public interface Callback {
        void gotPokiError(String message);
        void gotPoki(ArrayList<Pokemon> pokilist);
    }

    // For error
    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotPokiError(error.getMessage());
    }

    // For response
    @Override
    public void onResponse(JSONObject response) { ;
    ArrayList<Pokemon> pokilist = new ArrayList<>();
        try {
            String name = response.getString("name");
            JSONObject sprites = response.getJSONObject("sprites");
            String url = (String) sprites.get("front_default");
            AsyncTask<String, Void, Bitmap> bmp = new Bit().execute(url);
            Pokemon pok = new Pokemon(name, bmp);
            pokilist.add(pok);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        callback.gotPoki(pokilist);
    }

    private Callback callback;
    void getPokemon(Callback activity){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://pokeapi.co/api/v2/pokemon/ditto/";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                this, this);
        queue.add(jsonObjectRequest);
        callback = activity;
    }
}
