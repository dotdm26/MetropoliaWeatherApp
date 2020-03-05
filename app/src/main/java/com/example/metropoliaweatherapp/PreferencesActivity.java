package com.example.metropoliaweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.metropoliaweatherapp.PreferencesActivity.LOCATION;
import static com.example.metropoliaweatherapp.PreferencesActivity.MAX_TEMP;
import static com.example.metropoliaweatherapp.PreferencesActivity.MIN_TEMP;
import static com.example.metropoliaweatherapp.PreferencesActivity.PREF_NAME;
import static com.example.metropoliaweatherapp.PreferencesActivity.SHARED_PREFS;

public class PreferencesActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "SHARED PREFERENCES";
    public static final String PREF_NAME = "PREFERENCE NAME";
    public static final String MIN_TEMP = "MINIMUM TEMPERATURE";
    public static final String MAX_TEMP = "MAXIMUM TEMPERATURE";
    public static final String LOCATION = "LOCATION";
    /**
     * An activity where the user can create new weather preferences for a specific activity (to be named by the user)
     * Add/Remove activities
     * Name, weather description, temperature, humidity, location? and etc.
     *
     * TO DO LIST:
     * - Store user preferences
     * - Make the spinner react and display detailed information of the saved preferences. The spinner's selections should be the name of the saved prefs.
     * - Make sure the stored preferences also work in other activities.
     */

    TextView prefInfo;
    TextView newPref;
    TextView prefLocation;
    TextView minTemp;
    TextView maxTemp;
    Spinner spinner2;
    Button returnButton;
    Button addPrefButton;
    Button prefDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        final listActivity cls = new listActivity();
        prefInfo = findViewById(R.id.savedPrefDetails);
        newPref = (EditText) findViewById(R.id.newPref);
        prefLocation = (EditText) findViewById(R.id.prefLocation);
        minTemp = (EditText) findViewById(R.id.minTemp);
        maxTemp = (EditText) findViewById(R.id.maxTemp);


        //Spinner to select favoured kind of weather when the user is creating a new preference.
        spinner2 = findViewById(R.id.weatherTypeSpin);
        ArrayAdapter<CharSequence> adapterW = ArrayAdapter.createFromResource(
                this,
                R.array.weather_types,
                android.R.layout.simple_spinner_item
        );
        adapterW.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapterW);

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
                cls.addPrefs();
            }
        });

        prefDisplay = findViewById(R.id.prefDisplayButton);
        prefDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefView();
            }
        });
    }

    public void returnMain() {
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);

    }

    /**
     * public void prefAdd() {
     * SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
     * SharedPreferences.Editor editor = sharedPreferences.edit();
     * editor.putString(PREF_NAME, newPref.getText().toString());
     * editor.putString(MIN_TEMP, minTemp.getText().toString());
     * editor.putString(MAX_TEMP, maxTemp.getText().toString());
     * editor.putString(LOCATION, prefLocation.getText().toString());
     * editor.apply();
     * Toast.makeText(this, "Preference saved!", Toast.LENGTH_SHORT);
     * }
     */


    public void prefView() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String minTempView = sharedPreferences.getString(MIN_TEMP, "");
        String maxTempView = sharedPreferences.getString(MAX_TEMP, "");
        String location = sharedPreferences.getString(LOCATION, "");

        prefInfo.setText(minTempView + "°C\n" + maxTempView + "°C\n" + location);
    }

}

//---------------------------------------------------------

class listActivity extends ListActivity {

    PreferencesActivity pA = new PreferencesActivity();
    TextView newPref = pA.newPref;
    TextView prefLocation = pA.prefLocation;
    TextView minTemp = pA.minTemp;
    TextView maxTemp = pA.maxTemp;

    ListView listPrefs;
    ArrayList<String> ListOfPrefs = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_preferences);

        listPrefs = findViewById(R.id.listofPrefs);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ListOfPrefs);
        setListAdapter(adapter);

    }

    public void addPrefs() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_NAME, newPref.getText().toString());
        editor.putString(MIN_TEMP, minTemp.getText().toString());
        editor.putString(MAX_TEMP, maxTemp.getText().toString());
        editor.putString(LOCATION, prefLocation.getText().toString());
        editor.commit();
        adapter.add(PREF_NAME);
    }

}