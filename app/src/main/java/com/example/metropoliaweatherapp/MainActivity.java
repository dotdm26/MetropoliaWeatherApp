package com.example.metropoliaweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MainActivity extends AppCompatActivity {
    /**
     * Big message displaying "nice day" or "not a nice day".
     * Followed by a few details about the weather.
     * A spinner to select a specific activity (that comes with the preferred weather, ...)
     * Location button to locate the user and get the weather data?
     * Calendar button to review past dates' information
     */

    private Button calendarButton;
    private Button userButton;
    private Button listButton;
    private Elements source;
    private TextView parse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userButton = findViewById(R.id.userButton);
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefAdd();
            }
        });

        listButton = findViewById(R.id.homeButton);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lvPrefs();
            }
        });

        calendarButton = findViewById(R.id.calendarButton);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar();
            }
        });

        parse = findViewById(R.id.weatherData);
        new doIt().execute();
    }

    public void prefAdd() {
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

    public class doIt extends AsyncTask<Void, Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect("https://www.bbc.com/weather/658225").get();
                source = doc.getElementsByClass("wr-value--temperature--c");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            parse.setText(source.get(0).text());
        }
    }
}
