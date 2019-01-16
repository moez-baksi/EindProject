package com.example.moez_.maps;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ThreadLocalRandom;


public class PokeRequest implements  Response.Listener<JSONObject>, Response.ErrorListener{

    // Constructor
    private Context context;
    PokeRequest(Context con){
        context = con;
    }

    // Callback
    public interface Callback {
        void gotPokeError(String message);
        void gotPoke(Pokemon pokemon);
    }

    // For error
    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotPokeError(error.getMessage());
    }

    // For response
    @Override
    public void onResponse(JSONObject response) {
    Pokemon temp = null;
        try {
            String name = response.getString("name");
            JSONObject sprites = response.getJSONObject("sprites");
            String url = sprites.getString("front_default");
            temp = new Pokemon(name, url);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        callback.gotPoke(temp);
    }

    private Callback callback;
    void getPokemon(Callback activity){
        RequestQueue queue = Volley.newRequestQueue(context);
        String number = String.valueOf(ThreadLocalRandom.current().nextInt(1, 150));
        String url = "https://pokeapi.co/api/v2/pokemon/%s/";
        url = String.format(url, number);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                this, this);
        queue.add(jsonObjectRequest);
        callback = activity;
    }
}
