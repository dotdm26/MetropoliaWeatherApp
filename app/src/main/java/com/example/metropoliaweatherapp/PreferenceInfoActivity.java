package com.example.metropoliaweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PreferenceInfoActivity extends AppCompatActivity {

    private TextView name, min, max, location, weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preference_info_activity);

        name = findViewById(R.id.prefName);
        min = findViewById(R.id.minTemp);
        max = findViewById(R.id.maxTemp);
        location = findViewById(R.id.location);
        weather = findViewById(R.id.weatherType);

        int i = getIntent().getIntExtra("EXTRA", 0);
        name.setText(GlobalModel.getInstance().getPreferences().get(i).getName());
        min.setText(Double.toString(GlobalModel.getInstance().getPreferences().get(i).getMinTemp()));
        max.setText(Double.toString(GlobalModel.getInstance().getPreferences().get(i).getMaxTemp()));
        location.setText(GlobalModel.getInstance().getPreferences().get(i).getLocation());
        weather.setText(GlobalModel.getInstance().getPreferences().get(i).getWeatherType());
    }
}
