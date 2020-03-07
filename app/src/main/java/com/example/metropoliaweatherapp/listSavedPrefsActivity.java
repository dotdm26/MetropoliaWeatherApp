package com.example.metropoliaweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class listSavedPrefsActivity extends AppCompatActivity {

    Button homeButton;
    Button prefButton;
    /**
     * Where the user can scroll through their saved preferences on a ListView.
     */

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_saved_prefs);

        //Displays ListView of saved preferences
        lv = findViewById(R.id.lvSavedPrefs);
        lv.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                GlobalModel.getInstance().getPreferences())
        );
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                Intent prefInfo = new Intent(view.getContext(), PreferenceInfoActivity.class);
                prefInfo.putExtra("EXTRA", i);
                startActivity(prefInfo);
            }
        });

        //Button to return to activity where the user creates and adds preferences
        prefButton = findViewById(R.id.prefButton);
        prefButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnPref();
            }
        });

        //Button to return to home screen (MainActivity)
        homeButton = findViewById(R.id.listButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnHome();
            }
        });
    }

    //Opens PreferencesActivity.java
    public void returnPref() {
        Intent pref = new Intent(this, PreferencesActivity.class);
        startActivity(pref);
    }

    //Opens MainActivity.java
    public void returnHome() {
        Intent list = new Intent(this, listSavedPrefsActivity.class);
        startActivity(list);
    }
}
