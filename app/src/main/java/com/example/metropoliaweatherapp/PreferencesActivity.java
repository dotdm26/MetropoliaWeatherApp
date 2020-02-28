package com.example.metropoliaweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class PreferencesActivity extends AppCompatActivity {

    /**
     * An activity where the user can create new weather preferences for a specific activity (to be named by the user)
     * Add/Remove activities
     * Name, weather description, temperature, humidity, location? and etc.
     */
    Spinner spinner;
    Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        //Spinner for user preferences. Allows the user to choose from their saved preferences and display more information about the preferences.
        spinner = findViewById(R.id.savedPrefs);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.saved_user_preferences,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Spinner to select favoured kind of weather when the user is creating a new preference.
        spinner = findViewById(R.id.weatherTypeSpin);
        ArrayAdapter<CharSequence> adapterW = ArrayAdapter.createFromResource(
                this,
                R.array.weather_types,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterW);

        //Button to return to main activity
        returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnMain();
            }
        });


    }

    public void returnMain() {
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);

    }
}
