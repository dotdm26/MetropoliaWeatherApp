package com.example.metropoliaweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PreferencesActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "SHARED PREFERENCES";
    public static final String PREF_NAME = "PREFERENCE NAME";
    public static final String WEATHER = "WEATHER TYPE";
    public static final String MIN_TEMP = "MINIMUM TEMPERATURE";
    public static final String MAX_TEMP = "MAXIMUM TEMPERATURE";
    public static final String LOCATION = "LOCATION";
    /**
     * An activity where the user can create new weather preferences for a specific activity (to be named by the user)
     * Add/Remove activities
     * Name, weather description, temperature, location and etc.
     *
     * TO DO LIST:
     * - Find a way to index each saved preferences (0; 1; 2; ...)
     * so that we can be able to recognize and have the option to delete the preference
     * later??
     */

    TextView newPref;
    TextView weatherType;
    TextView prefLocation;
    TextView minTemp;
    TextView maxTemp;
    Button homeButton;
    Button addPrefButton;
    Button prefDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        newPref = (EditText) findViewById(R.id.newPref);
        weatherType = (EditText) findViewById(R.id.weatherType);
        prefLocation = (EditText) findViewById(R.id.prefLocation);
        minTemp = (EditText) findViewById(R.id.minTemp);
        maxTemp = (EditText) findViewById(R.id.maxTemp);


        //Button to return to main activity
        homeButton = findViewById(R.id.listButton);
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
                /**
                 * TO DO: Convert the Min temp and Max temp to integers, and make sure Max >= Min
                 * otherwise we get a Toast that says something like
                 * "Make sure your maximum temp is bigger than or equal to your minimum temp!"
                 */
                if (newPref.getText().toString().isEmpty() || weatherType.getText().toString().isEmpty() || prefLocation.getText().toString().isEmpty() || minTemp.getText().toString().isEmpty() || maxTemp.getText().toString().isEmpty()) {
                    toastNo();
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
    public void lvPrefs() {
        Intent list = new Intent(this, listSavedPrefsActivity.class);
        startActivity(list);
    }

    //Opens new activity (Main Activity)
    public void returnHome() {
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
    }

    //Adds preference
    public void prefAdd() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_NAME, newPref.getText().toString());
        editor.putString(WEATHER, weatherType.getText().toString());
        editor.putString(MIN_TEMP, String.valueOf(Integer.parseInt(minTemp.getText().toString())));
        editor.putString(MAX_TEMP, String.valueOf(Integer.parseInt(maxTemp.getText().toString())));
        editor.putString(LOCATION, prefLocation.getText().toString());
        editor.commit();

        String name = sharedPreferences.getString(PREF_NAME, "");
        String minTempStr = sharedPreferences.getString(MIN_TEMP, "");
        String maxTempStr = sharedPreferences.getString(MAX_TEMP, "");
        String location = sharedPreferences.getString(LOCATION, "");
        String weatherType = sharedPreferences.getString(WEATHER, "");
        GlobalModel preference = GlobalModel.getInstance();
        int minTemp = Integer.parseInt(minTempStr);
        int maxTemp = Integer.parseInt(maxTempStr);
        preference.prefAdd(new Preference(name, minTemp, maxTemp, location, weatherType));
    }

    //Toast confirming the addition of preference
    public void toastYes() {
        Toast.makeText(this, "User preferences have been saved", Toast.LENGTH_SHORT).show();
    }

    //Toast informing the user to fill out the preference's form if user clicks "Add" without filling them
    public void toastNo() {
        Toast.makeText(this, "Please fill out all required fields!", Toast.LENGTH_SHORT).show();
    }
}
