package com.example.metropoliaweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    /**
     * Big message displaying "nice day" or "not a nice day".
     * Followed by a few details about the weather.
     * A spinner to select a specific activity (that comes with the preferred weather, ...)
     * Location button to locate the user and get the weather data?
     * Calendar button to review past dates' information
     */

    private Button userButton, listButton, update;
    private TextView result, temps, dayVerdict;
    private RequestQueue mQueue;
    private Spinner prefSpinner;
    private String mainw, description;
    private int temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        temps = findViewById(R.id.temperature);
        result = findViewById(R.id.weatherRep);

        mQueue = Volley.newRequestQueue(this);

        update = findViewById(R.id.updateButton);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
                jsonParse2();
                goodDay();
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

        prefSpinner = findViewById(R.id.savedprefsSpinner);
        ArrayAdapter adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                (ArrayList) GlobalModel.getInstance().getPreferences());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prefSpinner.setAdapter(adapter);
        prefSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void jsonParse() {
        clearWeather();
        int i = prefSpinner.getSelectedItemPosition();
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

                                mainw = weather.getString("main");
                                description = weather.getString("description");

                                result.append(mainw + " \n" + description);
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
        int i = prefSpinner.getSelectedItemPosition();
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

                                temp = jsonObject.getInt("temp");

                                temps.setText(String.valueOf(temp) + "°C");
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

    private void goodDay() {
        int num = prefSpinner.getSelectedItemPosition();
        dayVerdict = findViewById(R.id.dayVerdict);
        String weatherType = GlobalModel.getInstance().getPreferences().get(num).getWeatherType();
        int minTemp = GlobalModel.getInstance().getPreferences().get(num).getMinTemp();
        int maxTemp = GlobalModel.getInstance().getPreferences().get(num).getMaxTemp();
        if (weatherType.equals(mainw) && temp >= minTemp && temp <= maxTemp){
            dayVerdict.setText("It is a nice day!");
        }
        else {
            dayVerdict.setText("It is a bad day :(");
        }
    }

    private void clearWeather() {
        result = findViewById(R.id.weatherRep);
        temps = findViewById(R.id.temperature);

        String clear = "";

        result.setText(clear);
        temps.setText(clear);
    }

    private void prefAdd() {
        Intent pref = new Intent(this, PreferencesActivity.class);
        startActivity(pref);
    }


    private void lvPrefs() {
        Intent list = new Intent(this, listSavedPrefsActivity.class);
        startActivity(list);
    }


}
