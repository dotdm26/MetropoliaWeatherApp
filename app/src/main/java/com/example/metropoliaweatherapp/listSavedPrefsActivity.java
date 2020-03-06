package com.example.metropoliaweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class listSavedPrefsActivity extends AppCompatActivity {

    /**
     * Where the user can scroll through their saved preferences on a ListView.
     */

    public ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_saved_prefs);

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
    }
}
