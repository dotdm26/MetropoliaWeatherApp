package com.example.metropoliaweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PreferenceInfoActivity extends AppCompatActivity {

    private TextView name, min, max, location, weather;
    Button listBt, removeBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preference_info_activity);

        name = findViewById(R.id.prefName);
        min = findViewById(R.id.maxTemp);
        max = findViewById(R.id.minTemp);
        location = findViewById(R.id.location);
        weather = findViewById(R.id.weather);

        //Button that returns to home screen
        listBt = findViewById(R.id.listButton);
        listBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnList();
            }
        });

        //Button that removes the preference (TO DO!!!)
        removeBt = findViewById(R.id.removeButton);
        removeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //Displays detailed info of preference
        int i = getIntent().getIntExtra("EXTRA", 0);
        Log.d("Test", Integer.toString(i));
        name.setText(GlobalModel.getInstance().getPreferences().get(i).getName());
        min.setText((GlobalModel.getInstance().getPreferences().get(i).getMinTemp()) + "°C");
        max.setText((GlobalModel.getInstance().getPreferences().get(i).getMaxTemp()) + "°C");
        location.setText(GlobalModel.getInstance().getPreferences().get(i).getLocation());
        weather.setText(GlobalModel.getInstance().getPreferences().get(i).getWeatherType());
    }


    //Returns to list of preference
    public void returnList() {
        Intent list = new Intent(this, listSavedPrefsActivity.class);
        startActivity(list);
    }

    public void removePref() {
        /**
         * TO DO: FIND A WAY TO REMOVE SELECTED PREFERENCE
         */
    }
}
