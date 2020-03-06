package com.example.metropoliaweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static com.example.metropoliaweatherapp.PreferencesActivity.LOCATION;
import static com.example.metropoliaweatherapp.PreferencesActivity.MAX_TEMP;
import static com.example.metropoliaweatherapp.PreferencesActivity.MIN_TEMP;
import static com.example.metropoliaweatherapp.PreferencesActivity.PREF_NAME;
import static com.example.metropoliaweatherapp.PreferencesActivity.SHARED_PREFS;
import static com.example.metropoliaweatherapp.PreferencesActivity.WEATHER;


public class listSavedPrefsActivity extends AppCompatActivity {

    /**
     * Where the user can scroll through their saved preferences on a ListView.
     * <p>
     * TO DO:
     * -
     */

    public ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_saved_prefs);


        lv = findViewById(R.id.lvSavedPrefs);
        GlobalModel preference = GlobalModel.getInstance();

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String name = sharedPreferences.getString(PREF_NAME, "");
        String minTempStr = sharedPreferences.getString(MIN_TEMP, "");
        String maxTempStr = sharedPreferences.getString(MAX_TEMP, "");
        String location = sharedPreferences.getString(LOCATION, "");
        String weatherType = sharedPreferences.getString(WEATHER, "");

        double minTemp = Double.parseDouble(minTempStr);
        double maxTemp = Double.parseDouble(maxTempStr);

        preference.prefAdd(new Preference(name, minTemp, maxTemp, location, weatherType));


        lv.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                GlobalModel.getInstance().getPreferences())
        );

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                Intent prefInfo = new Intent(view.getContext(), PreferenceInfoActivity.class);
                startActivity(prefInfo);
            }
        });
    }
}
