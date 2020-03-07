package com.example.metropoliaweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import static com.example.metropoliaweatherapp.GlobalModel.preferences;


public class listSavedPrefsActivity extends AppCompatActivity {

    Button homeButton, prefButton;
    /**
     * Where the user can scroll through their saved preferences on a ListView.
     */

    private ListView lv;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_saved_prefs);

        //Displays ListView of saved preferences
        lv = findViewById(R.id.lvSavedPrefs);
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                GlobalModel.getInstance().getPreferences());
        lv.setAdapter(adapter);
        //Opens detailed info of preference in PreferenceInfoActivity.java
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                Intent prefInfo = new Intent(view.getContext(), PreferenceInfoActivity.class);
                prefInfo.putExtra("EXTRA", i);
                startActivity(prefInfo);
            }
        });
        //Removes preference by long-clicking on the name
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int index, long l) {
                preferences.remove(index);
                adapter.notifyDataSetChanged();
                toastRemove();
                return true;
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

    public void toastRemove() {
        Toast.makeText(this, "Removed preference", Toast.LENGTH_SHORT).show();
    }
}
