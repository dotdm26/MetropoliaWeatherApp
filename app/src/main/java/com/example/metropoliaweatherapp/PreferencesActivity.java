package com.example.metropoliaweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PreferencesActivity extends AppCompatActivity {

    /**
     * An activity where the user can create new weather preferences for a specific activity (to be named by the user)
     * Add/Remove activities
     * Name, weather description, temperature, humidity, location? and etc.
     */

    Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

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
