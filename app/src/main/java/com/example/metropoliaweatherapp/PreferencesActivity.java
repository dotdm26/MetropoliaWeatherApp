package com.example.metropoliaweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PreferencesActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "SHARED PREFERENCES";
    public static final String PREF_NAME = "PREFERENCE NAME";
    public static final String WEATHER = "WEATHER TYPE";
    public static String MIN_TEMP = "MINIMUM TEMPERATURE";
    public static String MAX_TEMP = "MAXIMUM TEMPERATURE";
    public static final String LOCATION = "LOCATION";
    /**
     * An activity where the user can create new weather preferences for a specific activity (to be named by the user)
     * Add/Remove activities
     * Name, weather description, temperature, humidity, location? and etc.
     *
     * TO DO LIST:
     * - Store user preferences
     *
     * - Make sure the stored preferences also work in other activities.
     */

    TextView newPref;
    TextView weatherType;
    TextView prefLocation;
    TextView minTemp;
    TextView maxTemp;
    Button returnButton;
    Button addPrefButton;
    Button prefDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        newPref = (EditText) findViewById(R.id.newPref);
        weatherType = (EditText) findViewById(R.id.weatherType);
        prefLocation = (EditText) findViewById(R.id.prefLocation);
        minTemp = (EditText) findViewById(R.id.maxTemp);
        maxTemp = (EditText) findViewById(R.id.minTemp);


        //Button to return to main activity
        returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnMain();
            }
        });

        addPrefButton = findViewById(R.id.prefAddButton);
        addPrefButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefAdd();
            }
        });

        prefDisplay = findViewById(R.id.prefDisplayButton);
        prefDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lvPrefs();
            }
        });
    }

    public void lvPrefs() {
        Intent list = new Intent(this, listSavedPrefsActivity.class);
        startActivity(list);
    }

    public void returnMain() {
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);

    }

    public void prefAdd() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_NAME, newPref.getText().toString());
        editor.putString(WEATHER, weatherType.getText().toString());
        editor.putString(MIN_TEMP, String.valueOf(Double.parseDouble(minTemp.getText().toString())));
        editor.putString(MAX_TEMP, String.valueOf(Double.parseDouble(maxTemp.getText().toString())));
        editor.putString(LOCATION, prefLocation.getText().toString());
        editor.commit();
    }
}
