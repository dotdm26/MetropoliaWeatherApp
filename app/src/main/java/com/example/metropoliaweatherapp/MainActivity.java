package com.example.metropoliaweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import static com.example.metropoliaweatherapp.PreferencesActivity.LOCATION;
import static com.example.metropoliaweatherapp.PreferencesActivity.SHARED_PREFS;

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
    private TextView mTextViewResult;
    private TextView temps;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        temps = findViewById(R.id.temperature);
        mTextViewResult = findViewById(R.id.text_view_result);
        Button buttonParse = findViewById(R.id.button_parse);

        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
                jsonParse2();
            }
        });

        setContentView(R.layout.activity_main);

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

        calendarButton = findViewById(R.id.calendarButton);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar();
            }
        });

        Spinner prefSpinner = findViewById(R.id.savedprefsSpinner);
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

    public void clearWeather() {
        mTextViewResult = findViewById(R.id.text_view_result);
        temps = findViewById(R.id.temperature);

        String clear = "";

        mTextViewResult.setText(clear);
        temps.setText(clear);
    }

    public void prefAdd() {
        Intent pref = new Intent(this, PreferencesActivity.class);
        startActivity(pref);
    }

    public void calendar() {
        Intent cal = new Intent(this, calendarActivity.class);
        startActivity(cal);
    }

    public void lvPrefs() {
        Intent list = new Intent(this, listSavedPrefsActivity.class);
        startActivity(list);
    }

    private void jsonParse() {
        clearWeather();
        String location = GlobalModel.getInstance().getPreferences().get(1).getLocation();
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

                                mTextViewResult.append(main + ", " + description);
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
        String location = GlobalModel.getInstance().getPreferences().get(1).getLocation();
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
}
