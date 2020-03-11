package com.example.metropoliaweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    /**
     * Big message displaying "nice day" or "not a nice day".
     * Followed by a few details about the weather.
     * A spinner to select a specific activity (that comes with the preferred weather, ...)
     * Location button to locate the user and get the weather data?
     * Calendar button to review past dates' information
     */

    private Button calendarButton;
    private Button userButton;
    private Button listButton;
    private TextView result;
    private TextView temps;
    private RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        temps = findViewById(R.id.temperature);
        result = findViewById(R.id.weatherRep);
        Button update = findViewById(R.id.updateButton);

        mQueue = Volley.newRequestQueue(this);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
                jsonParse2();
                Log.d("whatever","whatever_i_want");
            }
        });


        userButton = findViewById(R.id.userButton);
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefAdd();
            }
        });

        listButton = findViewById(R.id.listButton);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lvPrefs();
            }
        });

    }

    private void jsonParse() {
        clearWeather();
        int i = getIntent().getIntExtra("EXTRA", 0);
        String location = GlobalModel.getInstance().getPreferences().get(i).getLocation();
        String cName = location;
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cName + "&units=metric&appid=b9572d546f224251f9983505002bbe7c";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("weather");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject weather = jsonArray.getJSONObject(i);

                                String main = weather.getString("main");
                                String description = weather.getString("description");

                                result.append(main + ", " + description);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    private void jsonParse2() {
        clearWeather();
        int i = getIntent().getIntExtra("EXTRA", 0);
        String location = GlobalModel.getInstance().getPreferences().get(i).getLocation();
        String cName = location;
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cName + "&units=metric&appid=b9572d546f224251f9983505002bbe7c";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonObject = response.getJSONObject("main");
                            for (int i = 0; i < jsonObject.length(); i++) {

                                int temp = jsonObject.getInt("temp");

                                temps.setText(String.valueOf(temp));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    public void clearWeather() {
        result = findViewById(R.id.weatherRep);
        temps = findViewById(R.id.temperature);

        String clear = "";

        result.setText(clear);
        temps.setText(clear);
    }

    public void prefAdd() {
        Intent pref = new Intent(this, PreferencesActivity.class);
        startActivity(pref);
    }


    public void lvPrefs() {
        Intent list = new Intent(this, listSavedPrefsActivity.class);
        startActivity(list);
    }


}
