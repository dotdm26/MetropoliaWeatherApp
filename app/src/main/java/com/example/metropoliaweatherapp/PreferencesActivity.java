package com.example.metropoliaweatherapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class PreferencesActivity extends AppCompatActivity {

    public static final String PREF_NAME = "PREFERENCE NAME";
    public static final String WEATHER = "WEATHER TYPE";
    public static final String MIN_TEMP = "MINIMUM TEMPERATURE";
    public static final String MAX_TEMP = "MAXIMUM TEMPERATURE";
    public static final String LOCATION = "LOCATION";
    /**
     * An activity where the user can create new weather preferences for a specific activity (to be named by the user)
     * Add/Remove activities
     * Name, weather description, temperature, location and etc.
     */

    TextView newPref, prefLocation, minTemp, maxTemp;
    Button homeButton, addPrefButton, prefDisplay;
    Spinner weatherTypes;
    String selectedWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        newPref = (EditText) findViewById(R.id.newPref);
        prefLocation = (EditText) findViewById(R.id.prefLocation);
        minTemp = (EditText) findViewById(R.id.minTemp);
        maxTemp = (EditText) findViewById(R.id.maxTemp);

        //Spinner where the user selects their preferred weather type (All condition names are taken directly from https://openweathermap.org/weather-conditions
        ArrayList<String> weatherList = new ArrayList<>();
        weatherList.add("Clear");
        weatherList.add("Clouds");
        weatherList.add("Drizzle");
        weatherList.add("Rain");
        weatherList.add("Thunderstorm");
        weatherList.add("Snow");
        weatherList.add("Tornado");
        weatherList.add("Mist");
        weatherList.add("Haze");
        weatherList.add("Smoke");
        weatherList.add("Dust");
        weatherList.add("Fog");
        weatherList.add("Sand");
        weatherList.add("Ash");
        weatherList.add("Squall");
        weatherTypes = findViewById(R.id.weathertypesSpinner);
        ArrayAdapter adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                weatherList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weatherTypes.setAdapter(adapter);
        weatherTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedWeather = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //Button to return to main activity
        homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnHome();
            }
        });

        //Button to add pref
        addPrefButton = findViewById(R.id.prefAddButton);
        addPrefButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Verifies whether all of the fields are filled or not
                //Also whether the min and max temperature are coherent or not (min <= max)
                if (newPref.getText().toString().isEmpty() || selectedWeather.isEmpty() || prefLocation.getText().toString().isEmpty() || minTemp.getText().toString().isEmpty() || maxTemp.getText().toString().isEmpty()) {
                    toastNo();
                } else if (Integer.parseInt(minTemp.getText().toString()) > Integer.parseInt(maxTemp.getText().toString())) {
                    toastTempDif();
                } else {
                    prefAdd();
                    toastYes();
                }
            }
        });

        //Allows the user to view the list of saved preferences
        prefDisplay = findViewById(R.id.prefDisplayButton);
        prefDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lvPrefs();
            }
        });
    }

    //Opens new activity (List of preferences)
    private void lvPrefs() {
        Intent list = new Intent(this, listSavedPrefsActivity.class);
        startActivity(list);
    }

    //Opens new activity (Main Activity)
    private void returnHome() {
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
    }

    //Adds preference
    private void prefAdd() {
        SharedPreferences sharedPreferences = getSharedPreferences(newPref.getText().toString(), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_NAME, newPref.getText().toString());
        editor.putString(WEATHER, selectedWeather);
        editor.putString(MIN_TEMP, String.valueOf(parseInt(minTemp.getText().toString())));
        editor.putString(MAX_TEMP, String.valueOf(parseInt(maxTemp.getText().toString())));
        editor.putString(LOCATION, prefLocation.getText().toString());
        editor.commit();

        super.onPause();

        String name = sharedPreferences.getString(PREF_NAME, "");
        String minTempStr = sharedPreferences.getString(MIN_TEMP, "");
        String maxTempStr = sharedPreferences.getString(MAX_TEMP, "");
        String location = sharedPreferences.getString(LOCATION, "");
        String weatherType = sharedPreferences.getString(WEATHER, "");
        GlobalModel preference = GlobalModel.getInstance();
        int minTemp = parseInt(minTempStr);
        int maxTemp = parseInt(maxTempStr);
        preference.prefAdd(new Preference(name, minTemp, maxTemp, location, weatherType));
    }

    //Toast confirming the addition of preference
    private void toastYes() {
        Toast.makeText(this, "User preferences have been saved", Toast.LENGTH_SHORT).show();
    }

    //Toast informing the user to fill out the preference's form if user clicks "Add" without filling them
    private void toastNo() {
        Toast.makeText(this, "Please fill out all required fields!", Toast.LENGTH_SHORT).show();
    }

    private void toastTempDif() {
        Toast.makeText(this, "Make sure the maximum temperature is not smaller than the minimum temperature!", Toast.LENGTH_SHORT).show();
    }
}
