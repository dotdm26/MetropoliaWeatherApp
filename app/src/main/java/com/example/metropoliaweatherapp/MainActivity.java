package com.example.metropoliaweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import static com.example.metropoliaweatherapp.GlobalModel.preferences;

public class MainActivity extends AppCompatActivity {
    /**
     * Big message displaying "nice day" or "not a nice day".
     * Followed by a few details about the weather.
     * A spinner to select a specific activity (that comes with the preferred weather, ...)
     * Location button to locate the user and get the weather data?
     * Calendar button to review past dates' information
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button userButton = findViewById(R.id.userButton);
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefNew();
            }
        });

        Button listButton = findViewById(R.id.listButton);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lvPrefs();
            }
        });

        Button calendarButton = findViewById(R.id.calendarButton);
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

    public void prefNew() {
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

}
